package com.authService.Mapper;

import com.authService.Entity.User;
import com.authService.Request.UserRequest;
import com.authService.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User convertRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setUsername( userRequest.getUsername() );
        String encryptedPassword = passwordEncoder.encode( userRequest.getPassword() );
        user.setPassword( encryptedPassword );
        user.setEmail( userRequest.getEmail() );
        user.setRole( userRequest.getRole() );
        return user;
    }

    public UserResponse convertUserToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setRole( user.getRole() );
        userResponse.setMessage( "User registration successful!" );
        return userResponse;
    }

}
