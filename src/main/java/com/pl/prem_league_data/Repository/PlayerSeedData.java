package com.pl.prem_league_data.Repository;

import com.pl.prem_league_data.PlayerEntity.Player;
import com.pl.prem_league_data.PlayerEntity.Position;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class PlayerSeedData {

        @Bean
        CommandLineRunner seedPlayers(PlayerRepository repo) {
            return args -> {
                // prevent reseeding on every restart
                if (repo.count() > 0) return;

                repo.save(new Player(
                        null,                                  // id (Integer)
                        "Erling Haaland",                   // name (String)
                        Position.FWD,                   // position (Enum)
                        "Manchester City",                  // team (String)
                        "Norway",                           // nationality (String)
                        2550,                               // minutes (Integer)
                        27,                                 // goals (Integer)
                        5,                                  // assists (Integer)
                        210,                                // totalPoints (Integer)
                        new BigDecimal("15.20"),            // price (BigDecimal - precision 5, scale 2)
                        new BigDecimal("22.50"),            // xG (BigDecimal)
                        new BigDecimal("3.10")

                ));

                repo.save(new Player(
                        null,                                  // id
                        "Bukayo Saka",                      // name
                        Position.MID,                // position
                        "Arsenal",                          // team
                        "England",                          // nationality
                        1569,                               // minutes (25/26 season approx)
                        4,                                  // goals
                        6,                                  // assists
                        120,                                // totalPoints
                        new BigDecimal("12.50"),            // price
                        new BigDecimal("5.58"),             // xG
                        new BigDecimal("5.11")
                ));

                repo.save(new Player(
                        null,                                  // id
                        "Virgil van Dijk",                  // name
                        Position.DEF,                  // position
                        "Liverpool",                        // team
                        "Netherlands",                      // nationality
                        2340,                               // minutes (25/26 season approx)
                        2,                                  // goals
                        0,                                  // assists
                        95,                                 // totalPoints
                        new BigDecimal("6.50"),             // price
                        new BigDecimal("1.90"),             // xG
                        new BigDecimal("0.67")
                ));

            };
        }
    }

