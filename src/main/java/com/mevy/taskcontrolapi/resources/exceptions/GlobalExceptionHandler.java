package com.mevy.taskcontrolapi.resources.exceptions;

import java.io.IOException;
import java.time.Instant;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mevy.taskcontrolapi.services.exceptions.DatabaseIntegrityException;
import com.mevy.taskcontrolapi.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements AuthenticationFailureHandler {
    
    @Value("${server.error.include-exception}")
    private boolean printStackTrace;

    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
                
                ErrorResponse errorResponse = new ErrorResponse(
                    Instant.now(),
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    "Validation Error",
                    "Validation error. Check 'errors' field for details.");
                    
                    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
                        errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
                    }
                    
                    return ResponseEntity.unprocessableEntity().body(errorResponse);
                }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaugthExceptions(Exception exception, WebRequest request) {
        return buildErrorResponse(
            exception,
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error"
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {
        return buildErrorResponse(
            exception,
            HttpStatus.NOT_FOUND,
            "Resource not found"
        );
    }

    @ExceptionHandler(DatabaseIntegrityException.class)
    public ResponseEntity<Object> handleDatabaseIntegrityException(
            DatabaseIntegrityException exception,
            WebRequest webRequest
    ) {
        return null;
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            HttpStatus status,
            String title,
            String message
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                status.value(),
                title,
                message
            );
        if (printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            HttpStatus status,
            String title
    ) {
        return buildErrorResponse(exception, status, title, exception.getMessage());
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Deu não");
    }

}
