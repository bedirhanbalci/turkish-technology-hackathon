package com.demo.multipayment.services.dtos.payment.responses;

import lombok.*;

@Builder
@Data
public class CreatePaymentResponse {

    private String  BankName;
    private Float amount;

}
