package com.authService.Controller;

import com.authService.Service.JwtService;
import com.authService.Entity.User;
import com.authService.Request.UserRequest;
import com.authService.Response.UserResponse;
import com.authService.Mapper.UserMapper;
import com.authService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/university")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/registerUser") // all can access
    public ResponseEntity<UserResponse> registerNewUser(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest.toString());
        User user = userMapper.convertRequestToUser( userRequest ); // saved user
        System.out.println(user.toString());
        User savedUser = userService.regsisterUser( user ); // returned user
        System.out.println(savedUser.toString());
        UserResponse userResponse = userMapper.convertUserToResponse( savedUser );
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/getUserById/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> findAUserByUserId(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    @GetMapping("/username")
    public ResponseEntity<UserResponse> getUserByUsername(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        String username = jwtService.extractUsername(token);
        User user = userService.findUserByUsername( username );
        UserResponse userResponse = userMapper.convertUserToResponse( user );
        return ResponseEntity.ok(userResponse);
    }

}
