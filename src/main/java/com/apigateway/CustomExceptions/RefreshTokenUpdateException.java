package com.apigateway.CustomExceptions;

public class RefreshTokenUpdateException extends RuntimeException {
    public RefreshTokenUpdateException(String message) {
        super(message);
    }
}
