package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.entities.concretes.Checkout;
import com.demo.multipayment.services.dtos.bank.requests.BankDepositeRequest;
import com.demo.multipayment.services.dtos.checkout.requests.AddCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.requests.OdemeRequest;
import com.demo.multipayment.services.dtos.checkout.requests.UpdateCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.responses.GetAllCheckoutsResponse;
import com.demo.multipayment.services.dtos.checkout.responses.ResponseCheckoutId;

import java.util.List;

public interface CheckoutService {

    Checkout getById(Integer id);

    void saveByCheckout(Checkout checkout);

    void saveChekout(AddCheckoutRequest addCheckoutRequest);

    String depozitCagri(BankDepositeRequest bankDepositeRequest);

    void odeme(String transactionId);

    void odemeyitamamla(OdemeRequest odemeRequest);

    ResponseCheckoutId saveCheckout(AddCheckoutRequest addCheckoutRequest);

    void add(AddCheckoutRequest addCheckoutRequest);

    void delete(Integer id);

    void update(UpdateCheckoutRequest updateCheckoutRequest);

    List<GetAllCheckoutsResponse> getAll();

}
