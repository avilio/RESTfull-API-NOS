package interview.challenge.restfullapinos.services;

import interview.challenge.restfullapinos.domain.Match;
import interview.challenge.restfullapinos.domain.MatchResult;
import interview.challenge.restfullapinos.domain.Team;
import interview.challenge.restfullapinos.repositories.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Set<Team> getTeams() {
        log.info("Search for all Teams");
        Set<Team> teamSet = new HashSet<>();
        teamRepository.findAll().iterator().forEachRemaining(teamSet::add);

        return teamSet;
    }

    @Override
    public Set<Team> searchTeamByName(String name) {
        log.info("Searching team with name:"+name);
        return teamRepository.searchByName(name);
    }

    @Override
    public Set<Team> searchTeamByCoach(String coachName) {
        log.info("Searching team with coach:"+coachName);
        return teamRepository.findByCoachName(coachName);
    }

    @Override
    public void deleteTeamById(String idToDelete) {
       // if (teamRepository.findById(idToDelete).isPresent())
        teamRepository.deleteById(idToDelete);
        log.info("Team with id:" + idToDelete +" Deleted");

    }

    @Override
    public Team addTeam(Team team) {
        team.getCoach().setId(new ObjectId().toString());
        Team savedTeam = teamRepository.insert(team);
        log.info("Team "+team +" was added!");
        return savedTeam;
    }

    @Override
    public void deleteTeam(Team team) {
        if (team.getId() == null)
            throw new IllegalArgumentException("Team Id is null");

        teamRepository.delete(team);
        log.info("Team "+team +" was deleted!");
    }

    @Override
    public Team updateTeamInfo(Team team) {

        if (team.getId() == null)
            throw new IllegalArgumentException("Team Id is null");

        Team teamFromDb = teamRepository.findById(team.getId()).get();

        if(team.getId().equals(teamFromDb.getId())) {
            if (!team.getMatches().isEmpty())
                teamFromDb.setMatches(team.getMatches());
            if (team.getCoach()!= null)
                teamFromDb.setCoach(team.getCoach());
            if (team.getName()!= null)
                teamFromDb.setName(team.getName());
        }

        if (teamFromDb.getCoach().getId()== null)
            teamFromDb.getCoach().setId(new ObjectId().toString());

        Team savedTeam = teamRepository.save(teamFromDb);
        log.info("Team "+team +" was updated!");
        return savedTeam;
    }

    @Override
    public void addMatch(String teamId, Match match) {

        Optional<Team> teamObject = teamRepository.findById(teamId);

        if(teamObject.isPresent()) {
            match.setId(new ObjectId().toString());
            Team team = teamObject.get();
            team.getMatches().add(match);

            teamRepository.save(team);
            log.info("match added: " + match.toString() + "to team:" + team.getName());
        }

        Optional<Team> againstTeamObject = teamRepository.findById(match.getAgainstTeamId());

        if(againstTeamObject.isPresent()) {
            Match againstMatch = new Match();
            againstMatch.setId(new ObjectId().toString());
            againstMatch.setAgainstTeamId(teamId);
            againstMatch.setMatchResult(inverseMatchResult(match.getMatchResult()));
            Team againstTeam = againstTeamObject.get();
            againstTeam.getMatches().add(againstMatch);

            teamRepository.save(againstTeam);
            log.info("match added: " + againstMatch.toString() + "to team:" + againstTeam.getName());
        }

        log.info("Match between" + teamObject.get().getName() + "and" + againstTeamObject.get().getName() + "added");


    }

    @Override
    public void updateMatch(String teamId, Match match) {

        if (match.getId() == null || teamId == null)
            throw new IllegalArgumentException("Match Id or Team ID is null");

        Optional<Team> teamOptional = teamRepository.findById(teamId);

        if(teamOptional.isPresent()) {
            Team team = teamOptional.get();
            for (Match teamMatch : team.getMatches()) {
               if (teamMatch.getId().equals(match.getId())){
                   teamMatch.setMatchResult(match.getMatchResult());
                   break;
               }
            }
            teamRepository.save(team);
        }

        Optional<Team> againstTeamOptional = teamRepository.findById(match.getAgainstTeamId());

        if (againstTeamOptional.isPresent()){

            Team againstTeam = againstTeamOptional.get();
            for (Match againstTeamMatch : againstTeam.getMatches()) {
                if (againstTeam.getId().equals(match.getAgainstTeamId())) {
                    againstTeamMatch.setMatchResult(inverseMatchResult(match.getMatchResult()));
                    break;
                }
            }
            teamRepository.save(againstTeam);
        }

        log.info( match +" was updated!");
    }

    private MatchResult inverseMatchResult(MatchResult matchResult) {

        switch (matchResult){
            case WON: return MatchResult.LOST;
            case LOST: return MatchResult.WON;
            default: return matchResult;
        }
    }
}
