package com.pl.prem_league_data.Controller;

import com.pl.prem_league_data.PlayerEntity.Player;
import com.pl.prem_league_data.Repository.PlayerRepository;
import com.pl.prem_league_data.Service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/players")
    List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/players/sortedByGoals")
    List<Player> getAllPlayersSortedByGoals(){
        return playerService.getAllPlayersSortedByGoals();
    }
}
