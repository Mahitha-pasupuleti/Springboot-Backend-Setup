package com.apigateway.ExceptionHandler;

import com.apigateway.CustomExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(RefreshTokenUpdateException.class)
    public ResponseEntity<String> handleRefreshTokenUpdateFailed(RefreshTokenUpdateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidAuthTokenException.class)
    public ResponseEntity<String> handleInvalidToken(InvalidAuthTokenException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handleIncorrectPassword(IncorrectPasswordException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGenericException(RuntimeException ex) {
        StringBuilder message = new StringBuilder( "Something went wrong: " + ex.getMessage() );

        Throwable cause = ex.getCause();
        if ( cause != null ) {
            message.append( " | Cause: " + cause.getMessage() );
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message.toString());
    }

}
