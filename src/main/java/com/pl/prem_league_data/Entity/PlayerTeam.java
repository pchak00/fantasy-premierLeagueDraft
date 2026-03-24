package com.pl.prem_league_data.Entity;

import jakarta.persistence.*;

@Entity
public class PlayerTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public DraftTeam getTeam() {
        return team;
    }

    public void setTeam(DraftTeam team) {
        this.team = team;
    }

    public PlayerTeam() {
    }

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private DraftTeam team;
}
