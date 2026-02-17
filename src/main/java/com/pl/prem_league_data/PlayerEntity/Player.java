package com.pl.prem_league_data.PlayerEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "players",
        indexes = {
                @Index(name = "idx_players_team", columnList = "team"),
                @Index(name = "idx_players_position", columnList = "position"),
                @Index(name = "idx_players_name", columnList = "name")
        } // optimizes queries filtering by team, position, or name
)
public class Player {

    //--------------- Identifiers ---------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer externalId;

    //--------------- Personal Info ---------------
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING) // stores enum as string for readability and flexibility
    @Column(nullable = false)
    private Position position;
    @Column(nullable = false)
    private String team;
    private String nationality;

    // -------------- Stats --------------
    private Integer minutes;
    private Integer goals;
    private Integer assists;
    private Integer totalPoints;

    // -------------- Performance Metrics -----------
    @Column(precision = 5, scale = 2)
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getXg() {
        return xg;
    }

    public void setXg(BigDecimal xg) {
        this.xg = xg;
    }

    public BigDecimal getxA() {
        return xA;
    }

    public void setxA(BigDecimal xA) {
        this.xA = xA;
    }

    public Player(Integer id, Integer externalId, String name, Position position, String team, String nationality, Integer minutes, Integer goals, Integer assists, Integer totalPoints, BigDecimal price, BigDecimal xg, BigDecimal xA) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.position = position;
        this.team = team;
        this.nationality = nationality;
        this.minutes = minutes;
        this.goals = goals;
        this.assists = assists;
        this.totalPoints = totalPoints;
        this.price = price;
        this.xg = xg;
        this.xA = xA;
    }

    public Player() {
    }

    @Column(precision = 5, scale = 2)
    private BigDecimal xg;
    @Column(precision = 5, scale = 2)
    private BigDecimal xA;
}
