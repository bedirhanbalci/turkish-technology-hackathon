package com.demo.multipayment.entities.concretes;

import com.demo.multipayment.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "transactionItems")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItem extends BaseEntity {

    @Column(name = "transaction_uuid")
    private String transactionUUid;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "checkout_id")
    private Integer checkoutId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
