package com.demo.multipayment.entities.concretes;

import com.demo.multipayment.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Table(name = "transactionItems")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionItem extends BaseEntity {

    private String transactionUUid;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Float amount;

    private Integer checkoutId;

    private String bankName;

    private String accountNumber;
}
