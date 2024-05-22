package com.demo.multipayment.services.concretes;

import com.demo.multipayment.entities.concretes.Account;
import com.demo.multipayment.repositories.AccountRepository;
import com.demo.multipayment.services.abstracts.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountManager implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void saveByAccount(Account account) {
        this.accountRepository.save(account);
    }

}
