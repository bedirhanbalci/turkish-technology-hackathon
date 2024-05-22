package com.demo.multipayment.services.dtos.account.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountRequest {

    private String accountNumber;

    private Float balance;

    private Integer bankId;

}
