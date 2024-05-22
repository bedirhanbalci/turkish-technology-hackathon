package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.services.dtos.flight.requests.AddFlightRequest;
import com.demo.multipayment.services.dtos.flight.requests.UpdateFlightRequest;
import com.demo.multipayment.services.dtos.flight.responses.GetAllFlightsResponse;
import com.demo.multipayment.services.dtos.flight.responses.GetByIdFlightResponse;

import java.util.List;

public interface FlightService {

    void add(AddFlightRequest addFlightRequest);

    void delete(Integer id);

    void update(UpdateFlightRequest updateFlightRequest);

    GetByIdFlightResponse getById(Integer id);

    List<GetAllFlightsResponse> getAll();

}
