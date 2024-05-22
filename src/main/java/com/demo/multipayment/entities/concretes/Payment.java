package com.demo.multipayment.entities.concretes;

import com.demo.multipayment.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "payments")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends BaseEntity {

    @Column(name = "amount")
    private Float amount;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "card_prefix")
    private String cardPrefix;

    @Column(name = "transaction_id")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "checkout_id")
    private Checkout checkout;

}
