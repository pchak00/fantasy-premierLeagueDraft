
package com.pl.prem_league_data.Repository;
import com.pl.prem_league_data.PlayerEntity.Player;
import com.pl.prem_league_data.PlayerEntity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    boolean existsByNameAndPosition(String name, Position position);

    @Query(value = "SELECT * FROM players ORDER BY goals DESC", nativeQuery = true)
    List<Player> sortByGoalsDesc();
}

