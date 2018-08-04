package interview.challenge.restfullapinos.repositories;

import interview.challenge.restfullapinos.domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Set;

public interface TeamRepository extends MongoRepository<Team,String> {

    @Query(value = "{name:?0}")
    Set<Team> searchByName(String name);

    @Query(value = "{coach.name:?0}")
    Set<Team> searchByCoach(String coachName);
}
