package com.demo.multipayment.services.dtos.checkout.requests;

import com.demo.multipayment.entities.concretes.CheckoutStatus;
import com.demo.multipayment.entities.concretes.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCheckoutRequest {

    private Integer id;

    private Float amount;

    private CheckoutStatus status;

    private PaymentMethod paymentMethod;

    private Integer userId;

    private Integer flightId;

}
