package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.entities.concretes.Payment;
import com.demo.multipayment.services.dtos.payment.requests.AddPaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.PaymentRequest;
import com.demo.multipayment.services.dtos.payment.responses.CreatePaymentResponse;

import java.util.List;
import java.util.Map;

public interface PaymentService {

     //List<CreatePaymentResponse> createPayments(AddPaymentRequest addPaymentRequest);
     Map<String, Object> createPayments(AddPaymentRequest addPaymentRequest);
     void processPayment(String transactionId);
     void multiplePayment(PaymentRequest paymentRequest);
}
