package com.demo.multipayment.services.concretes;

import com.demo.multipayment.core.utilities.mappers.ModelMapperService;
import com.demo.multipayment.entities.concretes.Ticket;
import com.demo.multipayment.repositories.TicketRepository;
import com.demo.multipayment.services.abstracts.TicketService;
import com.demo.multipayment.services.dtos.ticket.requests.AddTicketRequest;
import com.demo.multipayment.services.dtos.ticket.requests.UpdateTicketRequest;
import com.demo.multipayment.services.dtos.ticket.responses.GetAllTicketsResponse;
import com.demo.multipayment.services.dtos.ticket.responses.GetByIdTicketResponse;
import com.demo.multipayment.services.rules.TicketBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketManager implements TicketService {

    private final TicketRepository ticketRepository;

    private final ModelMapperService modelMapperService;

    private final TicketBusinessRules ticketBusinessRules;

    @Override
    public void add(AddTicketRequest addTicketRequest) {

        Ticket ticket = this.modelMapperService.forRequest()
                .map(addTicketRequest, Ticket.class);
        ticket.setId(null);

        this.ticketRepository.save(ticket);

    }

    @Override
    public void delete(Integer id) {

        this.ticketBusinessRules.checkIfTicketIdExists(id);

        Ticket ticket = this.modelMapperService.forRequest()
                .map(id, Ticket.class);

        this.ticketRepository.delete(ticket);

    }

    @Override
    public void update(UpdateTicketRequest updateTicketRequest) {

        this.ticketBusinessRules.checkIfTicketIdExists(updateTicketRequest.getId());

        Ticket ticket = this.modelMapperService.forRequest()
                .map(updateTicketRequest, Ticket.class);

        this.ticketRepository.save(ticket);

    }

    @Override
    public GetByIdTicketResponse getById(Integer id) {

        this.ticketBusinessRules.checkIfTicketIdExists(id);

        GetByIdTicketResponse response = this.modelMapperService.forResponse()
                .map(ticketRepository.findById(id), GetByIdTicketResponse.class);

        return response;

    }

    @Override
    public List<GetAllTicketsResponse> getAll() {

        List<Ticket> tickets = ticketRepository.findAll();

        List<GetAllTicketsResponse> ticketsResponse = tickets.stream()
                .map(ticket -> this.modelMapperService.forResponse().map(ticket, GetAllTicketsResponse.class)).toList();

        return ticketsResponse;

    }
}
