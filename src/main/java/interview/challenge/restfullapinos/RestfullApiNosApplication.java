package interview.challenge.restfullapinos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestfullApiNosApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfullApiNosApplication.class, args);
    }
}
