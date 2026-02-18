package com.pl.prem_league_data.Repository;

import com.pl.prem_league_data.PlayerEntity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
