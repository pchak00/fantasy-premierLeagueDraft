package com.pl.prem_league_data.Exceptions;

public class PlayerNotInTeamException extends RuntimeException {
    public PlayerNotInTeamException(String message) {
        super(message);
    }
}
