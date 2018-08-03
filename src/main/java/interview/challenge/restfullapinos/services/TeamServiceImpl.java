package interview.challenge.restfullapinos.services;

import interview.challenge.restfullapinos.domain.Team;
import interview.challenge.restfullapinos.repositories.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public Set<Team> getTeams() {

        Set<Team> teamSet = new HashSet<>();
        teamRepository.findAll().iterator().forEachRemaining(teamSet::add);

        return teamSet;
    }

    @Override
    public Team searchTeamByName(String name) {

        return null;
    }

    @Override
    public Team searchTeamByCoach(String coachName) {
        return null;
    }

    @Override
    public void deleteTeam(String idToDelete) {
        teamRepository.deleteById(idToDelete);
        log.debug("Team with id:" + idToDelete +" Deleted");

    }

    @Override
    public void addTeam(Team team) {

    }

    @Override
    public void updateTeamInfo(Team team) {

    }

    @Override
    public void teamMatches(Team team) {

    }
}
