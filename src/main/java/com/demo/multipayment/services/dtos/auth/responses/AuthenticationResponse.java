package com.demo.multipayment.services.dtos.auth.responses;

import com.demo.multipayment.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Integer id;

    private String accessToken;

    private String refreshToken;

    private User user;

}
