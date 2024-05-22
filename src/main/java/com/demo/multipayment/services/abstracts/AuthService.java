package com.demo.multipayment.services.abstracts;

import com.demo.multipayment.services.dtos.auth.requests.LoginRequest;
import com.demo.multipayment.services.dtos.auth.requests.RegisterRequest;
import com.demo.multipayment.services.dtos.auth.responses.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

}
