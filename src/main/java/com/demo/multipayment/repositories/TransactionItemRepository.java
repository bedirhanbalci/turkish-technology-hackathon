package com.demo.multipayment.repositories;

import com.demo.multipayment.entities.concretes.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Integer> {

    TransactionItem findByTransactionUUid(String transactionUUid);

}
