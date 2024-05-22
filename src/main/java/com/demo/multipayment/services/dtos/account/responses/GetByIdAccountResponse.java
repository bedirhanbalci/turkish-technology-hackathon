package com.demo.multipayment.services.dtos.account.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdAccountResponse {

    private Integer id;

    private String accountNumber;

    private Float balance;

    private String bankName;

}
