package interview.challenge.restfullapinos.services;

import interview.challenge.restfullapinos.domain.Match;
import interview.challenge.restfullapinos.domain.Team;

import java.util.Set;

public interface TeamService {

    Set<Team> getTeams();

    Set<Team> searchTeamByName(String name);
    Set<Team> searchTeamByCoach(String coachName);

    Team deleteTeamById(String idToDelete);
    Team addTeam(Team team);
    Team deleteTeam(Team team);
    Team updateTeamInfo(Team team);

    void addMatch(String teamId, Match match);
}
