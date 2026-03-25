package com.pl.prem_league_data.Exceptions;

public class DuplicatePlayerException extends RuntimeException {
    public DuplicatePlayerException(String message) {
        super(message);
    }
}
