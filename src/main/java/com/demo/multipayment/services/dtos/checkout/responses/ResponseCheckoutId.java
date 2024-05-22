package com.demo.multipayment.services.dtos.checkout.responses;

import com.demo.multipayment.entities.concretes.CheckoutStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseCheckoutId {

    private Integer checkoutId;
    private CheckoutStatus checkoutStatus;
}
