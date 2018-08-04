package interview.challenge.restfullapinos.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Data
@Document
public class Team {

    @Id
    private String id;
    @Indexed
    private String name;

    private Coach coach;

    private Set<Match> matches = new HashSet<>();
}
