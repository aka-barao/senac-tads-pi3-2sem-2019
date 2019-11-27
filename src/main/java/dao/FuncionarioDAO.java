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

/**
 *
 * @author samue
 */
public class FuncionarioDAO {
    
    private PreparedStatement instrucao;
    private ArrayList<Funcionario> listaDeFuncionarios;

    public Object listarClientes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Funcionario buscarFuncionarioPorID(Integer IdFuncionario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    public Funcionario buscarFuncionarioPorCPF(String CPF) {
        String codigoSQL
                = "SELECT "
                + "id_funcionario,"
                + "nome,"
                + "data_nascimento,"
                + "cpf "
                + "FROM funcionario "
                + "WHERE cpf = ?";
        
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setString(1, CPF);
            ResultSet resultado = instrucao.executeQuery();

            Funcionario funcionario = new Funcionario(
                resultado.getInt("funcionario.id_funcionario"),
                resultado.getString("funcionario.nome"),
                new java.util.Date(resultado.getTimestamp("funcionario.data_nascimento").getTime()),
                resultado.getString("funcionario.cpf")
        );

            resultado.close();
            instrucao.close();
            conexao.close();

            return funcionario;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca de Pessoa!");
            throw new RuntimeException(e);
        }
    }

    public void inserirFuncionario(Funcionario funcionario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void atualizarCliente(Funcionario funcionario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void excluir(Integer IdFuncionario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
