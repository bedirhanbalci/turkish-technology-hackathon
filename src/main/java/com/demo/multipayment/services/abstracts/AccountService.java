package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.entities.concretes.Account;

public interface AccountService {

    Account getByAccountNumber(String accountNumber);

    void saveByAccount(Account account);

}
