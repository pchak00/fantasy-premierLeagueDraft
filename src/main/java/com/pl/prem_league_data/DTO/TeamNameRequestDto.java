package com.pl.prem_league_data.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamNameRequestDto {
    @JsonProperty("teamName")
    private String teamName;

    public TeamNameRequestDto(String teamName) {
        this.teamName = teamName;
    }

    public TeamNameRequestDto() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
