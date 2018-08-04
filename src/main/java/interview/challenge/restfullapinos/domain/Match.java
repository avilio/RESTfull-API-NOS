package interview.challenge.restfullapinos.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Match {

    @Id
    private String id;
    private String againstTeamId;
    private MatchResult matchResult;
}
