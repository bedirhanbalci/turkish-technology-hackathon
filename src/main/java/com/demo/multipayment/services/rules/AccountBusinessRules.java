package com.demo.multipayment.services.rules;

import com.demo.multipayment.core.utilities.exceptions.BusinessException;
import com.demo.multipayment.repositories.AccountRepository;
import com.demo.multipayment.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountBusinessRules {

    private final AccountRepository accountRepository;

    public void checkIfAccountIdExists(Integer id) {
        if (!this.accountRepository.existsById(id)) {
            throw new BusinessException(Messages.ID_NOT_FOUND);
        }
    }

}
