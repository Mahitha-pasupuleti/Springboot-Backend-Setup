package com.authService.Mapper;

import com.authService.Response.LoginResponse;
import com.authService.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public LoginResponse convertUserToLoginResponse(User user, String accessToken) {
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setUsername( user.getUsername() );
        loginResponse.setEmail( user.getEmail() );
        loginResponse.setRole( user.getRole() );
        loginResponse.setRefreshToken( user.getRefreshToken() );
        loginResponse.setAccessToken(accessToken);

        return loginResponse;
    }
}
