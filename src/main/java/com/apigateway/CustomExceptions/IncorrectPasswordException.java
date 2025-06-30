package com.apigateway.CustomExceptions;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
