package interview.challenge.restfullapinos.services;

import interview.challenge.restfullapinos.domain.Team;
import interview.challenge.restfullapinos.repositories.TeamRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTeams() {

    }

    @Test
    public void searchTeamByNameThatExists() {
        // given
        Set<Team> teams = new HashSet<>();
        teams.add(createTeam("team1"));

        Mockito.when(teamRepository.searchByName(Mockito.anyString())).thenReturn(teams);

        // when
        Set<Team> teasm = teamService.searchTeamByName("team1");

        // then
        Assert.assertNotNull(teasm);
        Assert.assertEquals(1, teasm.size());

        Team team = (Team) teasm.toArray()[0];

        Assert.assertEquals("team1", team.getName());
    }

    @Test
    public void searchTeamByNameThatDoesntExists() {
        Set<Team> teams = new HashSet<>();
        Mockito.when(teamRepository.searchByName(Mockito.anyString())).thenReturn(teams);

        // when
        Set<Team> teasm = teamService.searchTeamByName("team1");

        // then
        Assert.assertNotNull(teasm);
        Assert.assertEquals(0, teams.size());
    }

    @Test
    public void searchTeamByCoach() {
    }

    @Test
    public void deleteTeamById() {
    }

    @Test
    public void addTeam() {
    }

    @Test
    public void deleteTeam() {
    }

    @Test
    public void updateTeamInfo() {
    }

    @Test
    public void addMatch() {
    }

    private Team createTeam(String name) {
        Team t = new Team();
        t.setName(name);

        return t;
    }
}