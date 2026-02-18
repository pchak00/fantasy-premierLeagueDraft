
package com.pl.prem_league_data.Repository;
import com.pl.prem_league_data.PlayerEntity.Player;
import com.pl.prem_league_data.PlayerEntity.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    boolean existsByNameAndPosition(String name, Position position);

    @Override
    public Page<Player> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM players ORDER BY goals DESC", nativeQuery = true)
    List<Player> sortByGoalsDesc();

    Page<Player> findByPosition(Position position, Pageable pageable);
    Page<Player> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

