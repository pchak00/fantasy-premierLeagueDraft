package com.pl.prem_league_data.Service;

import com.pl.prem_league_data.Entity.Player;
import com.pl.prem_league_data.Entity.Position;
import com.pl.prem_league_data.Repository.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PlayerService {
    PlayerRepository playerRepository;
    PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Page<Player> searchRepository(String name, Position position, Pageable pageable) {
        if(position != null) {
            return playerRepository.findByPosition(position, pageable);
        } else if(name != null) {
            return playerRepository.findByNameContainingIgnoreCase(name, pageable);
        }
        else
            return playerRepository.findAll(pageable);
    }
}
