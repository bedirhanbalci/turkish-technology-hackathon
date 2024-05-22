package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.services.dtos.ticket.requests.AddTicketRequest;
import com.demo.multipayment.services.dtos.ticket.requests.UpdateTicketRequest;
import com.demo.multipayment.services.dtos.ticket.responses.GetAllTicketsResponse;
import com.demo.multipayment.services.dtos.ticket.responses.GetByIdTicketResponse;

import java.util.List;

public interface TicketService {

    void add(AddTicketRequest addTicketRequest);

    void delete(Integer id);

    void update(UpdateTicketRequest updateTicketRequest);

    GetByIdTicketResponse getById(Integer id);

    List<GetAllTicketsResponse> getAll();

}
