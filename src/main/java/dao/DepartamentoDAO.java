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
import model.Departamento;
/**
 *
 * @author samue
 */
public class DepartamentoDAO {
    
    private PreparedStatement instrucao;
    
    public DepartamentoDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }
    
    public Departamento buscaDepartamentoPorID(int id) {
        String codigoSQL
                = "SELECT "
                + "descricao "
                + "FROM departamento "
                + "WHERE id_departamento = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            Departamento departamento = new Departamento(
                    id,
                    resultado.getString("descricao"));

            resultado.close();
            instrucao.close();
            conexao.close();

            return departamento;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca!");
            throw new RuntimeException(e);
        }
    }
    
    public Departamento buscaDepartamento(String descricao) {
        String codigoSQL
                = "SELECT "
                + "id_departamento,"
                + "descricao "
                + "FROM departamento "
                + "WHERE descricao = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setString(1, descricao);
            ResultSet resultado = instrucao.executeQuery();

            Departamento departamento = new Departamento(
                    resultado.getInt("id_departamento"),
                    resultado.getString("descricao"));

            resultado.close();
            instrucao.close();
            conexao.close();

            return departamento;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca!");
            throw new RuntimeException(e);
        }
    }
    
}
