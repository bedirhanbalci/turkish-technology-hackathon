package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.CheckoutService;
import com.demo.multipayment.services.dtos.bank.requests.BankDepositeRequest;
import com.demo.multipayment.services.dtos.checkout.requests.AddCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.requests.OdemeRequest;
import com.demo.multipayment.services.dtos.checkout.requests.UpdateCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.responses.GetAllCheckoutsResponse;
import com.demo.multipayment.services.dtos.checkout.responses.ResponseCheckoutId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkouts")
@AllArgsConstructor
@CrossOrigin
public class CheckoutController {

    private CheckoutService checkoutService;

    @PostMapping("/save")
    public ResponseCheckoutId saveCheckout(@RequestBody AddCheckoutRequest addCheckoutRequest){
        return this.checkoutService.saveCheckout(addCheckoutRequest);
    }
    @PostMapping("/deposit")
    public String depositCall(@RequestBody BankDepositeRequest bankDepositeRequest) {
        return checkoutService.depozitCagri(bankDepositeRequest);
    }
    @PostMapping("/complete")
    public void completePayment(@RequestBody OdemeRequest odemeRequest) {
        checkoutService.odemeyitamamla(odemeRequest);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddCheckoutRequest addCheckoutRequest) {

        this.checkoutService.add(addCheckoutRequest);

    }

    @DeleteMapping()
    public void delete(@PathVariable Integer id) {

        this.checkoutService.delete(id);

    }

    @PutMapping()
    public void update(@RequestBody UpdateCheckoutRequest updateCheckoutRequest) {

        this.checkoutService.update(updateCheckoutRequest);

    }

    @GetMapping()
    public List<GetAllCheckoutsResponse> getAll() {

        return this.checkoutService.getAll();

    }

}