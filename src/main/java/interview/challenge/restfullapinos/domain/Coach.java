package interview.challenge.restfullapinos.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;


@Data
@Document
public class Coach {

    @Id
    private String id;

    private String name;
}
