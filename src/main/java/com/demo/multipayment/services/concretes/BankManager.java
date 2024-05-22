package com.demo.multipayment.services.concretes;

import com.demo.multipayment.core.utilities.exceptions.BusinessException;
import com.demo.multipayment.core.utilities.mappers.ModelMapperService;
import com.demo.multipayment.entities.concretes.*;
import com.demo.multipayment.repositories.BankRepository;
import com.demo.multipayment.repositories.TransactionItemRepository;
import com.demo.multipayment.services.abstracts.AccountService;
import com.demo.multipayment.services.abstracts.BankService;
import com.demo.multipayment.services.abstracts.CheckoutService;
import com.demo.multipayment.services.dtos.bank.requests.AddBankRequest;
import com.demo.multipayment.services.dtos.bank.requests.BankDepositeRequest;
import com.demo.multipayment.services.dtos.bank.requests.UpdateBankRequest;
import com.demo.multipayment.services.dtos.bank.responses.BankDepositResponse;
import com.demo.multipayment.services.dtos.bank.responses.GetAllBanksResponse;
import com.demo.multipayment.services.dtos.bank.responses.GetByIdBankResponse;
import com.demo.multipayment.services.rules.BankBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BankManager implements BankService {

    private final BankRepository bankRepository;

    private final AccountService accountService;

    private final ModelMapperService modelMapperService;

    private final BankBusinessRules bankBusinessRules;
   @Autowired
    private final TransactionItemRepository transactionItemRepository;

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
    public BankDepositResponse bankDepositResponse (BankDepositeRequest bankDepositeRequest)  {
        Bank bank = findBankByCardNumber(bankDepositeRequest.getCardNumber());
        if (bank == null) {
            bank.setStatus(BankStatus.FAILED);
        }
        Account account = findAccountByCardNumber(bankDepositeRequest.getCardNumber());
        if (account == null || account.getBalance() < bankDepositeRequest.getAmount()) {
            bank.setStatus(BankStatus.FAILED);
        }

        if (account == null || account.getBalance() < bankDepositeRequest.getAmount()) {
            bank.setStatus(BankStatus.FAILED);
        }
        String transactionId = UUID.randomUUID().toString();
        TransactionItem transactionItem  = TransactionItem.builder()
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

  /*  public String bankDepositeResponse(BankDepositeRequest bankDepositeRequest) {
        Bank bank = findBankByCardNumber(bankDepositeRequest.getCardNumber());
        if (bank == null) {
            bank.setStatus(BankStatus.FAILED);
        }
        Account account = findAccountByCardNumber(bankDepositeRequest.getCardNumber());
        if (account == null || account.getBalance() < bankDepositeRequest.getAmount()) {
            bank.setStatus(BankStatus.FAILED);
        }
        String transactionId = UUID.randomUUID().toString();
        TransactionItem transactionItem  = new TransactionItem();
        transactionItem.setTransactionUUid(transactionId);
        transactionItem.setBank(bank);
        transactionItem.setBankName(bank.getName());
        transactionItem.setAccount(account);
        transactionItem.setAmount(bankDepositeRequest.getAmount());
        transactionItem.setAccountNumber(account.getAccountNumber());
        transactionItem.setCheckoutId(bankDepositeRequest.getCheckoutId());
        bank.getTransactionItems().add(transactionItem);
        account.getTransactionItems().add(transactionItem);
        bank.setStatus(BankStatus.APPROVE);
        transactionItemRepository.save(transactionItem);
        bankRepository.save(bank);

        BankDepositResponse bankDepositResponse = new BankDepositResponse();
        bankDepositResponse.setBankStatus(bank.getStatus());
        bankDepositResponse.setTransactionId(transactionId);
        /*
        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.APPROVE);
        payment.setBankName(bank.getName());
        payment.setAmount(bankDepositeRequest.getAmount());
        payment.setCardPrefix(bank.getCardPrefix());
        payment.setTransactionId(transactionId);
        return transactionId;
    } * /


/*    public BankConfirm bankConfirm(){
        return null;
    }
    public BankCancell bankCancell() {
        return null;
    } */

    @Override
    public BankStatus requestBank(String cardNumber ,Float amount) {
        Bank bank = findBankByCardNumber(cardNumber);
        if (bank == null) {
            bank.setStatus(BankStatus.FAILED);
            return BankStatus.FAILED;
        }
        Account account = findAccountByCardNumber(cardNumber);
        if (account == null || account.getBalance() < amount) {
            bank.setStatus(BankStatus.FAILED);
            return BankStatus.FAILED;
        }
        bank.setStatus(BankStatus.APPROVE);
        return BankStatus.APPROVE;
    }
    @Override
    public void paymentProccess(String cardNumber, Float amount) {
        Account account = findAccountByCardNumber(cardNumber);
        account.setBalance(account.getBalance()-amount);
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
        if(account==null){
            throw new BusinessException("Account not found for card number");
        }
        return account;

    }
}
