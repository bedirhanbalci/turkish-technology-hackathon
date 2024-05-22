package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.entities.concretes.Account;
import com.demo.multipayment.services.dtos.account.requests.AddAccountRequest;
import com.demo.multipayment.services.dtos.account.requests.UpdateAccountRequest;
import com.demo.multipayment.services.dtos.account.responses.GetAllAccountsResponse;
import com.demo.multipayment.services.dtos.account.responses.GetByIdAccountResponse;

import java.util.List;

public interface AccountService {

    Account getByAccountNumber(String accountNumber);

    void saveByAccount(Account account);

    void add(AddAccountRequest addAccountRequest);

    void delete(Integer id);

    void update(UpdateAccountRequest updateAccountRequest);

    GetByIdAccountResponse getById(Integer id);

    List<GetAllAccountsResponse> getAll();

}
