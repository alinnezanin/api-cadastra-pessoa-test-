package database.consultas;

import database.conexao.GerenciadorConexao;
import entidades.Pessoa;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscaDados {


    public Pessoa buscaDadosUmaPessoa(String id) throws IOException, SQLException, ClassNotFoundException {

        Pessoa pessoa =null;

            Statement statement = GerenciadorConexao.getInstancia().connect().createStatement();
            String sql = "Select * from pessoa where id="+id;
            ResultSet resultSet = statement.executeQuery(sql);

           // while (resultSet.next()){
           if(resultSet.next()){
                pessoa = Pessoa.builder()
                        .id(resultSet.getString("id"))
                        .nome(resultSet.getString("nome"))
                        .cpf(resultSet.getString("cpf"))
                        .telefone(resultSet.getString("telefone"))
                        .build();
           }
           return pessoa;

    }



}
