package com.demo.multipayment.services.dtos.flight.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdFlightResponse {

    private Integer id;

    private String number;

    private String origin;

    private String destination;

    private LocalDate departureTime;

    private LocalDate arrivalTime;

    private Float price;

}
