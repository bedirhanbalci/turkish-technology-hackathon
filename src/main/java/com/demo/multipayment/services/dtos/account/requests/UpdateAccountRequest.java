package com.demo.multipayment.services.dtos.account.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountRequest {

    private Integer id;

    private String accountNumber;

    private Float balance;

    private Integer bankId;

}
