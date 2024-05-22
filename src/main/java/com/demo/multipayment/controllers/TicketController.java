package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.TicketService;
import com.demo.multipayment.services.dtos.ticket.requests.AddTicketRequest;
import com.demo.multipayment.services.dtos.ticket.requests.UpdateTicketRequest;
import com.demo.multipayment.services.dtos.ticket.responses.GetAllTicketsResponse;
import com.demo.multipayment.services.dtos.ticket.responses.GetByIdTicketResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
@CrossOrigin
public class TicketController {

    private final TicketService ticketService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddTicketRequest addTicketRequest) {

        this.ticketService.add(addTicketRequest);

    }

    @DeleteMapping()
    public void delete(@PathVariable Integer id) {

        this.ticketService.delete(id);

    }

    @PutMapping()
    public void update(@RequestBody UpdateTicketRequest updateTicketRequest) {

        this.ticketService.update(updateTicketRequest);

    }

    @GetMapping()
    public List<GetAllTicketsResponse> getAll() {

        return this.ticketService.getAll();

    }

    @GetMapping("/{id}")
    public GetByIdTicketResponse getById(@PathVariable Integer id) {

        return this.ticketService.getById(id);

    }

}
