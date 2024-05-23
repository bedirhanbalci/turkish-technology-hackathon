package com.demo.multipayment.services.concretes;

import com.demo.multipayment.core.utilities.exceptions.BusinessException;
import com.demo.multipayment.core.utilities.mappers.ModelMapperService;
import com.demo.multipayment.entities.concretes.*;
import com.demo.multipayment.repositories.BankRepository;
import com.demo.multipayment.repositories.TransactionItemRepository;
import com.demo.multipayment.services.abstracts.AccountService;
import com.demo.multipayment.services.abstracts.BankService;
import com.demo.multipayment.services.dtos.bank.requests.AddBankRequest;
import com.demo.multipayment.services.dtos.bank.requests.BankDepositeRequest;
import com.demo.multipayment.services.dtos.bank.requests.UpdateBankRequest;
import com.demo.multipayment.services.dtos.bank.responses.BankDepositResponse;
import com.demo.multipayment.services.dtos.bank.responses.GetAllBanksResponse;
import com.demo.multipayment.services.dtos.bank.responses.GetByIdBankResponse;
import com.demo.multipayment.services.rules.BankBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BankManager implements BankService {

    private final BankRepository bankRepository;

    private final TransactionItemRepository transactionItemRepository;

    private final AccountService accountService;

    private final ModelMapperService modelMapperService;

    private final BankBusinessRules bankBusinessRules;


    @Override
    public void add(AddBankRequest addBankRequest) {

        Bank bank = this.modelMapperService.forRequest()
                .map(addBankRequest, Bank.class);
        bank.setId(null);

        this.bankRepository.save(bank);

    }

    @Override
    public void delete(Integer id) {

        this.bankBusinessRules.checkIfBankIdExists(id);

        Bank bank = this.modelMapperService.forRequest()
                .map(id, Bank.class);

        this.bankRepository.delete(bank);

    }

    @Override
    public void update(UpdateBankRequest updateBankRequest) {

        this.bankBusinessRules.checkIfBankIdExists(updateBankRequest.getId());

        Bank bank = this.modelMapperService.forRequest()
                .map(updateBankRequest, Bank.class);

        this.bankRepository.save(bank);

    }

    @Override
    public GetByIdBankResponse getById(Integer id) {

        this.bankBusinessRules.checkIfBankIdExists(id);

        GetByIdBankResponse response = this.modelMapperService.forResponse()
                .map(bankRepository.findById(id), GetByIdBankResponse.class);

        return response;

    }

    @Override
    public List<GetAllBanksResponse> getAll() {

        List<Bank> banks = bankRepository.findAll();

        List<GetAllBanksResponse> banksResponse = banks.stream()
                .map(bank -> this.modelMapperService.forResponse().map(bank, GetAllBanksResponse.class)).toList();

        return banksResponse;

    }

    @Override
    public BankDepositResponse bankDepositResponse(BankDepositeRequest bankDepositeRequest) {
        Bank bank = findBankByCardNumber(bankDepositeRequest.getCardNumber());
        if (bank == null) {
            bank.setStatus(BankStatus.FAILED);
            return BankDepositResponse.builder()
                    .bankStatus(BankStatus.FAILED)
                    .build();
        }
        Account account = findAccountByCardNumber(bankDepositeRequest.getCardNumber());
        if (account == null || account.getBalance() < bankDepositeRequest.getAmount()) {
            bank.setStatus(BankStatus.FAILED);
            return BankDepositResponse.builder()
                    .bankStatus(BankStatus.FAILED)
                    .build();
        }

        String transactionId = UUID.randomUUID().toString();
        TransactionItem transactionItem = TransactionItem.builder()
                .transactionUUid(transactionId)
                .bank(bank)
                .bankName(bank.getName())
                .account(account)
                .amount(bankDepositeRequest.getAmount())
                .accountNumber(account.getAccountNumber())
                .checkoutId(bankDepositeRequest.getCheckoutId())
                .build();
        bank.getTransactionItems().add(transactionItem);
        account.getTransactionItems().add(transactionItem);
        bank.setStatus(BankStatus.APPROVE);
        transactionItemRepository.save(transactionItem);
        bankRepository.save(bank);
        BankDepositResponse bankDepositResponse = BankDepositResponse.builder()
                .bankStatus(bank.getStatus())
                .transactionId(transactionId)
                .build();
        return bankDepositResponse;
    }

    @Override
    public void paymentProccess(String cardNumber, Float amount) {

        Account account = findAccountByCardNumber(cardNumber);
        account.setBalance(account.getBalance() - amount);

    }

    @Override
    public Bank findBankByCardNumber(String cardNumber) {

        String cardPrefix = cardNumber.substring(0, 4);
        Bank bank = bankRepository.findByCardPrefix(cardPrefix);
        if (bank == null) {
            throw new BusinessException("Bank not found for card number");
        }
        return bank;

    }

    @Override
    public Account findAccountByCardNumber(String cardNumber) {

        String accountNumber = cardNumber.substring(4);
        Account account = accountService.getByAccountNumber(accountNumber);
        if (account == null) {
            throw new BusinessException("Account not found for card number");
        }
        return account;

    }
}
