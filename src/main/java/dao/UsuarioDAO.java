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
    
    public boolean inserirNovoUsuario(Usuario usuario){
        boolean retorno = false;

        String cadastraClienteSQL = "INSERT INTO usuario(username, senha, id_funcionario)"
                + " VALUES (?,?,?)";

        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(cadastraClienteSQL);

            instrucao.setString(1, usuario.getNomeUsuario());
            instrucao.setString(2, usuario.getSenhaUsuario());
            instrucao.setInt(3, usuario.getId_funcionario());
            
            instrucao.execute();
            instrucao.close();

            retorno = true;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Cadastro de Cliente!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }
    }
    
    public boolean atualizarUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void excluirUsuario(int Id_Usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Usuario buscarUsuarioPorID(int Id_Usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public ArrayList<Usuario> listarUsuarios() {
        String codigoSQL
                = "SELECT "
                + "usuario.id_usuario,"
                + "usuario.username,"
                + "usuario.senha,"
                + "funcionario.id_funcionario"
                + "FROM usuario"
                + "INNER JOIN funcionario ON "
                + "funcionario.id_funcionario = usuario.id_funcionario";

        ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();
            
            while (resultado.next()) {
                if (resultado.getInt("usuario.id_usuario") != 0) {
                    Usuario usuario = new Usuario(
                        resultado.getInt("usuario.id_usuario"),
                        resultado.getString("usuario.username"),
                        resultado.getString("usuario.senha"),
                        resultado.getInt("funcionario.id_funcionario")
                );
                        
                    listaDeUsuarios.add(usuario);
                }

            }

            resultado.close();
            instrucao.close();
            conexao.close();

            return listaDeUsuarios;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Consulta de Pessoas!");
            throw new RuntimeException(e);
        }
    }
    
    
}
