package interview.challenge.restfullapinos.services;

import interview.challenge.restfullapinos.domain.Coach;
import interview.challenge.restfullapinos.domain.Team;
import interview.challenge.restfullapinos.repositories.TeamRepository;
import org.bson.types.ObjectId;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

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
        List<Team> teams = new ArrayList<>();

        when(teamRepository.findAll()).thenReturn(teams);

        Set<Team> team1 = teamService.getTeams();

        Assert.assertNotNull(team1);

    }

    @Test
    public void searchTeamByNameThatExists() {
        // given
        Set<Team> teams = new HashSet<>();
        teams.add(createTeam("team1"));

        when(teamRepository.searchByName(Mockito.anyString())).thenReturn(teams);

        // when
        Set<Team> team1 = teamService.searchTeamByName("team1");

        // then
        Assert.assertNotNull(team1);
        Assert.assertEquals(1, team1.size());

        Team team = (Team) team1.toArray()[0];

        Assert.assertEquals("team1", team.getName());
    }

    @Test
    public void searchTeamByNameThatDoesntExists() {
       //given
        Set<Team> teams = new HashSet<>();
        when(teamRepository.searchByName(Mockito.anyString())).thenReturn(teams);

        // when
        Set<Team> teams1 = teamService.searchTeamByName("team1");

        // then
        Assert.assertNotNull(teams1);
        Assert.assertEquals(0, teams.size());
    }

    @Test
    public void addTeam() {

        List<Team> teams = new ArrayList<>();

        when(teamRepository.insert(Mockito.anyIterable())).thenReturn(teams);

        Team team1 = teamService.addTeam(createTeam("PORTO"));

        Assert.assertNotNull(team1);
        Assert.assertEquals("PORTO", team1.getName());

    }

    @Test
    public void updateTeamInfo() {

        List<Team> teams = new ArrayList<>();
        teams.add(createTeam("team1"));

        when(teamRepository.save(Mockito.any())).thenReturn(teams);

        Team teamToModify = (Team) teams.toArray()[0];
        Assert.assertEquals("team1", teamToModify.getName());

        teamToModify.setName("PORTO");
        Team team1 = teamService.updateTeamInfo(teamToModify);

        Assert.assertNotNull(team1);
        Assert.assertEquals("PORTO", team1.getName());
    }

    private Team createTeam(String name) {
        Team t = new Team();
        Coach c = new Coach();

        c.setId((new ObjectId().toString()));
        c.setName("jj");

        t.setName(name);
        t.setId(new ObjectId().toString());
        t.setId(new ObjectId().toString());
        t.setCoach(c);

        return t;
    }

}