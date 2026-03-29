
package com.pl.prem_league_data.Repository;
import com.pl.prem_league_data.Entity.Player;
import com.pl.prem_league_data.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player>{
    public boolean existsByNameAndPosition(String name, Position position);
}

