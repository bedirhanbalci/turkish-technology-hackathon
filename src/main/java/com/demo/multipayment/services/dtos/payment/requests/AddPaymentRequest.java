package com.demo.multipayment.services.dtos.payment.requests;

import com.demo.multipayment.entities.concretes.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPaymentRequest {

    private Integer checkoutId;
    private List<CardRequest> cardRequestList;

}
