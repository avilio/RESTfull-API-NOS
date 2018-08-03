package interview.challenge.restfullapinos.repositories;

import interview.challenge.restfullapinos.domain.Coach;
import org.springframework.data.repository.CrudRepository;

public interface CoachRepository extends CrudRepository<Coach,String> {
}
