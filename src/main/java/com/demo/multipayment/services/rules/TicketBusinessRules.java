package com.demo.multipayment.services.rules;

import com.demo.multipayment.core.utilities.exceptions.BusinessException;
import com.demo.multipayment.repositories.TicketRepository;
import com.demo.multipayment.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketBusinessRules {

    private final TicketRepository ticketRepository;

    public void checkIfTicketIdExists(Integer id) {
        if (!this.ticketRepository.existsById(id)) {
            throw new BusinessException(Messages.ID_NOT_FOUND);
        }
    }

}
