package com.pl.prem_league_data.PlayerEntity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "players",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_player_name_position", columnNames = {"name", "position"})
        }
)
public class Player {

    //--------------- Identifiers ---------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long externalId;

    //--------------- Personal Info ---------------
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING) // stores enum as string for readability and flexibility
    @Column(nullable = false)
    private Position position;

    // -------------- Stats --------------
    private Integer minutes;
    private Integer goals;
    private Integer assists;
    private Integer totalPoints;

    // -------------- Performance Metrics -----------
    @Column(precision = 5, scale = 2)
    private BigDecimal price;

    public Player() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
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

    public Player(String name, Position position,
                  Integer minutes, Integer goals, Integer assists,
                  Integer totalPoints, BigDecimal price) {
        this.name = name;
        this.position = position;
        this.minutes = minutes;
        this.goals = goals;
        this.assists = assists;
        this.totalPoints = totalPoints;
        this.price = price;
    }
}
