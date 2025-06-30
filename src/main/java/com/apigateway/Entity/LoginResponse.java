package com.apigateway.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String username;
    private String email;
    private UserRole role;
    private String refreshToken;
    private String accessToken;

}
