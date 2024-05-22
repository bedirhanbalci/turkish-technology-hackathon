package com.demo.multipayment.services.dtos.payment.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private Float amount;
}
