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
import model.UnidadeEmpresa;

/**
 *
 * @author vinicius
 */
public class UnidadeEmpresaDAO {
    
    private PreparedStatement instrucao;
    private ArrayList<Produto> listaDeProdutos;

    public UnidadeEmpresaDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }
    
    public UnidadeEmpresa buscaUnidadeEmpresa(int id) {
        String codigoSQL
                = "SELECT "
                + "descricao,"
                + "tipo_unidade "
                + "FROM unidade_empresa "
                + "WHERE id_unidade_empresa = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            UnidadeEmpresa unidadeEmpresa = new UnidadeEmpresa(
                    id,
                    resultado.getString("descricao"),
                    resultado.getInt("tipo_unidade")
            );

            resultado.close();
            instrucao.close();
            conexao.close();

            return unidadeEmpresa;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca!");
            throw new RuntimeException(e);
        }
    }
    
}
