package interview.challenge.restfullapinos.repositories;

import interview.challenge.restfullapinos.domain.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,String> {
}
