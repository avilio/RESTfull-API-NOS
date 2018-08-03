package interview.challenge.restfullapinos.controllers;

import interview.challenge.restfullapinos.services.TeamService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }



}
