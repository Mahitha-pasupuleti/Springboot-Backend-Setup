package com.authService.CustomExceptions;

public class RefreshTokenUpdateException extends RuntimeException {
    public RefreshTokenUpdateException(String message) {
        super(message);
    }
}
