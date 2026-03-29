package com.pl.prem_league_data.Exceptions;

import java.util.Map;

public record ExceptionResponse(int status,
                                String message,
                                long timestamp,
                                String path,
                                Map<String, String> errors) {

    public ExceptionResponse(int status, String message, String path) {
        this(status, message, System.currentTimeMillis(), path, null);
    }

}
