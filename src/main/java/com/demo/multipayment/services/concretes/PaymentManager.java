package com.demo.multipayment.services.concretes;

import com.demo.multipayment.core.utilities.mappers.ModelMapperService;
import com.demo.multipayment.entities.concretes.*;
import com.demo.multipayment.repositories.PaymentRepository;
import com.demo.multipayment.services.abstracts.*;
import com.demo.multipayment.services.dtos.payment.requests.AddPaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.PaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.UpdatePaymentRequest;
import com.demo.multipayment.services.dtos.payment.responses.GetAllPaymentsResponse;
import com.demo.multipayment.services.dtos.payment.responses.GetByIdPaymentResponse;
import com.demo.multipayment.services.dtos.payment.responses.PaymentDetailResponse;
import com.demo.multipayment.services.rules.PaymentBusinessRules;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final CheckoutService checkoutService;

    private final AccountService accountService;

    private final TransactionItemService transactionItemService;

    private final ModelMapperService modelMapperService;

    private final PaymentBusinessRules paymentBusinessRules;


    @Override
    public void processPayment(String transactionId, List<PaymentDetailResponse.PaymentDetail> paymentDetails) {

        TransactionItem transactionItem = transactionItemService.findTransactionItem(transactionId);
        Checkout checkout = checkoutService.getById(transactionItem.getCheckoutId());
        Bank bank = transactionItem.getBank();
        Account account = transactionItem.getAccount();
        account.setBalance(account.getBalance() - transactionItem.getAmount());
        this.accountService.saveByAccount(account);
        Payment payment = Payment.builder()
                .status(PaymentStatus.APPROVE)
                .bankName(bank.getName())
                .amount(transactionItem.getAmount())
                .checkout(checkout)
                .build();
        paymentRepository.save(payment);

        PaymentDetailResponse.PaymentDetail paymentDetail = new PaymentDetailResponse.PaymentDetail();
        paymentDetail.setBankName(bank.getName());
        paymentDetail.setAmount(transactionItem.getAmount());
        paymentDetails.add(paymentDetail);

    }

    @Override
    @Transactional
    public void multiplePayment(PaymentRequest paymentRequest, List<PaymentDetailResponse.PaymentDetail> paymentDetails) {

        Checkout checkout = checkoutService.getById(paymentRequest.getCheckoutId());
        for (String transactionId : paymentRequest.getTransactionIds()) {
            processPayment(transactionId, paymentDetails);
        }
        checkout.setStatus(CheckoutStatus.SUCCESS);
        this.checkoutService.saveByCheckout(checkout);

    }

    @Override
    public PaymentDetailResponse getPaymentDetails(PaymentRequest paymentRequest) {

        List<PaymentDetailResponse.PaymentDetail> paymentDetails = new ArrayList<>();
        multiplePayment(paymentRequest, paymentDetails);
        PaymentDetailResponse response = new PaymentDetailResponse();
        response.setPaymentDetails(paymentDetails);
        return response;

    }

    @Override
    public void add(AddPaymentRequest addPaymentRequest) {

        Payment payment = this.modelMapperService.forRequest()
                .map(addPaymentRequest, Payment.class);
        payment.setId(null);

        this.paymentRepository.save(payment);

    }

    @Override
    public void delete(Integer id) {

        this.paymentBusinessRules.checkIfPaymentIdExists(id);

        Payment payment = this.modelMapperService.forRequest()
                .map(id, Payment.class);

        this.paymentRepository.delete(payment);

    }

    @Override
    public void update(UpdatePaymentRequest updatePaymentRequest) {

        this.paymentBusinessRules.checkIfPaymentIdExists(updatePaymentRequest.getId());

        Payment payment = this.modelMapperService.forRequest()
                .map(updatePaymentRequest, Payment.class);

        this.paymentRepository.save(payment);

    }

    @Override
    public GetByIdPaymentResponse getById(Integer id) {

        this.paymentBusinessRules.checkIfPaymentIdExists(id);

        GetByIdPaymentResponse response = this.modelMapperService.forResponse()
                .map(paymentRepository.findById(id), GetByIdPaymentResponse.class);

        return response;

    }

    @Override
    public List<GetAllPaymentsResponse> getAll() {

        List<Payment> payments = paymentRepository.findAll();

        List<GetAllPaymentsResponse> paymentsResponse = payments.stream()
                .map(payment -> this.modelMapperService.forResponse().map(payment, GetAllPaymentsResponse.class)).toList();

        return paymentsResponse;

    }

}
