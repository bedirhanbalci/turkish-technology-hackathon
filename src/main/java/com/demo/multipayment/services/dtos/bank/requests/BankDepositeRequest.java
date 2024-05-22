package com.demo.multipayment.services.dtos.bank.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDepositeRequest {
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private Float amount;
    private Integer checkoutId;
}
