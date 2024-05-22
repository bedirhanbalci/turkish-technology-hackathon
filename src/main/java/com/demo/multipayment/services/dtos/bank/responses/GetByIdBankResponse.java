package com.demo.multipayment.services.dtos.bank.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdBankResponse {

    private Integer id;

    private String name;

    private String cardPrefix;

}
