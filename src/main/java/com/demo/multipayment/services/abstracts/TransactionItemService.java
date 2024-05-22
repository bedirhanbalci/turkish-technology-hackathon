package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.entities.concretes.Bank;
import com.demo.multipayment.entities.concretes.TransactionItem;

public interface TransactionItemService {
    TransactionItem findTransactionItem(String transactionItem);
}
