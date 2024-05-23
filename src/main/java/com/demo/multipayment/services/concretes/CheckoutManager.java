package com.demo.multipayment.services.concretes;

import com.demo.multipayment.core.utilities.mappers.ModelMapperService;
import com.demo.multipayment.entities.concretes.*;
import com.demo.multipayment.repositories.*;
import com.demo.multipayment.services.abstracts.CheckoutService;
import com.demo.multipayment.services.dtos.checkout.requests.AddCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.requests.UpdateCheckoutRequest;
import com.demo.multipayment.services.dtos.checkout.responses.GetAllCheckoutsResponse;
import com.demo.multipayment.services.dtos.checkout.responses.ResponseCheckoutId;
import com.demo.multipayment.services.rules.CheckoutBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CheckoutManager implements CheckoutService {

    private final CheckoutRepository checkoutRepository;

    private final UserRepository userRepository;

    private final ModelMapperService modelMapperService;

    private final CheckoutBusinessRules checkoutBusinessRules;

    @Override
    public Checkout getById(Integer id) {

        return checkoutRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Checkout with id " + id + " not found"));

    }

    @Override
    public void add(AddCheckoutRequest addCheckoutRequest) {

        Checkout checkout = this.modelMapperService.forRequest()
                .map(addCheckoutRequest, Checkout.class);
        checkout.setId(null);

        this.checkoutRepository.save(checkout);

    }

    @Override
    public void delete(Integer id) {

        this.checkoutBusinessRules.checkIfCheckoutIdExists(id);

        Checkout checkout = this.modelMapperService.forRequest()
                .map(id, Checkout.class);

        this.checkoutRepository.delete(checkout);

    }

    @Override
    public void update(UpdateCheckoutRequest updateCheckoutRequest) {

        this.checkoutBusinessRules.checkIfCheckoutIdExists(updateCheckoutRequest.getId());

        Checkout checkout = this.modelMapperService.forRequest()
                .map(updateCheckoutRequest, Checkout.class);

        this.checkoutRepository.save(checkout);

    }

    @Override
    public List<GetAllCheckoutsResponse> getAll() {

        List<Checkout> checkouts = checkoutRepository.findAll();

        List<GetAllCheckoutsResponse> checkoutsResponse = checkouts.stream()
                .map(checkout -> this.modelMapperService.forResponse().map(checkout, GetAllCheckoutsResponse.class)).toList();

        return checkoutsResponse;

    }

    @Override
    public void saveByCheckout(Checkout checkout) {

        this.checkoutRepository.save(checkout);

    }

    @Override
    public ResponseCheckoutId saveCheckout(AddCheckoutRequest addCheckoutRequest) {

        Checkout checkout = new Checkout();
        User user = userRepository.getReferenceById(addCheckoutRequest.getUserId());
        checkout.setStatus(CheckoutStatus.INITIALIZE);
        checkout.setPaymentMethod(PaymentMethod.MULTIPLE_PAYMENTS);
        checkout.setUser(user);
        checkoutRepository.save(checkout);
        return ResponseCheckoutId.builder()
                .checkoutId(checkout.getId())
                .checkoutStatus(checkout.getStatus())
                .build();

    }

}
