package com.pl.prem_league_data.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeamNameRequestDto {
    @JsonProperty("teamName")
    @NotBlank(message = "Team name must not be blank")
    @Size(max = 20, message = "Team name must not exceed 20 characters")
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
