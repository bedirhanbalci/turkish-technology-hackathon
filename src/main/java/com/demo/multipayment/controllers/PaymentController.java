package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.PaymentService;
import com.demo.multipayment.services.dtos.payment.requests.AddPaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.PaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.UpdatePaymentRequest;
import com.demo.multipayment.services.dtos.payment.responses.GetAllPaymentsResponse;
import com.demo.multipayment.services.dtos.payment.responses.GetByIdPaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
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

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddPaymentRequest addPaymentRequest) {

        this.paymentService.add(addPaymentRequest);

    }

    @DeleteMapping()
    public void delete(@PathVariable Integer id) {

        this.paymentService.delete(id);

    }

    @PutMapping()
    public void update(@RequestBody UpdatePaymentRequest updatePaymentRequest) {

        this.paymentService.update(updatePaymentRequest);

    }

    @GetMapping()
    public List<GetAllPaymentsResponse> getAll() {

        return this.paymentService.getAll();

    }

    @GetMapping("/{id}")
    public GetByIdPaymentResponse getById(@PathVariable Integer id) {

        return this.paymentService.getById(id);

    }

}
