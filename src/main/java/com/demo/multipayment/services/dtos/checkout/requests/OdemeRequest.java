package com.demo.multipayment.services.dtos.checkout.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OdemeRequest {
    private Integer checkoutId;

    private List<String> transactionIds;

}
