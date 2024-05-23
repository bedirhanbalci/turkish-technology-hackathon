package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.entities.concretes.*;
import com.demo.multipayment.services.dtos.bank.requests.AddBankRequest;
import com.demo.multipayment.services.dtos.bank.requests.BankDepositeRequest;
import com.demo.multipayment.services.dtos.bank.requests.UpdateBankRequest;
import com.demo.multipayment.services.dtos.bank.responses.BankDepositResponse;
import com.demo.multipayment.services.dtos.bank.responses.GetAllBanksResponse;
import com.demo.multipayment.services.dtos.bank.responses.GetByIdBankResponse;

import java.util.List;

public interface BankService {

    void add(AddBankRequest addBankRequest);

    void delete(Integer id);

    void update(UpdateBankRequest updateBankRequest);

    GetByIdBankResponse getById(Integer id);

    List<GetAllBanksResponse> getAll();

    Bank findBankByCardNumber(String cardNumber);

    Account findAccountByCardNumber(String cardNumber);

    void paymentProccess(String cardNumber, Float amount);

    BankDepositResponse bankDepositResponse(BankDepositeRequest bankDepositeRequest);

}
