package steps;

import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoSteps {

    private String baseUrl = "http://localhost:8080";
    private Response response;
    private static Integer produtoId;

    private Map<String, Object> produtoValido;
    private Map<String, Object> produtoInvalido;

    @Dado("que a API está rodando no ambiente de teste")
    public void que_a_api_esta_rodando_no_ambiente_de_teste() {
        baseURI = baseUrl;
        System.out.println("API configurada em ambiente de teste: " + baseUrl);
    }

    @Dado("que tenho um novo produto válido para cadastrar")
    public void que_tenho_um_novo_produto_valido_para_cadastrar() {
        produtoValido = new HashMap<>();
        produtoValido.put("nome", "Copo biodegradável");
        produtoValido.put("descricao", "Copo feito de amido de milho");
        produtoValido.put("preco", 5.50);
        produtoValido.put("tempoDegradacao", "6 meses");
    }

    @Quando("envio a requisição de cadastro de produto")
    public void envio_a_requisicao_de_cadastro_de_produto() {
        response = given()
                .contentType("application/json")
                .body(produtoValido)
                .when()
                .post("/api/produtos");

        if (response.statusCode() == 201) {
            produtoId = response.jsonPath().getInt("id");
            System.out.println("Produto criado com ID: " + produtoId);
        }
    }

    @Então("o produto é cadastrado com sucesso e o status deve ser {int}")
    public void o_produto_e_cadastrado_com_sucesso_e_o_status_deve_ser(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Dado("que tento cadastrar um produto sem nome")
    public void que_tento_cadastrar_um_produto_sem_nome() {
        produtoInvalido = new HashMap<>();
        produtoInvalido.put("nome", "");
        produtoInvalido.put("descricao", "Produto sem nome");
        produtoInvalido.put("preco", 2.0);
        produtoInvalido.put("tempoDegradacao", "2 meses");
    }

    @Quando("envio a requisição de cadastro inválida")
    public void envio_a_requisicao_de_cadastro_invalida() {
        response = given()
                .contentType("application/json")
                .body(produtoInvalido)
                .when()
                .post("/api/produtos");
    }

    @Então("a API deve retornar o status {int} e uma mensagem de erro")
    public void a_api_deve_retornar_o_status_e_uma_mensagem_de_erro(Integer statusCode) {
        response.then()
                .statusCode(statusCode)
                .body(containsString("obrigatório"));
    }

    @Quando("envio uma requisição GET de produto para {string}")
    public void envio_uma_requisicao_get_de_produto_para(String endpoint) {
        response = given()
                .when()
                .get(baseUrl + endpoint);
    }

    @Então("o status da resposta de produto deve ser {int}")
    public void o_status_da_resposta_de_produto_final_deve_ser(Integer statusEsperado) {
        Assertions.assertNotNull(response, "A resposta não pode ser nula!");
        response.then().statusCode(statusEsperado);
        System.out.println("Status final da resposta: " + response.getStatusCode());
    }

    @E("o corpo da resposta deve conter uma lista de produtos")
    public void o_corpo_da_resposta_deve_conter_uma_lista_de_produtos() {
        response.then().body("size()", greaterThan(0));
    }

    @Dado("que existe um produto cadastrado com o nome {string}")
    public void que_existe_um_produto_cadastrado_com_o_nome(String nome) {
        response = given()
                .when()
                .get(baseUrl + "/api/produtos/nome/" + nome);

        if (response.statusCode() == 200) {
            List<Integer> ids = response.jsonPath().getList("id");
            if (ids != null && !ids.isEmpty()) {
                produtoId = ids.get(0);
                System.out.println("Produto encontrado: ID = " + produtoId);
            } else {
                System.out.println("Nenhum produto encontrado com esse nome!");
            }
        }

    }

    @E("o corpo da resposta deve conter o campo {string} com valor {string}")
    public void o_corpo_da_resposta_deve_conter_o_campo_com_valor(String campo, String valor) {
        try {
            Object responseBody = response.then().extract().path(campo);

            if (responseBody instanceof List) {
                response.then().body(campo, hasItem(valor));
            } else {
                response.then().body(campo, equalTo(valor));
            }
        } catch (Exception e) {
            throw new AssertionError("Erro ao validar campo '" + campo + "': " + e.getMessage());
        }
    }

    @Dado("que tenho um produto existente para atualizar")
    public void que_tenho_um_produto_existente_para_atualizar() {
        produtoValido = new HashMap<>();
        produtoValido.put("id", produtoId);
        produtoValido.put("nome", "Produto Atualizado");
        produtoValido.put("descricao", "Descrição atualizada do produto");
        produtoValido.put("preco", 8.0);
        produtoValido.put("tempoDegradacao", "3 meses");
    }

    @Quando("envio uma requisição PUT para {string}")
    public void envio_uma_requisicao_put_para(String endpoint) {
        response = given()
                .contentType("application/json")
                .body(produtoValido)
                .when()
                .put(baseUrl + endpoint);

        System.out.println("Status da resposta: " + response.getStatusCode());
        System.out.println("Corpo da resposta: " + response.getBody().asString());
    }

    @Dado("que tenho um produto existente")
    public void que_tenho_um_produto_existente() {
        if (produtoId == null) {
            que_tenho_um_novo_produto_valido_para_cadastrar();
            envio_a_requisicao_de_cadastro_de_produto();
        }
    }

    @Quando("envio uma requisição DELETE para {string}")
    public void envio_uma_requisicao_delete_para(String endpoint) {
        response = given()
                .when()
                .delete(baseUrl + endpoint + "/" + produtoId);

        System.out.println("Status da resposta: " + response.getStatusCode());
    }
}
