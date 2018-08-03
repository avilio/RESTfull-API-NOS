package interview.challenge.restfullapinos.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Team {

    @Id
    private String id;
    @Indexed
    private String name;

    private Coach coach;


}
