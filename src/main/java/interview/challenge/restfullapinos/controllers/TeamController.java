package interview.challenge.restfullapinos.controllers;


import interview.challenge.restfullapinos.domain.Match;
import interview.challenge.restfullapinos.domain.Team;
import interview.challenge.restfullapinos.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<?> getTeams() {
        Set<Team> teams = teamService.getTeams();
        return ResponseEntity.ok(teams);
    }

    @PostMapping
    public ResponseEntity<?> addTeam(@RequestBody Team team) {
        Team savedTeam = teamService.addTeam(team);
        return ResponseEntity.ok(savedTeam);
    }

    @PutMapping
    public ResponseEntity<?> updateTeam(@RequestBody Team team) {
        Team savedTeam = teamService.updateTeamInfo(team);
        return ResponseEntity.ok(savedTeam);
    }

    @GetMapping("/{teamName}")
    public ResponseEntity<?> searchByTeamName(@PathVariable String teamName) {
        Set<Team> teams = teamService.searchTeamByName(teamName);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/coach/{coachName}")
    public ResponseEntity<?> searchByCoachName(@PathVariable String coachName) {
        Set<Team> teams = teamService.searchTeamByCoach(coachName);
        return ResponseEntity.ok(teams);
    }

    @PostMapping("/match/{teamId}")
    public ResponseEntity<?> addMatch(@PathVariable String teamId, @RequestBody Match match) {
        teamService.addMatch(teamId, match);
        return  ResponseEntity.noContent().build();
    }
}
