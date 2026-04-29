package com.pl.prem_league_data.Service;

import com.pl.prem_league_data.Entity.Player;
import com.pl.prem_league_data.Entity.Position;
import com.pl.prem_league_data.Repository.PlayerRepository;
import com.pl.prem_league_data.Repository.PlayerSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class PlayerService {
    PlayerRepository playerRepository;
    PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Page<Player> searchRepository(String name, Position position, BigDecimal minPrice, BigDecimal maxPrice, Integer minTotalPoints, Pageable pageable) {
        Specification<Player> spec = Specification.allOf();
        spec = spec.and(PlayerSpecification.byName(name));
        spec = spec.and(PlayerSpecification.byPosition(position));
        spec = spec.and(PlayerSpecification.byMinPrice(minPrice));
        spec = spec.and(PlayerSpecification.byMaxPrice(maxPrice));
        spec = spec.and(PlayerSpecification.byMinTotalPoints(minTotalPoints));
        return playerRepository.findAll(spec, pageable);
    }


}
