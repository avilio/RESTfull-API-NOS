package interview.challenge.restfullapinos.repositories;

import interview.challenge.restfullapinos.domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Set;

public interface TeamRepository extends MongoRepository<Team, String> {

    //Duas maneiras de fazer

    //Query caso algo mais complexo ( nao é o caso) apenas para demonstrar outra maneira
    @Query(value = "{name:?0}")
    Set<Team> searchByName(String name);

    //sem query usa-se o find que faz parte das querys do MongoDB e os atributos em Upper Camel Case dos atributos a ir buscar a base de dados
    Set<Team> findByCoachName(String coachName);
}
