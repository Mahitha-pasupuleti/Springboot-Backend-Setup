package com.apigateway.Controller;

import com.apigateway.CustomExceptions.IncorrectPasswordException;
import com.apigateway.Entity.JwtService;
import com.apigateway.Entity.LoginRequest;
import com.apigateway.Entity.LoginResponse;
import com.apigateway.Entity.User;
import com.apigateway.Mapper.LoginMapper;
import com.apigateway.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class UserLoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> loginExistingUser(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        return refreshTokens(request, response, loginRequest);
    }

    @PostMapping("/refreshTokens")
    public ResponseEntity<LoginResponse> refreshTokens(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        User user = userService.findUserByUsername( loginRequest.getUsername() );

        if ( !passwordEncoder.matches( loginRequest.getPassword(), user.getPassword() ) ) {
            throw new IncorrectPasswordException("Incorrect Password. Please try again!");
        }

        String refreshToken = jwtService.generateRefreshToken( user.getUsername(), user.getEmail() );
        String accessToken = jwtService.generateAccessToken( user.getUsername(), user.getEmail() );

        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(15 * 60);

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        User updatedUser = userService.updateRefreshToken( user.getUsername(), refreshToken );

        LoginResponse loginResponse = loginMapper.convertUserToLoginResponse( updatedUser, accessToken );
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }

}
