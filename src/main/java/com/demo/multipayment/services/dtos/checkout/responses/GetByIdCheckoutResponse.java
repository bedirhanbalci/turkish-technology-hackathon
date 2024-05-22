package com.demo.multipayment.services.dtos.checkout.responses;

import com.demo.multipayment.entities.concretes.CheckoutStatus;
import com.demo.multipayment.entities.concretes.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCheckoutResponse {

    private Integer id;

    private Float amount;

    private CheckoutStatus status;

    private PaymentMethod paymentMethod;

    private String userEmail;

    private String flightNumber;

}

