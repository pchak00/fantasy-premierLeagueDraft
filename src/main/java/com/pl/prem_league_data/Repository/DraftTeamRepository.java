package com.pl.prem_league_data.Repository;

import com.pl.prem_league_data.Entity.DraftTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftTeamRepository extends JpaRepository<DraftTeam, Long> {
}
