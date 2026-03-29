package com.pl.prem_league_data.Controller;

import com.pl.prem_league_data.DTO.DraftTeamSummaryDto;
import com.pl.prem_league_data.DTO.IdRequestDto;
import com.pl.prem_league_data.DTO.TeamNameRequestDto;
import com.pl.prem_league_data.Service.PlayerTeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DraftTeamController {
    PlayerTeamService playerTeamService;

     DraftTeamController(PlayerTeamService playerTeamService) {
        this.playerTeamService = playerTeamService;
    }

    @PostMapping("/draft-teams/create")
    public void createDraftTeam(@RequestBody TeamNameRequestDto teamNameRequestDto) {
        String teamName = teamNameRequestDto.getTeamName();
        playerTeamService.createDraftTeam(teamName);
    }

    @GetMapping("/draft-teams/{id}")
    public DraftTeamSummaryDto getDraftTeamById(@PathVariable Long id) {
        return playerTeamService.getDraftTeamByTeamId(id);
    }
    @PutMapping("/draft-teams/add/{teamId}")
    public void addPlayerToDraftTeam(@RequestBody IdRequestDto idRequestDto, @PathVariable Long teamId) {
         Long playerId = idRequestDto.getId();
        playerTeamService.addPlayerToDraftTeam(playerId, teamId);
    }
    @PutMapping("/draft-teams/removePlayer/{teamId}")
    public void removePlayerFromDraftTeam(@RequestBody IdRequestDto idRequestDto, @PathVariable Long teamId) {
        Long playerId = idRequestDto.getId();
         playerTeamService.removePlayer(playerId, teamId);
     }
     @GetMapping("/draft-teams")
        public List<DraftTeamSummaryDto> getDraftTeams() {
        return playerTeamService.getDraftTeams();
     }
     @PutMapping("/draft-teams/removeTeam/{teamId}")
    public void removeDraftTeam(@PathVariable Long teamId) {
        playerTeamService.deleteDraftTeam(teamId);
     }
}
