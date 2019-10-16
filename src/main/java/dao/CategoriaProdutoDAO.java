/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Produto;

/**
 *
 * @author vinicius
 */
public class CategoriaProdutoDAO {
    private PreparedStatement instrucao;
    private ArrayList<Produto> listaDeProdutos;

    public CategoriaProdutoDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }
    
    /*public CategoriaProduto buscaCategoriaProduto(int id){
        String codigoSQL 
                = "SELECT"
                + "id,"
                + "descricao "
                + "FROM categoria_produto"
    }*/
}
