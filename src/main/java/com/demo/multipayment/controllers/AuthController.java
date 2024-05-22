package com.demo.multipayment.controllers;

import com.demo.multipayment.services.abstracts.AuthService;
import com.demo.multipayment.services.dtos.auth.requests.LoginRequest;
import com.demo.multipayment.services.dtos.auth.requests.RegisterRequest;
import com.demo.multipayment.services.dtos.auth.responses.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
