package com.pl.prem_league_data.Exceptions;

public record ExceptionResponse(int status,
                                String message,
                                long timestamp,
                                String path) {
}
