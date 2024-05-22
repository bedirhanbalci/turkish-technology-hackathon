package com.demo.multipayment.services.rules;

import com.demo.multipayment.core.utilities.exceptions.BusinessException;
import com.demo.multipayment.repositories.CheckoutRepository;
import com.demo.multipayment.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutBusinessRules {

    private final CheckoutRepository checkoutRepository;

    public void checkIfCheckoutIdExists(Integer id) {
        if (!this.checkoutRepository.existsById(id)) {
            throw new BusinessException(Messages.ID_NOT_FOUND);
        }
    }

}
