package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.entities.concretes.Checkout;
import com.demo.multipayment.services.dtos.checkout.requests.AddCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.requests.UpdateCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.responses.GetAllCheckoutsResponse;
import com.demo.multipayment.services.dtos.checkout.responses.ResponseCheckoutId;

import java.util.List;

public interface CheckoutService {

    Checkout getById(Integer id);

    void saveByCheckout(Checkout checkout);

    ResponseCheckoutId saveCheckout(AddCheckoutRequest addCheckoutRequest);

    void add(AddCheckoutRequest addCheckoutRequest);

    void delete(Integer id);

    void update(UpdateCheckoutRequest updateCheckoutRequest);

    List<GetAllCheckoutsResponse> getAll();

}
