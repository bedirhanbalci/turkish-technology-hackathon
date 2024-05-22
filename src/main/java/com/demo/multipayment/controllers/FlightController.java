package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.FlightService;
import com.demo.multipayment.services.dtos.flight.requests.AddFlightRequest;
import com.demo.multipayment.services.dtos.flight.requests.UpdateFlightRequest;
import com.demo.multipayment.services.dtos.flight.responses.GetAllFlightsResponse;
import com.demo.multipayment.services.dtos.flight.responses.GetByIdFlightResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@AllArgsConstructor
@CrossOrigin
public class FlightController {

    private final FlightService flightService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody AddFlightRequest addFlightRequest) {

        this.flightService.add(addFlightRequest);

    }

    @DeleteMapping()
    public void delete(@PathVariable Integer id) {

        this.flightService.delete(id);

    }

    @PutMapping()
    public void update(@RequestBody UpdateFlightRequest updateFlightRequest) {

        this.flightService.update(updateFlightRequest);

    }

    @GetMapping()
    public List<GetAllFlightsResponse> getAll() {

        return this.flightService.getAll();

    }

    @GetMapping("/{id}")
    public GetByIdFlightResponse getById(@PathVariable Integer id) {

        return this.flightService.getById(id);

    }

}
