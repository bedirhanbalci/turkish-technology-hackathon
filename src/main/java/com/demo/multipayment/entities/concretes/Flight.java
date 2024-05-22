package com.demo.multipayment.entities.concretes;

import com.demo.multipayment.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table(name = "flights")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight extends BaseEntity {

    @Column(name = "number")
    private String number;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_time")
    private LocalDate departureTime;

    @Column(name = "arrival_time")
    private LocalDate arrivalTime;

    @Column(name = "price")
    private Float price;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "flight")
    private List<Checkout> checkouts;


}
