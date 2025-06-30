package com.apigateway.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_" + user.getRole().name()) );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can add logic based on your app needs
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Add real checks if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Add expiration logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Check for enabled flag if you have one
    }

    public User getUser() {
        return user;
    }
}
