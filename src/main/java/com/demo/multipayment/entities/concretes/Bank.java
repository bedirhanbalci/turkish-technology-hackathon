package com.demo.multipayment.entities.concretes;

import com.demo.multipayment.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "banks")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bank extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "card_prefix")
    private String cardPrefix;

    @OneToMany(mappedBy = "bank")
    private List<Account> accounts;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BankStatus status;

    @OneToMany(mappedBy = "bank")
    private List<TransactionItem> transactionItems;

}
