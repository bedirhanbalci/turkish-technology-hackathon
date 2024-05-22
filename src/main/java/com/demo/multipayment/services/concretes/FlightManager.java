package com.demo.multipayment.services.concretes;

import com.demo.multipayment.core.utilities.mappers.ModelMapperService;
import com.demo.multipayment.entities.concretes.Flight;
import com.demo.multipayment.repositories.FlightRepository;
import com.demo.multipayment.services.abstracts.FlightService;
import com.demo.multipayment.services.dtos.flight.requests.AddFlightRequest;
import com.demo.multipayment.services.dtos.flight.requests.UpdateFlightRequest;
import com.demo.multipayment.services.dtos.flight.responses.GetAllFlightsResponse;
import com.demo.multipayment.services.dtos.flight.responses.GetByIdFlightResponse;
import com.demo.multipayment.services.rules.FlightBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightManager implements FlightService {

    private final FlightRepository flightRepository;

    private final ModelMapperService modelMapperService;

    private final FlightBusinessRules flightBusinessRules;


    @Override
    public void add(AddFlightRequest addFlightRequest) {

        Flight flight = this.modelMapperService.forRequest()
                .map(addFlightRequest, Flight.class);
        flight.setId(null);

        this.flightRepository.save(flight);

    }

    @Override
    public void delete(Integer id) {

        this.flightBusinessRules.checkIfFlightIdExists(id);

        Flight flight = this.modelMapperService.forRequest()
                .map(id, Flight.class);

        this.flightRepository.delete(flight);

    }

    @Override
    public void update(UpdateFlightRequest updateFlightRequest) {

        this.flightBusinessRules.checkIfFlightIdExists(updateFlightRequest.getId());

        Flight flight = this.modelMapperService.forRequest()
                .map(updateFlightRequest, Flight.class);

        this.flightRepository.save(flight);

    }

    @Override
    public GetByIdFlightResponse getById(Integer id) {

        this.flightBusinessRules.checkIfFlightIdExists(id);

        GetByIdFlightResponse response = this.modelMapperService.forResponse()
                .map(flightRepository.findById(id), GetByIdFlightResponse.class);

        return response;

    }

    @Override
    public List<GetAllFlightsResponse> getAll() {

        List<Flight> flights = flightRepository.findAll();

        List<GetAllFlightsResponse> flightsResponse = flights.stream()
                .map(flight -> this.modelMapperService.forResponse().map(flight, GetAllFlightsResponse.class)).toList();

        return flightsResponse;

    }
}
