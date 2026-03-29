package com.pl.prem_league_data.Controller;

import com.pl.prem_league_data.DTO.DraftTeamSummaryDto;
import com.pl.prem_league_data.DTO.IdRequestDto;
import com.pl.prem_league_data.DTO.TeamNameRequestDto;
import com.pl.prem_league_data.Service.PlayerTeamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class DraftTeamController {
    PlayerTeamService playerTeamService;

     DraftTeamController(PlayerTeamService playerTeamService) {
        this.playerTeamService = playerTeamService;
    }

    @PostMapping("/draft-teams/create")
    public void createDraftTeam( @Valid @RequestBody TeamNameRequestDto teamNameRequestDto) {
        String teamName = teamNameRequestDto.getTeamName();
        playerTeamService.createDraftTeam(teamName);
    }

    @GetMapping("/draft-teams/{id}")
    public DraftTeamSummaryDto getDraftTeamById(@PathVariable @PositiveOrZero(message = "id must be non negative") Long id) {
        return playerTeamService.getDraftTeamByTeamId(id);
    }
    @PutMapping("/draft-teams/add/{teamId}")
    public void addPlayerToDraftTeam(@Valid @RequestBody IdRequestDto idRequestDto, @PathVariable @PositiveOrZero(message = "id must be non negative") Long teamId) {
         Long playerId = idRequestDto.getId();
        playerTeamService.addPlayerToDraftTeam(playerId, teamId);
    }
    @PutMapping("/draft-teams/removePlayer/{teamId}")
    public void removePlayerFromDraftTeam(@Valid @RequestBody IdRequestDto idRequestDto, @PathVariable @PositiveOrZero(message = "id must be non negative") Long teamId) {
        Long playerId = idRequestDto.getId();
         playerTeamService.removePlayer(playerId, teamId);
     }
     @GetMapping("/draft-teams")
        public List<DraftTeamSummaryDto> getDraftTeams() {
        return playerTeamService.getDraftTeams();
     }
     @PutMapping("/draft-teams/removeTeam/{teamId}")
    public void removeDraftTeam(@PathVariable @PositiveOrZero(message = "id must be non negative") Long teamId) {
        playerTeamService.deleteDraftTeam(teamId);
     }
}
