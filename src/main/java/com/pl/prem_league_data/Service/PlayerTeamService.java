package com.pl.prem_league_data.Service;

import com.pl.prem_league_data.DTO.PlayerSummaryDto;
import com.pl.prem_league_data.Entity.DraftTeam;
import com.pl.prem_league_data.Entity.Player;
import com.pl.prem_league_data.Entity.PlayerTeam;
import com.pl.prem_league_data.Repository.DraftTeamRepository;
import com.pl.prem_league_data.Repository.PlayerRepository;
import com.pl.prem_league_data.Repository.PlayerTeamRepository;

import java.math.BigDecimal;
import java.util.List;

public class PlayerTeamService {
    private PlayerRepository playerRepository;
    private DraftTeamRepository draftTeamRepository;
    private PlayerTeamRepository playerTeamRepository;

    public PlayerTeamService(PlayerRepository playerRepository, DraftTeamRepository draftTeamRepository, PlayerTeamRepository playerTeamRepository) {
        this.playerRepository = playerRepository;
        this.draftTeamRepository = draftTeamRepository;
        this.playerTeamRepository = playerTeamRepository;
    }

    public void addPlayerToDraftTeam(Player player, DraftTeam draftTeam) {
        Long playerId = player.getId();
        Long teamId = draftTeam.getId();

        List<PlayerTeam> playerTeams = playerTeamRepository.playerInTeam(playerId, teamId);
        List<PlayerTeam> totalPlayersInTeam = playerTeamRepository.findTotalPlayerByTeam(teamId);
        List<PlayerSummaryDto> playerByPosition = playerTeamRepository.playerTeamByPosition(teamId, player.getPosition());
        Boolean positionLimit = draftTeam.positionCap(player.getPosition(), playerByPosition.size()); // return true if adding player of this position to the team is valid

        BigDecimal remainingBudget = draftTeam.getBudget().subtract(player.getPrice());
        if (playerTeams.isEmpty() && totalPlayersInTeam.size() < 15 && positionLimit && remainingBudget.compareTo(BigDecimal.ZERO) >= 0) {
            PlayerTeam playerTeam = new PlayerTeam();
            playerTeam.setPlayer(player);
            playerTeam.setTeam(draftTeam);
            draftTeam.setBudget(remainingBudget);
            draftTeamRepository.save(draftTeam);
            playerTeamRepository.save(playerTeam);
        }
    }
}
