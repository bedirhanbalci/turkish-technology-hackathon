package com.demo.multipayment.repositories;

import com.demo.multipayment.entities.concretes.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
}
