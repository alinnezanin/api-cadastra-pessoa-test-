import database.consultas.BuscaDados;
import entidades.BaseTest;
import entidades.Pessoa;
import entidades.request.PessoaRequest;
import entidades.responses.PessoaResponse;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static io.restassured.RestAssured.*;

@Listeners({TM4JTestNGListener.class})
public class ValidaApiCadastroPessoa extends BaseTest {


    @Test
    @TestCase(key = {"TOC-T438"})
    public void getUmaPessoa() throws SQLException, IOException, ClassNotFoundException {
        BuscaDados buscaDados = new BuscaDados();
        Pessoa pessoaBanco = Pessoa.builder().build();
        pessoaBanco = buscaDados.buscaDadosUmaPessoa("10");
        PessoaResponse pessoaResponse =
        given()
                .pathParam("id", "10")
                .when()
                .log().all()
                .get("/pessoa/{id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .body().as(PessoaResponse.class);

        Assert.assertEquals(pessoaResponse.getNome(), pessoaBanco.getNome());
        Assert.assertEquals(pessoaResponse.getTelefone(), pessoaBanco.getTelefone());
        Assert.assertEquals(pessoaResponse.getCpf(), pessoaBanco.getCpf());
    }



    @Test
    public void postUmaPessoa() throws SQLException, IOException, ClassNotFoundException {

        PessoaRequest request = PessoaRequest.builder().nome("Serafina").telefone("1234567890").cpf("123654788965").build();

        PessoaResponse pessoaResponse =
                given()
                        .relaxedHTTPSValidation()
                        .header("content-type", "application/json")
                        .body(request)
                        .when()
                        .log().all()
                        .post("/pessoa")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .body().as(PessoaResponse.class);

        BuscaDados buscaDados = new BuscaDados();
        Pessoa pessoaBanco = Pessoa.builder().build();
        pessoaBanco = buscaDados.buscaDadosUmaPessoa(pessoaResponse.getId());

        Assert.assertEquals(pessoaResponse.getNome(), pessoaBanco.getNome());
        Assert.assertEquals(pessoaResponse.getTelefone(), pessoaBanco.getTelefone());
        Assert.assertEquals(pessoaResponse.getCpf(), pessoaBanco.getCpf());
    }






}
