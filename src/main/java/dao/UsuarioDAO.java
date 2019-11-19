/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Funcionario;
import model.Usuario;

/**
 *
 * @author vinicius
 */
public class UsuarioDAO {

    private PreparedStatement instrucao;

    public UsuarioDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }

    public Usuario fazLogin(String username, String senha) {
        String codigoSQL
                = "SELECT "
                + "id_usuario,"
                + "username,"
                + "senha,"
                + "id_funcionario "
                + "FROM usuario "
                + "WHERE username = ? "
                + "AND senha = MD5(?)";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setString(1, username);
            instrucao.setString(2, senha);
            ResultSet resultado = instrucao.executeQuery();
            
            Usuario usuarioLogado;
            
            if(resultado.next()){
                usuarioLogado = new Usuario();
                usuarioLogado.setId_usuario(resultado.getInt("id_usuario"));
                usuarioLogado.setId_funcionario(resultado.getInt("id_funcionario"));
            } else {
                usuarioLogado = null;
            }

            resultado.close();
            instrucao.close();
            conexao.close();

            return usuarioLogado;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Login!");
            throw new RuntimeException(e);
        }
    }
}
