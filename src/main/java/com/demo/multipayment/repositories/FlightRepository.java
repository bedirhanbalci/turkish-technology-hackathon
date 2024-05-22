package com.demo.multipayment.repositories;

import com.demo.multipayment.entities.concretes.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
