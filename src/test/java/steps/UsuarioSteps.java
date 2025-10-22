package steps;

import br.com.fiap.BioPure.BioPureApplication;
import io.cucumber.java.pt.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = BioPureApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsuarioSteps {

    private Response response;
    private Map<String, Object> usuarioValido;
    private String baseUrl = "http://localhost:8080/api/usuarios";

    @Dado("que a API de usuários está rodando no ambiente de teste")
    public void que_a_api_de_usuarios_esta_rodando_no_ambiente_de_teste() {
        System.out.println("API configurada em ambiente de teste: " + baseUrl);
    }

    @Dado("que tenho um novo usuário válido para cadastrar")
    public void que_tenho_um_novo_usuario_valido_para_cadastrar() {
        usuarioValido = new HashMap<>();
        usuarioValido.put("nome", "João da Silva");
        usuarioValido.put("email", "joao.silva@teste.com");
        usuarioValido.put("senha", "123456");
        usuarioValido.put("role", "USER");
    }

    @Quando("envio a requisição de cadastro de usuário")
    public void envio_a_requisicao_de_cadastro_de_usuario() {
        response = given()
                .contentType(ContentType.JSON)
                .body(usuarioValido)
                .when()
                .post(baseUrl);
    }

    @Então("o usuário é cadastrado com sucesso e o status deve ser {int}")
    public void o_usuario_e_cadastrado_com_sucesso_e_o_status_deve_ser(Integer statusEsperado) {
        response.then().statusCode(statusEsperado);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    @Quando("envio uma requisição GET para {string}")
    public void envio_uma_requisicao_get_para(String endpoint) {
        response = given()
                .when()
                .get("http://localhost:8080" + endpoint);
    }

    @Então("o status da resposta deve ser {int}")
    public void o_status_da_resposta_deve_ser(Integer statusEsperado) {
        Assertions.assertNotNull(response, "A resposta não pode ser nula!");
        response.then().statusCode(statusEsperado);
        System.out.println("Status da resposta: " + response.getStatusCode());
    }
}
