package com.authService.CustomExceptions;

public class InvalidAuthTokenException extends RuntimeException{
    public InvalidAuthTokenException(String message) {
        super(message);
    }
}
