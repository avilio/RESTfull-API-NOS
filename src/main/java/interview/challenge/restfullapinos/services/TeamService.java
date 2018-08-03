package interview.challenge.restfullapinos.services;

import interview.challenge.restfullapinos.domain.Team;

import java.util.Set;

public interface TeamService {

    Set<Team> getTeams();

    Team searchTeamByName(String name);
    Team searchTeamByCoach(String coachName);

    void deleteTeam(String idToDelete);
    void addTeam(Team team);
    void updateTeamInfo(Team team);
    void teamMatches(Team team);
}
