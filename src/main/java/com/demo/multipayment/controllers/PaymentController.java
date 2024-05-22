package com.demo.multipayment.controllers;

import com.demo.multipayment.entities.concretes.Payment;
import com.demo.multipayment.services.abstracts.PaymentService;
import com.demo.multipayment.services.dtos.payment.requests.AddPaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.PaymentRequest;
import com.demo.multipayment.services.dtos.payment.responses.CreatePaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cards")
@AllArgsConstructor
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

   /* @PostMapping
    public ResponseEntity<Map<String, Object>> createPayments(@RequestBody AddPaymentRequest addPaymentRequest) {
        try {
            List<CreatePaymentResponse> responses = paymentService.createPayments(addPaymentRequest);
            return new ResponseEntity<>(responses, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception (optional)
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPayments(@RequestBody AddPaymentRequest addPaymentRequest) {
        try {
            Map<String, Object> response = paymentService.createPayments(addPaymentRequest);
            HttpStatus status = "success".equals(response.get("status")) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, status);
        } catch (Exception e) {
            // İsteğe bağlı olarak istisnayı loglayabilirsiniz
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Bir iç sunucu hatası oluştu.");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/multiplePaymentCreate")
    public void multiplePaymentCreate(@RequestBody PaymentRequest paymentRequest) {
        this.paymentService.multiplePayment(paymentRequest);
    }



}
