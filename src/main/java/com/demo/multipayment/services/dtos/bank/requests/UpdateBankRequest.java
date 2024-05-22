package com.demo.multipayment.services.dtos.bank.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBankRequest {

    private Integer id;

    private String name;

    private String cardPrefix;

}
