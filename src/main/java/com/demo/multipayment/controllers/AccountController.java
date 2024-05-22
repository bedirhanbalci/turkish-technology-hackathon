package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.AccountService;
import com.demo.multipayment.services.dtos.account.requests.AddAccountRequest;
import com.demo.multipayment.services.dtos.account.requests.UpdateAccountRequest;
import com.demo.multipayment.services.dtos.account.responses.GetAllAccountsResponse;
import com.demo.multipayment.services.dtos.account.responses.GetByIdAccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddAccountRequest addAccountRequest) {

        this.accountService.add(addAccountRequest);

    }

    @DeleteMapping()
    public void delete(@PathVariable Integer id) {

        this.accountService.delete(id);

    }

    @PutMapping()
    public void update(@RequestBody UpdateAccountRequest updateAccountRequest) {

        this.accountService.update(updateAccountRequest);

    }

    @GetMapping()
    public List<GetAllAccountsResponse> getAll() {

        return this.accountService.getAll();

    }

    @GetMapping("/{id}")
    public GetByIdAccountResponse getById(@PathVariable Integer id) {

        return this.accountService.getById(id);

    }

}
