package com.pl.prem_league_data.Service;

import com.pl.prem_league_data.DTO.DraftTeamSummaryDto;
import com.pl.prem_league_data.DTO.PlayerSummaryDto;
import com.pl.prem_league_data.Entity.DraftTeam;
import com.pl.prem_league_data.Entity.Player;
import com.pl.prem_league_data.Entity.PlayerTeam;
import com.pl.prem_league_data.Exceptions.*;
import com.pl.prem_league_data.Repository.DraftTeamRepository;
import com.pl.prem_league_data.Repository.PlayerRepository;
import com.pl.prem_league_data.Repository.PlayerTeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class PlayerTeamService {
    private PlayerRepository playerRepository;
    private DraftTeamRepository draftTeamRepository;
    private PlayerTeamRepository playerTeamRepository;

    public PlayerTeamService(PlayerRepository playerRepository, DraftTeamRepository draftTeamRepository, PlayerTeamRepository playerTeamRepository) {
        this.playerRepository = playerRepository;
        this.draftTeamRepository = draftTeamRepository;
        this.playerTeamRepository = playerTeamRepository;
    }

    @Transactional
    public void addPlayerToDraftTeam(long playerId, long teamId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player not found"));
        DraftTeam draftTeam = draftTeamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));

        List<PlayerTeam> playerTeams = playerTeamRepository.playerInTeam(playerId, teamId);
        List<PlayerTeam> totalPlayersInTeam = playerTeamRepository.findTotalPlayerByTeam(teamId);
        List<PlayerSummaryDto> playerByPosition = playerTeamRepository.playerTeamByPosition(teamId, player.getPosition());
        boolean positionLimit = draftTeam.positionCap(player.getPosition(), playerByPosition.size()); // return true if adding player of this position to the team is valid
        BigDecimal remainingBudget = draftTeam.getBudget().subtract(player.getPrice());

        if(!playerTeams.isEmpty()) {
            throw new DuplicatePlayerException("Player already exists in team");
        }
        if(totalPlayersInTeam.size() >=15 ) {
            throw new TeamCapacityOutOfBoundException("Team capacity is full");
        }
        if(!positionLimit) {
            throw new TeamPositionOutOfBoundException("Team already has maximum number of players in this position");
        }
        if(remainingBudget.compareTo(BigDecimal.ZERO) < 0) {
            throw new TeamBudgetOutOfBoundException("Team budget is not sufficient to add this player");
        }

            PlayerTeam playerTeam = new PlayerTeam();
            playerTeam.setPlayer(player);
            playerTeam.setTeam(draftTeam);

            player.addPlayerTeam(playerTeam);
            draftTeam.addPlayerTeam(playerTeam);
            draftTeam.setBudget(remainingBudget);

            playerTeamRepository.save(playerTeam);

    }
    @Transactional
    public void removePlayer(long playerId, long teamId) {
        Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player not found"));
        DraftTeam draftTeam = draftTeamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));
        PlayerTeam playerTeam = playerTeamRepository.findPlayerInTeam(player.getId(), draftTeam.getId());
        if(playerTeam == null) { throw new PlayerNotInTeamException("Player not found in team"); // nothing to remove
        }
        BigDecimal newBudget = draftTeam.getBudget().add(player.getPrice());
        draftTeam.setBudget(newBudget); // updating remaining budget of the team after removing the player

        playerTeamRepository.delete(playerTeam);
        player.removePlayerTeam(playerTeam);
        draftTeam.removePlayerTeam(playerTeam);

    }
    @Transactional
    public DraftTeamSummaryDto getDraftTeamByTeamId(long teamId) {

        DraftTeam draftTeam = draftTeamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException("Team not found"));
        DraftTeamSummaryDto draftTeamSummaryDto = new DraftTeamSummaryDto();
        draftTeamSummaryDto.setId(draftTeam.getId());
        draftTeamSummaryDto.setName(draftTeam.getName());
        draftTeamSummaryDto.setBudget(draftTeam.getBudget());

        List<PlayerTeam> playerTeams = draftTeam.getPlayerTeams();
        draftTeamSummaryDto.setTotalPlayers(playerTeams.size());
        for(PlayerTeam playerTeam : playerTeams) {
            Player player = playerTeam.getPlayer();
            PlayerSummaryDto playerSummaryDto = new PlayerSummaryDto(player.getId(), player.getName(), player.getPosition());
            draftTeamSummaryDto.addPlayerDto(playerSummaryDto);
        }
        return draftTeamSummaryDto;
    }
    public void createDraftTeam(String name) {
        DraftTeam draftTeam = new DraftTeam();
        draftTeam.setName(name);
        draftTeam.setBudget(new BigDecimal("100.00"));
        draftTeamRepository.save(draftTeam);
    }
    public List<DraftTeamSummaryDto> getDraftTeams() {
        List<DraftTeamSummaryDto> draftTeamsSummary = new ArrayList<>();
        List<DraftTeam> draftTeams = draftTeamRepository.getDraftTeams();
        for(DraftTeam draftTeam : draftTeams) {
            DraftTeamSummaryDto draftTeamSummaryDto = new DraftTeamSummaryDto();
            draftTeamSummaryDto.setId(draftTeam.getId());
            draftTeamSummaryDto.setName(draftTeam.getName());
            draftTeamSummaryDto.setBudget(draftTeam.getBudget());
            draftTeamSummaryDto.setTotalPlayers(draftTeam.getPlayerTeams().size());
            for (PlayerTeam playerTeam : draftTeam.getPlayerTeams()) {
                Player player = playerTeam.getPlayer();
                PlayerSummaryDto playerSummaryDto = new PlayerSummaryDto(player.getId(), player.getName(), player.getPosition());
                draftTeamSummaryDto.addPlayerDto(playerSummaryDto);
            }
            draftTeamsSummary.add(draftTeamSummaryDto);
        }
        return draftTeamsSummary;
        }
        @Transactional
        public void deleteDraftTeam(long teamId) {
            DraftTeam draftTeam = draftTeamRepository.getDraftTeamsById(teamId);
            if(draftTeam == null) {
                throw new TeamNotFoundException("Team not found");
            }
            List<PlayerTeam> playerTeams = draftTeam.getPlayerTeams();
            for (PlayerTeam playerTeam : playerTeams) {
                Player player = playerTeam.getPlayer();
                player.removePlayerTeam(playerTeam);
            }
            draftTeamRepository.delete(draftTeam);
        }
}

