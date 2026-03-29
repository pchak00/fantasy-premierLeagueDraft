package com.pl.prem_league_data.DTO;

import com.pl.prem_league_data.Entity.Position;
import jakarta.validation.constraints.NotBlank;

public class PlayerSummaryDto {
    private Long id;
    @NotBlank
    private String name;
    private Position position;

    public PlayerSummaryDto(Long id, String name, Position position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public PlayerSummaryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
