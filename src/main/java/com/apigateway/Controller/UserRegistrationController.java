package com.apigateway.Controller;

import com.apigateway.Entity.User;
import com.apigateway.Entity.UserRequest;
import com.apigateway.Entity.UserResponse;
import com.apigateway.Mapper.UserMapper;
import com.apigateway.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/registerUser") // all can access
    public ResponseEntity<UserResponse> registerNewUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.convertRequestToUser( userRequest ); // saved user
        User savedUser = userService.regsisterUser( user ); // returned user
        UserResponse userResponse = userMapper.convertUserToResponse( savedUser );
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/getUserById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> findAUserByUserId(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

}
