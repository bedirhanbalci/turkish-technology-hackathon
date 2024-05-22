package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.BankService;
import com.demo.multipayment.services.dtos.bank.requests.AddBankRequest;
import com.demo.multipayment.services.dtos.bank.requests.BankDepositeRequest;
import com.demo.multipayment.services.dtos.bank.requests.UpdateBankRequest;
import com.demo.multipayment.services.dtos.bank.responses.BankDepositResponse;
import com.demo.multipayment.services.dtos.bank.responses.GetAllBanksResponse;
import com.demo.multipayment.services.dtos.bank.responses.GetByIdBankResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@AllArgsConstructor
@CrossOrigin
public class BankController {

    private final BankService bankService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddBankRequest addBankRequest) {

        this.bankService.add(addBankRequest);

    }

    @DeleteMapping()
    public void delete(@PathVariable Integer id) {

        this.bankService.delete(id);

    }

    @PutMapping()
    public void update(@RequestBody UpdateBankRequest updateBankRequest) {

        this.bankService.update(updateBankRequest);

    }

    @GetMapping()
    public List<GetAllBanksResponse> getAll() {

        return this.bankService.getAll();

    }

    @GetMapping("/{id}")
    public GetByIdBankResponse getById(@PathVariable Integer id) {

        return this.bankService.getById(id);

    }

    @PostMapping("/requestBank")
    public BankDepositResponse bankDepositResponse(@RequestBody BankDepositeRequest bankDepositeRequest) {
        return this.bankService.bankDepositResponse(bankDepositeRequest);
    }

}
