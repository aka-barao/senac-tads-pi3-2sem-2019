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
import model.Cargo;

/**
 *
 * @author samue
 */
public class CargoDAO {
    
    private PreparedStatement instrucao;
    
    public CargoDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }
    
    public Cargo buscaCargoPorID(int id) {
        String codigoSQL
                = "SELECT "
                + "descricao "
                + "FROM cargo "
                + "WHERE id_cargo = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            Cargo cargo = new Cargo(
                    id,
                    resultado.getString("descricao"));

            resultado.close();
            instrucao.close();
            conexao.close();

            return cargo;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca!");
            throw new RuntimeException(e);
        }
    }
    
    public Cargo buscaCargo(String descricao) {
        String codigoSQL
                = "SELECT "
                + "id_cargo,"
                + "descricao "
                + "FROM cargo "
                + "WHERE descricao = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setString(1, descricao);
            ResultSet resultado = instrucao.executeQuery();

            Cargo cargo = new Cargo(
                    resultado.getInt("id_cargo"),
                    resultado.getString("descricao"));

            resultado.close();
            instrucao.close();
            conexao.close();

            return cargo;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca!");
            throw new RuntimeException(e);
        }
    }
    
}
