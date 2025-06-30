package com.apigateway.CustomExceptions;

public class InvalidAuthTokenException extends RuntimeException{
    public InvalidAuthTokenException(String message) {
        super(message);
    }
}
