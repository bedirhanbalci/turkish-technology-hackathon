package com.demo.multipayment.services.rules;

import com.demo.multipayment.core.utilities.exceptions.BusinessException;
import com.demo.multipayment.repositories.PaymentRepository;
import com.demo.multipayment.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {

    private final PaymentRepository paymentRepository;

    public void checkIfPaymentIdExists(Integer id) {
        if (!this.paymentRepository.existsById(id)) {
            throw new BusinessException(Messages.ID_NOT_FOUND);
        }
    }

}
