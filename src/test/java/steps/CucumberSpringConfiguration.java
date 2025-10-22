package steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.fiap.BioPure.BioPureApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = BioPureApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberSpringConfiguration {
}
