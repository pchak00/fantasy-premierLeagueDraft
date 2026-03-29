package com.pl.prem_league_data.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DraftTeamSummaryDto {
    private Long id;
    @NotBlank
    private String name;
    @PositiveOrZero
    private BigDecimal budget;
    private List<PlayerSummaryDto> players = new ArrayList<>();
    @PositiveOrZero
    private int totalPlayers;

    public void addPlayerDto(PlayerSummaryDto player) {
        this.players.add(player);
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

    public List<PlayerSummaryDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerSummaryDto> players) {
        this.players = players;
    }

    public DraftTeamSummaryDto() {
    }

    @Override
    public String toString() {
        return "DraftTeamSummaryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", players=" + players +
                ", totalPlayers=" + totalPlayers +
                '}';
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public DraftTeamSummaryDto(Long id, String name, BigDecimal budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.players = players;
        this.totalPlayers = totalPlayers;
    }
}
