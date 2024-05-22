package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.services.dtos.payment.requests.AddPaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.PaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.UpdatePaymentRequest;
import com.demo.multipayment.services.dtos.payment.responses.GetAllPaymentsResponse;
import com.demo.multipayment.services.dtos.payment.responses.GetByIdPaymentResponse;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    //List<CreatePaymentResponse> createPayments(AddPaymentRequest addPaymentRequest);
    Map<String, Object> createPayments(AddPaymentRequest addPaymentRequest);

    void processPayment(String transactionId);

    void multiplePayment(PaymentRequest paymentRequest);

    void add(AddPaymentRequest addPaymentRequest);

    void delete(Integer id);

    void update(UpdatePaymentRequest updatePaymentRequest);

    GetByIdPaymentResponse getById(Integer id);

    List<GetAllPaymentsResponse> getAll();

}
