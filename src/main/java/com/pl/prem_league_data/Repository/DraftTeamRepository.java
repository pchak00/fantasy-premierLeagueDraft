package com.pl.prem_league_data.Repository;

import com.pl.prem_league_data.DTO.DraftTeamSummaryDto;
import com.pl.prem_league_data.Entity.DraftTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DraftTeamRepository extends JpaRepository<DraftTeam, Long> {
    @Query("Select dt from DraftTeam dt left join fetch dt.playerTeams pt left join fetch pt.player")
    List<DraftTeam> getDraftTeams();
}
