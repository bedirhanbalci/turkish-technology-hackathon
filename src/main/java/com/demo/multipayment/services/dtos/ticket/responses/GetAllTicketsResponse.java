package com.demo.multipayment.services.dtos.ticket.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTicketsResponse {

    private Integer id;

    private Float amountPaid;

    private String userEmail;

    private String flightNumber;

}
