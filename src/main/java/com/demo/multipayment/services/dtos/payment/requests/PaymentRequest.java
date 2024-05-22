package com.demo.multipayment.services.dtos.payment.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest{

    private Integer checkoutId;

    private List<String> transactionIds;

}
