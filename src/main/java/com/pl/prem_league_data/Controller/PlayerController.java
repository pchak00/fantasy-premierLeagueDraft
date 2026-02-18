package com.pl.prem_league_data.Controller;

import com.pl.prem_league_data.PlayerEntity.Player;
import com.pl.prem_league_data.PlayerEntity.Position;
import com.pl.prem_league_data.Service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    PlayerService playerService;

    PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/players")
    Page<Player> getAllPlayers(@RequestParam(required = false) String name,
                               @RequestParam(required = false) Position position,
                               @PageableDefault(
                                       page = 0,
                                       size = 20,
                                       sort = "totalPoints",
                                       direction = Sort.Direction.DESC
                               )Pageable pageable) {
       // Pageable pageable = PageRequest.of(page, size);
        return playerService.searchRepository(name, position, pageable);
    }

}
