package com.demo.multipayment.services.dtos.payment.responses;

import com.demo.multipayment.entities.concretes.CheckoutStatus;
import com.demo.multipayment.entities.concretes.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPaymentsResponse {

    private Integer id;

    private Float amount;

    private String bankName;

    private PaymentStatus status;

    private Float checkoutAmount;

}
