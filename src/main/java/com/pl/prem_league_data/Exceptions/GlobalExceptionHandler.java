package com.pl.prem_league_data.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handle 404 - Not Found
    @ExceptionHandler({PlayerNotFoundException.class, TeamNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    // Handle 409 - Conflict
    @ExceptionHandler(DuplicatePlayerException.class)
    public ResponseEntity<ExceptionResponse> handleConflict(RuntimeException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    // Handle 400 - Bad Request
    @ExceptionHandler({
            TeamCapacityOutOfBoundException.class,
            TeamPositionOutOfBoundException.class,
            TeamBudgetOutOfBoundException.class,
            PlayerNotInTeamException.class
    })
    public ResponseEntity<ExceptionResponse> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    // Helper method to keep things DRY
    private ResponseEntity<ExceptionResponse> buildResponse(HttpStatus status, String message, HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse(
                status.value(),
                message,
                System.currentTimeMillis(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, status);
    }
}
