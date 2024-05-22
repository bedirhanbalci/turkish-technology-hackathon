package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.CheckoutService;
import com.demo.multipayment.services.dtos.bank.requests.BankDepositeRequest;
import com.demo.multipayment.services.dtos.checkout.requests.AddCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.requests.OdemeRequest;
import com.demo.multipayment.services.dtos.checkout.responses.ResponseCheckoutId;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@AllArgsConstructor
@CrossOrigin
public class CheckoutController {

    @Autowired
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

}