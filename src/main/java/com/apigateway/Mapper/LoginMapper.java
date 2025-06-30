package com.apigateway.Mapper;

import com.apigateway.Entity.LoginResponse;
import com.apigateway.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
