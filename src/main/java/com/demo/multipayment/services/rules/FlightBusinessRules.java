package com.demo.multipayment.services.rules;

import com.demo.multipayment.core.utilities.exceptions.BusinessException;
import com.demo.multipayment.repositories.FlightRepository;
import com.demo.multipayment.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightBusinessRules {

    private final FlightRepository flightRepository;

    public void checkIfFlightIdExists(Integer id) {
        if (!this.flightRepository.existsById(id)) {
            throw new BusinessException(Messages.ID_NOT_FOUND);
        }
    }

}
