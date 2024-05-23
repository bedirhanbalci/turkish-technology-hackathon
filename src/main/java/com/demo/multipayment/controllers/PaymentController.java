package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.PaymentService;
import com.demo.multipayment.services.dtos.payment.requests.AddPaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.PaymentRequest;
import com.demo.multipayment.services.dtos.payment.requests.UpdatePaymentRequest;
import com.demo.multipayment.services.dtos.payment.responses.GetAllPaymentsResponse;
import com.demo.multipayment.services.dtos.payment.responses.GetByIdPaymentResponse;
import com.demo.multipayment.services.dtos.payment.responses.PaymentDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/multiplePaymentCreate")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PaymentDetailResponse> processPayments(@RequestBody PaymentRequest paymentRequest) {

        PaymentDetailResponse response = paymentService.getPaymentDetails(paymentRequest);
        return ResponseEntity.ok(response);

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
