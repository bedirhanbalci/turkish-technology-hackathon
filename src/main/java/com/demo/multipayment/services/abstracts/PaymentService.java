package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.services.dtos.payment.requests.AddPaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.PaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.UpdatePaymentRequest;
import com.demo.multipayment.services.dtos.payment.responses.GetAllPaymentsResponse;
import com.demo.multipayment.services.dtos.payment.responses.GetByIdPaymentResponse;
import com.demo.multipayment.services.dtos.payment.responses.PaymentDetailResponse;

import java.util.List;

public interface PaymentService {

    void processPayment(String transactionId, List<PaymentDetailResponse.PaymentDetail> paymentDetails);

    void multiplePayment(PaymentRequest paymentRequest, List<PaymentDetailResponse.PaymentDetail> paymentDetails);

    PaymentDetailResponse getPaymentDetails(PaymentRequest paymentRequest);

    void add(AddPaymentRequest addPaymentRequest);

    void delete(Integer id);

    void update(UpdatePaymentRequest updatePaymentRequest);

    GetByIdPaymentResponse getById(Integer id);

    List<GetAllPaymentsResponse> getAll();

}
