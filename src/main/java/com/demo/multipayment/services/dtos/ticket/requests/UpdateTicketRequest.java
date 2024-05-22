package com.demo.multipayment.services.dtos.ticket.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketRequest {

    private Integer id;

    private Float amountPaid;

    private Integer userId;

    private Integer flightId;

}
