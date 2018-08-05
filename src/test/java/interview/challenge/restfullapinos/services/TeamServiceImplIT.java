package interview.challenge.restfullapinos.services;

import interview.challenge.restfullapinos.domain.Team;
import interview.challenge.restfullapinos.repositories.TeamRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TeamServiceImplIT {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamService teamService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getTeams() {
        List<Team> teamSet = teamRepository.findAll();

        Assert.assertEquals(2,teamSet.size());
    }

    @Test
    public void searchTeamByName() {

        Set<Team> teamSet = teamRepository.searchByName("SCP");
        Team team = (Team) teamSet.toArray()[0];

        Assert.assertEquals("SCP",team.getName());
    }

    @Test
    public void searchTeamByCoach() {

        Set<Team> teamSet = teamRepository.findByCoachName("just you");
        Team team = (Team) teamSet.toArray()[0];

        Assert.assertEquals("SCP",team.getName());
    }

}