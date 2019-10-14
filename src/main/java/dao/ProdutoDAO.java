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
public class ProdutoDAO {

    private PreparedStatement instrucao;
    private ArrayList<Produto> listaDeProdutos;

    public ProdutoDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }

    public boolean inserirProduto(Produto produto) {
        boolean retorno = false;

        String insereProdutoSQL = "INSERT INTO produto(nome, descricao, valor, quantidade_estoque, id_categoria_produto, data_cadastro)"
                + " VALUES (?,?,?,?,?,CURRENT_TIMESTAMP())";

        /*
        String insereRelacaoCategoriaProdutoSQL = "INSERT INTO produto_categoria(id_produto, id_categoria)"
                + " VALUES ((SELECT LAST_INSERT_ID()), ?) ";
         */
        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try (Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(insereProdutoSQL);

            instrucao.setString(1, produto.getNome());
            instrucao.setString(2, produto.getDescricao());
            instrucao.setDouble(3, produto.getValor());
            instrucao.setDouble(4, produto.getQuantidadeEstoque());
            instrucao.setInt(5, produto.getCategoriaProduto().getId());

            instrucao.execute();
            instrucao.close();

            // Caso o produto tenha categoria associada.
            /*if (produto.getCategoriaProduto() != null) {
                instrucao = conexao.prepareStatement(insereRelacaoCategoriaProdutoSQL);
                CategoriaProduto categoria = produto.getCategoriaProduto();
                instrucao.setInt(1, categoria.getIDCategoriaProduto());

                instrucao.execute();
                instrucao.close();
            }*/
            retorno = true;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Cadastro!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }
    }

    public ArrayList<Produto> listarProdutos() {
        String codigoSQL
                = "SELECT "
                + "produto.id_produto,"
                + "produto.nome,"
                + "produto.descricao,"
                + "produto.valor,"
                //+ "produto.quantidade_estoque,"
                + "produto.data_cadastro,"
                + "categoria_produto.id_categoria_produto,"
                + "categoria_produto.descricao "
                + "FROM produto "
                + "INNER JOIN categoria_produto ON "
                + "categoria_produto.id_categoria_produto = produto.id_categoria_produto";

        listaDeProdutos = new ArrayList<Produto>();

        try (Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto(
                        resultado.getInt("produto.id_produto"),
                        resultado.getDouble("produto.valor"),
                        resultado.getString("produto.nome"),
                        resultado.getString("produto.descricao"),
                        //resultado.getInt("produto.quantidade"),
                        resultado.getDate("produto.data_cadastro")
                );

                CategoriaProduto categoria = new CategoriaProduto(
                        resultado.getInt("categoria_produto.id_categoria_produto"),
                        resultado.getString("ccategoria_produto.descricao")
                );

                produto.setCategoriaProduto(categoria);

                listaDeProdutos.add(produto);
            }

            resultado.close();
            instrucao.close();
            conexao.close();

            return listaDeProdutos;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Consulta!");
            throw new RuntimeException(e);
        }
    }

    public boolean atualizarProduto(Produto produto) {

        boolean retorno = false;

        String atualizaProdutoSQL = "UPDATE produto SET "
                + "nome = ?, "
                + "descricao = ?, "
                + "valor = ?, "
                //+ "id_categoria_produto = ? "
                //+ "quantidade_estoque = ?,"
                + "WHERE id = ?";

        try (Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(atualizaProdutoSQL);

            instrucao.setString(1, produto.getNome());
            instrucao.setString(2, produto.getDescricao());
            instrucao.setDouble(3, produto.getValor());
            //instrucao.setInt(4, produto.getCategoriaProduto().getId());
            instrucao.setInt(4, produto.getId());

            int linhasAfetadasProduto = instrucao.executeUpdate();
            instrucao.close();

            /*int linhasAfetadasCategoriaProduto = 0;

            // Caso o produto tenha categoria associada.
            if (produto.getCategoriaProduto() != null) {
                instrucao = conexao.prepareStatement(atualizaCategoriaProdutoSQL);

                CategoriaProduto categoria = produto.getCategoriaProduto();

                instrucao.setInt(1, categoria.getIDCategoriaProduto());
                instrucao.setInt(2, produto.getIdProduto());

                linhasAfetadasCategoriaProduto = instrucao.executeUpdate();
                instrucao.close();

            }*/
            retorno = linhasAfetadasProduto > 0; // || linhasAfetadasCategoriaProduto > 0;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Atualização!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }

    public boolean excluirProduto(int i) {

        boolean retorno = false;

        String deletaProdutoSQL = "DELETE FROM produto WHERE id_produto = ?";
        //String deletaRelacaoCategoriaProdutoSQL = "DELETE FROM produto_categoria WHERE id_produto = ?";

        try (Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(deletaProdutoSQL);

            instrucao.setInt(1, i);

            int linhasAfetadasProduto = instrucao.executeUpdate();
            instrucao.close();

            /*instrucao = conexao.prepareStatement(deletaRelacaoCategoriaProdutoSQL);

            instrucao.setInt(1, i);

            int linhasAfetadasCategoriaProduto = instrucao.executeUpdate();
            instrucao.close();*/
            retorno = linhasAfetadasProduto > 0; //|| (linhasAfetadasProduto > 0 && linhasAfetadasCategoriaProduto > 0);

        } catch (SQLException e) {
            System.out.println("Erro na operação de Exclusão!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }
    
    // Para Implementar
    public Produto buscaProduto(int id){
        String codigoSQL
                = "SELECT "
                + "id_produto,"
                + "valor,"
                + "nome,"
                + "descricao,"
                + "data_cadastro "
                + "FROM produto "
                + "WHERE "
                + "id_produto = ?";
        
        try (Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();
            
        }
    }
}
