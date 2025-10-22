package hook;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.restassured.RestAssured;

public class Hook {

    @Before
    public void setup() {
        System.out.println("Iniciando cenário de teste...");
        RestAssured.baseURI = "http://localhost:8080";
    }

    @After
    public void tearDown() {
        System.out.println("Finalizando cenário de teste...");
    }
}

