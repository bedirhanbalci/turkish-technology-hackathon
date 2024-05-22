package com.demo.multipayment.repositories;

import com.demo.multipayment.entities.concretes.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

}
