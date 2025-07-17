package com.authService.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/university")
public class UserLogoutController {

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( "accessToken".equals( cookie.getName() ) ) {
                    Cookie deleteAccessToken = new Cookie("accessToken", null);
                    deleteAccessToken.setMaxAge(0);
                    deleteAccessToken.setPath("/");
                    deleteAccessToken.setHttpOnly(true);
                    deleteAccessToken.setSecure(true);
                    response.addCookie(deleteAccessToken);
                }
                if ( "refreshToken".equals( cookie.getName() ) ) {
                    Cookie deleteRefreshToken = new Cookie( "refreshToken", null);
                    deleteRefreshToken.setMaxAge(0);
                    deleteRefreshToken.setPath("/");
                    deleteRefreshToken.setHttpOnly(true);
                    deleteRefreshToken.setSecure(true);
                    response.addCookie(deleteRefreshToken);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully!");
    }

}
