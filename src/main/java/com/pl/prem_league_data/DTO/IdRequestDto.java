package com.pl.prem_league_data.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PositiveOrZero;

public class IdRequestDto {
    @JsonProperty("id")
    @PositiveOrZero(message = "Id must be a positive number or zero")
    private Long id;

    @Override
    public String toString() {
        return "IdResponseDto{" +
                "id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IdRequestDto() {
    }

    public IdRequestDto(Long id) {
        this.id = id;
    }
}
