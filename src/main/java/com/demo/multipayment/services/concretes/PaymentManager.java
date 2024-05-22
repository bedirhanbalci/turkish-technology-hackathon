package com.demo.multipayment.services.concretes;

import com.demo.multipayment.entities.concretes.*;
import com.demo.multipayment.repositories.CheckoutRepository;
import com.demo.multipayment.repositories.PaymentRepository;
import com.demo.multipayment.services.abstracts.*;
import com.demo.multipayment.services.dtos.checkout.requests.OdemeRequest;
import com.demo.multipayment.services.dtos.payment.requests.AddPaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.CardRequest;
import com.demo.multipayment.services.dtos.payment.requests.PaymentRequest;
import com.demo.multipayment.services.dtos.payment.responses.CreatePaymentResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final CheckoutService checkoutService;

    private final BankService bankService;

    private final AccountService accountService;

    @Autowired
    TransactionItemService transactionItemService;

   /* public List<CreatePaymentResponse> createPayments(AddPaymentRequest addPaymentRequest) {
        Checkout checkout = checkoutService.getById(addPaymentRequest.getCheckoutId());
        List<CreatePaymentResponse> responseList = new ArrayList<>();
        checkout.setStatus(CheckoutStatus.INITIALIZE);
        boolean allPaymentsApproved = true;
        for(CardRequest cardRequest : addPaymentRequest.getCardRequestList()){
            BankStatus bankStatus = this.bankService.requestBank(cardRequest.getCardNumber(), cardRequest.getAmount());
            if (bankStatus != BankStatus.APPROVE) {
                allPaymentsApproved = false;
                break;
            }
        }
        for(CardRequest cardRequest : addPaymentRequest.getCardRequestList()) {
            Payment payment = new Payment();
            payment.setCheckout(checkout);
            payment.setAmount(cardRequest.getAmount());
            this.bankService.paymentProccess(cardRequest.getCardNumber(), cardRequest.getAmount());
            Bank bank = this.bankService.findBankByCardNumber(cardRequest.getCardNumber());
            CreatePaymentResponse response = CreatePaymentResponse.builder()
                    .BankName(bank.getName())
                    .amount(payment.getAmount())
                    .build();
            responseList.add(response);
            paymentRepository.save(payment);
        }

        return responseList;
    }*/
   /* public List<CreatePaymentResponse> createPayments(AddPaymentRequest addPaymentRequest) {
        Checkout checkout = checkoutService.getById(addPaymentRequest.getCheckoutId());
        List<CreatePaymentResponse> responseList = new ArrayList<>();
        checkout.setStatus(CheckoutStatus.INITIALIZE);
        boolean allBanksApproved = true;
        for (CardRequest cardRequest : addPaymentRequest.getCardRequestList()) {
            BankStatus bankStatus = this.bankService.requestBank(cardRequest.getCardNumber(), cardRequest.getAmount());
            if (bankStatus != BankStatus.APPROVE) {
                allBanksApproved = false;
                break;
            }
        }
        if (allBanksApproved) {
            checkout.setStatus(CheckoutStatus.PROCESSES);
            for (CardRequest cardRequest : addPaymentRequest.getCardRequestList()) {
                Payment payment = new Payment();
                payment.setCheckout(checkout);
                payment.setAmount(cardRequest.getAmount());
                this.bankService.paymentProccess(cardRequest.getCardNumber(), cardRequest.getAmount());
                Bank bank = this.bankService.findBankByCardNumber(cardRequest.getCardNumber());

                CreatePaymentResponse response = CreatePaymentResponse.builder()
                        .BankName(bank.getName())
                        .amount(payment.getAmount())
                        .build();
                responseList.add(response);
                paymentRepository.save(payment);
            }
            return responseList;
        }

       return null;
    } */
    @Transactional
    @Override
    public Map<String, Object> createPayments(AddPaymentRequest addPaymentRequest) {
        Checkout checkout = checkoutService.getById(addPaymentRequest.getCheckoutId());
        List<CreatePaymentResponse> responseList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        checkout.setStatus(CheckoutStatus.INITIALIZE);
        boolean allBanksApproved = true;
        String declinedBankName = null;
        for (CardRequest cardRequest : addPaymentRequest.getCardRequestList()) {
       /* if(!validateCreditCard(cardRequest)){
              result.put("status", "failure");
              result.put("message", "Invalid credit card: " + cardRequest.getCardNumber());
              checkout.setStatus(CheckoutStatus.FAILED);
              this.checkoutService.saveByCheckout(checkout);
              return result;
          } */
            BankStatus bankStatus = this.bankService.requestBank(cardRequest.getCardNumber(), cardRequest.getAmount());

            if (bankStatus != BankStatus.APPROVE) {
                allBanksApproved = false;
                Bank declinedBank = this.bankService.findBankByCardNumber(cardRequest.getCardNumber());
                declinedBankName = declinedBank.getName();
                checkout.setStatus(CheckoutStatus.FAILED);
                this.checkoutService.saveByCheckout(checkout);
                result.put("status", "failure");
                result.put("message", "Payment declined by bank: " + declinedBankName);
            }
        }
        if (allBanksApproved) {
            checkout.setStatus(CheckoutStatus.PROCESSES);
            for (CardRequest cardRequest : addPaymentRequest.getCardRequestList()) {
                Payment payment = new Payment();
                payment.setCheckout(checkout);
                payment.setAmount(cardRequest.getAmount());
                this.bankService.paymentProccess(cardRequest.getCardNumber(), cardRequest.getAmount());
                Bank bank = this.bankService.findBankByCardNumber(cardRequest.getCardNumber());
                CreatePaymentResponse response = CreatePaymentResponse.builder()
                        .BankName(bank.getName())
                        .amount(payment.getAmount())
                        .build();
                responseList.add(response);
                checkout.setStatus(CheckoutStatus.SUCCESS);
                this.checkoutService.saveByCheckout(checkout);
                paymentRepository.save(payment);
            }
            result.put("status", "success");
            result.put("payments", responseList);
        }
        return result;
    }

    @Override
    public void processPayment(String transactionId) {
        TransactionItem transactionItem = this.transactionItemService.findTransactionItem(transactionId);
        Checkout checkout = this.checkoutService.getById(transactionItem.getCheckoutId());
        Bank bank = transactionItem.getBank();
        Account account =transactionItem.getAccount();
        account.setBalance(account.getBalance()-transactionItem.getAmount());
        this.accountService.saveByAccount(account);
        Payment payment = Payment.builder()
                .status(PaymentStatus.APPROVE)
                .bankName(bank.getName())
                .amount(transactionItem.getAmount())
                .checkout(checkout)
                .build();
        paymentRepository.save(payment);

    }
    @Transactional
    @Override
    public void multiplePayment(PaymentRequest paymentRequest) {
        Checkout checkout = checkoutService.getById(paymentRequest.getCheckoutId());
        for (String transactionId : paymentRequest.getTransactionIds()) {
                processPayment(transactionId);
        }
        checkout.setStatus(CheckoutStatus.SUCCESS);
        this.checkoutService.saveByCheckout(checkout);
    }
    public boolean validateCreditCard(CardRequest cardRequest) {
        if (!isValidExpiryDate(cardRequest.getExpiryDate())) {
            return false;
        }
        if (!isLuhnValid(cardRequest.getCardNumber())) {
            return false;
        }
        return true;
    }
    private static boolean isValidExpiryDate(String expiryDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
            Date expDate = sdf.parse(expiryDate);
            Date currentDate = new Date();
            return expDate.after(currentDate);
        } catch (ParseException e) {
            return false;
        }
    }
    private static boolean isLuhnValid(String cardNumber) {
        int[] digits = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            digits[i] = Character.getNumericValue(cardNumber.charAt(i));
        }

        for (int i = digits.length - 2; i >= 0; i -= 2) {
            int doubleDigit = digits[i] * 2;
            if (doubleDigit > 9) {
                doubleDigit -= 9;
            }
            digits[i] = doubleDigit;
        }

        int sum = 0;
        for (int digit : digits) {
            sum += digit;
        }

        return sum % 10 == 0;
    }
}
