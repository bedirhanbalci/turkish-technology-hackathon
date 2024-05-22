package com.demo.multipayment.repositories;

import com.demo.multipayment.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
