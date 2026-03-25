package com.pl.prem_league_data.Repository;

import com.pl.prem_league_data.DTO.PlayerSummaryDto;
import com.pl.prem_league_data.Entity.Player;
import com.pl.prem_league_data.Entity.PlayerTeam;
import com.pl.prem_league_data.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerTeamRepository extends JpaRepository<PlayerTeam, Long> {
    @Query("Select pt from PlayerTeam pt where pt.player.id = :playerId and pt.team.id = :teamId")
    public List<PlayerTeam> playerInTeam(Long playerId, Long teamId);

    @Query("Select pt from PlayerTeam pt where pt.team.id = :teamId")
    public List<PlayerTeam> findTotalPlayerByTeam(Long teamId);

    @Query("Select new com.pl.prem_league_data.DTO.PlayerSummaryDto(pt.player.id, pt.player.name, pt.player.position) from PlayerTeam pt where pt.team.id = :teamId and pt.player.position = :position")
    public List<PlayerSummaryDto> playerTeamByPosition(Long teamId, Position position);
}
