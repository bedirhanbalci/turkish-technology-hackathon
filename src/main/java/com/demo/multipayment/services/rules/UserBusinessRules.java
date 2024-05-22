package com.demo.multipayment.services.rules;

import com.demo.multipayment.core.utilities.exceptions.BusinessException;
import com.demo.multipayment.repositories.UserRepository;
import com.demo.multipayment.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void checkIfUserByEmailExists(String email) {
        if (this.userRepository.existsByEmail(email)) {
            throw new BusinessException(Messages.EMAIL_ALREADY_EXISTS);
        }
    }

    public void checkIfUserByIdExists(Integer id) {
        if (!this.userRepository.existsById(id)) {
            throw new BusinessException(Messages.ID_NOT_FOUND);
        }
    }

}