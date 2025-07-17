package com.authService.Service;

import com.authService.Entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    private static final String SECRET_KEY = "6e2ff9c92f41a22153133085b79a10f284ddaa364078ee1872b6d98babe9b7f3";

    // Generate Jwt Tokens

    public String generateAccessToken(Long userId, String username, String email, UserRole role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        claims.put("email", email);
        claims.put("role", role.toString());
        return createAccessToken(claims, username, 1000*60*15);
    }

    public String generateRefreshToken(Long userId, String username, String email, UserRole role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        claims.put("email", email);
        claims.put("role", role.toString());
        return createAccessToken(claims, username, 1000L*60*60*24*7);
    }

    public String createAccessToken(Map<String, Object> claims, String username, long expirationTimeInMs) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSignKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // Extract data from a given JWT Token

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, String claimName, Class<T> requiredType) {
        return extractAllClaims(token).get(claimName, requiredType);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims.SUBJECT, String.class);
    }

    public Long extractUserId(String token) {
        return extractClaim(token, "userId", Long.class);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims.EXPIRATION, Date.class);
    }

    // Token Validation
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
