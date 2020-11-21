package database.conexao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GerenciadorConexao {

    //    public static String driver = "oracle.jdbc.driver.OracleDriver";
    public static String driver = "com.mysql.cj.jdbc.Driver";
    private static String arquivo = "configuracao.properties";
    private String url, tns, user, pass, ambiente;
    private static GerenciadorConexao instancia = null;
    private Connection conexao = null;


    public String getUrl() {
        return url;
    }

    public String getAmbiente() {
        return ambiente;
    }


    private GerenciadorConexao() throws IOException {
        Properties props = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(arquivo);
        props.load(in);
        in.close();
        url = props.getProperty("url");
        tns = props.getProperty("tns");
        user = props.getProperty("user");
        pass = props.getProperty("password");
        ambiente = props.getProperty("ambiente");
    }

    public static GerenciadorConexao getInstancia() throws IOException {
        if(instancia == null){
            instancia = new GerenciadorConexao();
        }
        return instancia;
    }


    public Connection connect() throws ClassNotFoundException, SQLException {
        if (conexao == null){
            Class.forName(driver);
            conexao = DriverManager.getConnection(tns,user,pass);
        }
        return conexao;
    }

    public  void disconnect() throws SQLException {
         conexao.close();
         conexao = null;
    }


}
