package interview.challenge.restfullapinos.repositories;

import interview.challenge.restfullapinos.domain.Coach;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CoachRepository extends MongoRepository<Coach,String> {
}
