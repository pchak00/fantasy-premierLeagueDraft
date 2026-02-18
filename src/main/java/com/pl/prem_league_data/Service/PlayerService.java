package com.pl.prem_league_data.Service;

import com.pl.prem_league_data.PlayerEntity.Player;
import com.pl.prem_league_data.Repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    PlayerRepository playerRepository;
    PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    public List<Player> getAllPlayersSortedByGoals(){
        return playerRepository.sortByGoalsDesc();
    }
}
