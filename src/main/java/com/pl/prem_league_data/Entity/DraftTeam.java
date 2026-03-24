package com.pl.prem_league_data.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class DraftTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double budget;

    public DraftTeam(Long id, String name, double budget) {
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
