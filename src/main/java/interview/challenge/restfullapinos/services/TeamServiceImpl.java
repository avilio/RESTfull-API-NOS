package interview.challenge.restfullapinos.services;

import interview.challenge.restfullapinos.domain.Team;
import interview.challenge.restfullapinos.repositories.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Set<Team> searchTeamByName(String name) {

        return teamRepository.searchByName(name);
    }

    @Override
    public Set<Team> searchTeamByCoach(String coachName) {
        
        return teamRepository.searchByCoach(coachName);
    }

    @Override
    public void deleteTeamById(String idToDelete) {
        teamRepository.deleteById(idToDelete);
        log.debug("Team with id:" + idToDelete +" Deleted");

    }

    @Override
    @PutMapping
    public void addTeam(@RequestBody Team team) {
        teamRepository.insert(team);
    }

    @Override
    @DeleteMapping
    public void deleteTeam(@RequestBody Team team) {
        teamRepository.delete(team);
    }

    @Override
    @PostMapping
    public void updateTeamInfo(@RequestBody Team team) {
        teamRepository.save(team);

    }

    @Override
    public void teamMatches(Team team) {

    }

    public Team getByID(String id){

        return teamRepository.findById(id).get();
    }
}
