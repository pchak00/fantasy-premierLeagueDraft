package com.pl.prem_league_data.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Entity
public class DraftTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Team name cannot be blank")
    @Size(max = 20, message = "Team name cannot exceed 20 characters")
    private String name;
    @PositiveOrZero
    private BigDecimal budget;

    public DraftTeam(Long id, String name, BigDecimal budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    public DraftTeam() {
    }
    @OneToMany(mappedBy = "team")
    List<PlayerTeam> playerTeams = new ArrayList<>();

    public List<PlayerTeam> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(List<PlayerTeam> playerTeams) {
        this.playerTeams = playerTeams;
    }

    @Override
    public String toString() {
        return "DraftTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                '}';
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

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    // helper method to check if the team has reached the position cap for a given position
    public Boolean positionCap(Position position, int size) {
        if(position == Position.GK && size < 2) return true;
        if(position == Position.DEF && size < 5) return true;
        if(position == Position.MID && size < 5) return true;
        if(position == Position.FWD && size < 3) return true;
        return false;
    }

    public void removePlayerTeam(PlayerTeam playerTeam) {
        playerTeams.remove(playerTeam);
    }
    public void addPlayerTeam(PlayerTeam playerTeam) {
        playerTeams.add(playerTeam);
    }
}
