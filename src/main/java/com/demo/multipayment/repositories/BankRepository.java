package com.demo.multipayment.repositories;

import com.demo.multipayment.entities.concretes.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {

    Bank findByCardPrefix(String cardPrefix);

}
