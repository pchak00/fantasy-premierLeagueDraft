package com.pl.prem_league_data.Controller;

import com.pl.prem_league_data.DTO.DraftTeamSummaryDto;
import com.pl.prem_league_data.Entity.PlayerTeam;
import com.pl.prem_league_data.Service.PlayerTeamService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DraftTeamController {
    PlayerTeamService playerTeamService;

     DraftTeamController(PlayerTeamService playerTeamService) {
        this.playerTeamService = playerTeamService;
    }

    @PostMapping("/draft-teams/create")
    public void createDraftTeam(@RequestBody Map<String, String> body) {
        String teamName = body.get("teamName");
        playerTeamService.createDraftTeam(teamName);
    }

    @GetMapping("/draft-team/{id}")
    public DraftTeamSummaryDto getDraftTeamById(@PathVariable Long id) {
        return playerTeamService.getDraftTeamByTeamId(id);
    }
    @PutMapping("/draft-team/add/{teamId}")
    public void addPlayerToDraftTeam(@RequestBody Map<String, String> body, @PathVariable Long teamId) {
         Long playerId = Long.parseLong(body.get("playerId"));
        playerTeamService.addPlayerToDraftTeam(playerId, teamId);
    }
    @PutMapping("/draft-team/remove/{teamId}")
    public void removePlayerFromDraftTeam(@RequestBody Map<String, String> body, @PathVariable Long teamId) {
        Long playerId = Long.parseLong(body.get("playerId"));
         playerTeamService.removePlayer(playerId, teamId);
     }
}
