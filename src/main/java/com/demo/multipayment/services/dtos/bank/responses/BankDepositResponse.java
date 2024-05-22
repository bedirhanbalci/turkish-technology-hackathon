package com.demo.multipayment.services.dtos.bank.responses;

import com.demo.multipayment.entities.concretes.BankStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankDepositResponse {

    private String transactionId;
    private BankStatus bankStatus;
}
