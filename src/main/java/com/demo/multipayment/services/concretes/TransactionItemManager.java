package com.demo.multipayment.services.concretes;

import com.demo.multipayment.entities.concretes.TransactionItem;
import com.demo.multipayment.repositories.TransactionItemRepository;
import com.demo.multipayment.services.abstracts.TransactionItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionItemManager implements TransactionItemService {

    private final TransactionItemRepository transactionItemRepository;

    @Override
    public TransactionItem findTransactionItem(String transactionItem) {

        return transactionItemRepository.findByTransactionUUid(transactionItem);

    }
}
