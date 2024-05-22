package com.demo.multipayment.services.concretes;

import com.demo.multipayment.core.utilities.mappers.ModelMapperService;
import com.demo.multipayment.entities.concretes.Account;
import com.demo.multipayment.repositories.AccountRepository;
import com.demo.multipayment.services.abstracts.AccountService;
import com.demo.multipayment.services.dtos.account.requests.AddAccountRequest;
import com.demo.multipayment.services.dtos.account.requests.UpdateAccountRequest;
import com.demo.multipayment.services.dtos.account.responses.GetAllAccountsResponse;
import com.demo.multipayment.services.dtos.account.responses.GetByIdAccountResponse;
import com.demo.multipayment.services.rules.AccountBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountManager implements AccountService {

    private final AccountRepository accountRepository;

    private final ModelMapperService modelMapperService;

    private final AccountBusinessRules accountBusinessRules;

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void saveByAccount(Account account) {
        this.accountRepository.save(account);
    }

    @Override
    public void add(AddAccountRequest addAccountRequest) {

        Account account = this.modelMapperService.forRequest().map(addAccountRequest, Account.class);
        account.setId(null);

        this.accountRepository.save(account);

    }

    @Override
    public void delete(Integer id) {

        this.accountBusinessRules.checkIfAccountIdExists(id);

        Account account = this.modelMapperService.forRequest().map(id, Account.class);

        this.accountRepository.delete(account);

    }

    @Override
    public void update(UpdateAccountRequest updateAccountRequest) {

        this.accountBusinessRules.checkIfAccountIdExists(updateAccountRequest.getId());

        Account account = this.modelMapperService.forRequest().map(updateAccountRequest, Account.class);

        this.accountRepository.save(account);

    }

    @Override
    public GetByIdAccountResponse getById(Integer id) {

        this.accountBusinessRules.checkIfAccountIdExists(id);

        GetByIdAccountResponse response = this.modelMapperService.forResponse()
                .map(accountRepository.findById(id), GetByIdAccountResponse.class);

        return response;

    }

    @Override
    public List<GetAllAccountsResponse> getAll() {

        List<Account> accounts = accountRepository.findAll();

        List<GetAllAccountsResponse> accountsResponse = accounts.stream()
                .map(account -> this.modelMapperService.forResponse().map(account, GetAllAccountsResponse.class)).toList();

        return accountsResponse;
    }

}
