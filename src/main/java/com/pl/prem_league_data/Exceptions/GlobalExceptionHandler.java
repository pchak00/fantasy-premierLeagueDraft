package com.pl.prem_league_data.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handle 404 - Not Found
    @ExceptionHandler({PlayerNotFoundException.class, TeamNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request, null);
    }

    // Handle 409 - Conflict
    @ExceptionHandler(DuplicatePlayerException.class)
    public ResponseEntity<ExceptionResponse> handleConflict(RuntimeException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request, null);
    }

    // Handle 400 - Bad Request
    @ExceptionHandler({
            TeamCapacityOutOfBoundException.class,
            TeamPositionOutOfBoundException.class,
            TeamBudgetOutOfBoundException.class,
            PlayerNotInTeamException.class
    })
    public ResponseEntity<ExceptionResponse> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        // Extract each field name and its corresponding error message
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ExceptionResponse errorResponse = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                System.currentTimeMillis(),
                request.getRequestURI(),
                errors
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ExceptionResponse> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        // ConstraintViolationException has a slightly different structure than MethodArgumentNotValidException
        ex.getConstraintViolations().forEach(violation -> {
            // This extracts the parameter name (e.g., "id") and the message
            String propertyName = violation.getPropertyPath().toString();
            // Often path variables look like "getPlayer.id", so we take the last part
            String fieldName = propertyName.substring(propertyName.lastIndexOf('.') + 1);
            errors.put(fieldName, violation.getMessage());
        });

        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid path or query parameter", request, errors);
    }

    private ResponseEntity<ExceptionResponse> buildResponse(
            HttpStatus status,
            String message,
            HttpServletRequest request,
            Map<String, String> errors) {

        ExceptionResponse error = new ExceptionResponse(
                status.value(),
                message,
                System.currentTimeMillis(),
                request.getRequestURI(),
                errors
        );
        return new ResponseEntity<>(error, status);
    }
}
