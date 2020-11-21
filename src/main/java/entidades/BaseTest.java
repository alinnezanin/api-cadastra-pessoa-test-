package entidades;

import database.conexao.GerenciadorConexao;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.sql.SQLException;

import static io.restassured.RestAssured.*;

public class BaseTest {


    @BeforeClass
    public static void precondicao() throws IOException {
    GerenciadorConexao.getInstancia();
    baseURI = GerenciadorConexao.getInstancia().getUrl();
    }

    @AfterClass
    public static void finalizaTest() throws IOException, SQLException {
        GerenciadorConexao.getInstancia().disconnect();
    }

}
