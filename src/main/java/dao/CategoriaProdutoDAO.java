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
import model.CategoriaProduto;
import model.Produto;

/**
 *
 * @author vinicius
 */
public class CategoriaProdutoDAO {

    private PreparedStatement instrucao;
    private ArrayList<CategoriaProduto> listaDeCategoriasProduto;

    public CategoriaProdutoDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }

    public CategoriaProduto buscaCategoriaProdutoPorID(int id) {
        String codigoSQL
                = "SELECT "
                + "descricao "
                + "FROM categoria_produto "
                + "WHERE id_categoria_produto = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setInt(1, id);
            ResultSet resultado = instrucao.executeQuery();

            CategoriaProduto categoriaProduto = new CategoriaProduto(
                    id,
                    resultado.getString("descricao")
            );

            resultado.close();
            instrucao.close();
            conexao.close();

            return categoriaProduto;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca!");
            throw new RuntimeException(e);
        }
    }
    
    public CategoriaProduto buscaCategoriaProduto(String categoria) {
        String codigoSQL
                = "SELECT "
                + "id_categoria_produto,"
                + "descricao "
                + "FROM categoria_produto "
                + "WHERE descricao = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            instrucao.setString(1, categoria);
            ResultSet resultado = instrucao.executeQuery();

            CategoriaProduto categoriaProduto = new CategoriaProduto(
                    resultado.getInt("id_categoria_produto"),
                    resultado.getString("descricao")
            );

            resultado.close();
            instrucao.close();
            conexao.close();

            return categoriaProduto;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca!");
            throw new RuntimeException(e);
        }
    }
}
