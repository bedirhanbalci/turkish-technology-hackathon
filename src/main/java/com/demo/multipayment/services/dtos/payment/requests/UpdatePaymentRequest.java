package com.demo.multipayment.services.dtos.payment.requests;

import com.demo.multipayment.entities.concretes.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {

    private Integer id;

    private Float amount;

    private String bankName;

    private PaymentStatus status;

    private Integer checkoutId;

}
