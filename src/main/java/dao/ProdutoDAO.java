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
public class ProdutoDAO {

    private PreparedStatement instrucao;
    private ArrayList<Produto> listaDeProdutos;

    private UnidadeEmpresaDAO unidadeEmpresaDAO;

    public ProdutoDAO() { // Avisa no console caso o programa consiga se conectar sem problemas ao BD;
        try {
            Connection testaConexao = new ConnectionFactory().getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            testaConexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados!");
        }

    }

    public boolean inserirNovoProduto(Produto produto) {
        boolean retorno = false;

        String insereProdutoSQL = "INSERT INTO produto(nome, descricao, valor, id_categoria_produto, data_cadastro)"
                + " VALUES (?,?,?,?,CURRENT_TIMESTAMP())";

        // try-with-resources || Conexão será aberta novamente dentro do "try" e fechada automaticamente ao final dele.
        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(insereProdutoSQL);

            instrucao.setString(1, produto.getNome());
            instrucao.setString(2, produto.getDescricao());
            instrucao.setDouble(3, produto.getValor());
            instrucao.setInt(4, produto.getCategoriaProduto().getId());

            instrucao.execute();
            instrucao.close();

            retorno = true;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Cadastro de Produto!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }
    }

    public boolean inserirQuantidadeEstoque(Produto produto, UnidadeEmpresa unidadeEmpresa) {
        boolean retorno = false;

        String insereQuantidadeEstoqueSQL = "INSERT INTO quantidade_estoque(id_produto, id_unidade_empresa, quantidade_estoque)"
                + " VALUES (?, ?, ?)";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {

            instrucao = conexao.prepareStatement(insereQuantidadeEstoqueSQL);
            
            instrucao.setInt(1, produto.getId());
            instrucao.setInt(2, unidadeEmpresa.getId());
            instrucao.setInt(2, produto.getQuantidadeEstoque(unidadeEmpresa));

            instrucao.execute();
            instrucao.close();

            retorno = true;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Cadastro de Estoque!");
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
                + "quantidade_estoque.quantidade_estoque,"
                + "quantidade_estoque.id_unidade_empresa,"
                + "produto.data_cadastro,"
                + "categoria_produto.id_categoria_produto,"
                + "categoria_produto.descricao "
                + "FROM produto "
                + "INNER JOIN categoria_produto ON "
                + "categoria_produto.id_categoria_produto = produto.id_categoria_produto "
                + "INNER JOIN quantidade_estoque ON "
                + "quantidade_estoque.id_produto = produto.id_produto "
                + "INNER JOIN unidade_empresa ON "
                + "unidade_empresa.id_unidade_empresa = quantidade_estoque.id_unidade_empresa";

        listaDeProdutos = new ArrayList<Produto>();

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto(
                        resultado.getInt("produto.id_produto"),
                        resultado.getDouble("produto.valor"),
                        resultado.getString("produto.nome"),
                        resultado.getString("produto.descricao"),
                        resultado.getDate("produto.data_cadastro")
                );

                CategoriaProduto categoria = new CategoriaProduto(
                        resultado.getInt("categoria_produto.id_categoria_produto"),
                        resultado.getString("ccategoria_produto.descricao")
                );

                unidadeEmpresaDAO = new UnidadeEmpresaDAO();
                UnidadeEmpresa unidadeEmpresa = unidadeEmpresaDAO.buscaUnidadeEmpresa(
                        resultado.getInt("quantidade_estoque.id_unidade_empresa"));

                produto.setCategoriaProduto(categoria);
                produto.setQuantidadeEstoque(unidadeEmpresa,
                        resultado.getInt("quantidade_estoque.quantidade_estoque"));

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

    public boolean atualizarProduto(Produto produto, UnidadeEmpresa unidadeEmpresa) {

        boolean retorno = false;

        String atualizaProdutoSQL
                = "UPDATE produto SET "
                + "nome = ?, "
                + "descricao = ?, "
                + "valor = ?, "
                + "id_categoria_produto = ? "
                + "WHERE id = ?";

        String atualizaQuantidadeEstoqueSQL
                = "UPDATE quantidade_estoque SET "
                + "quantidade_estoque = ? "
                + "WHERE id_produto = ? "
                + "AND id_unidade_empresa = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(atualizaProdutoSQL);

            instrucao.setString(1, produto.getNome());
            instrucao.setString(2, produto.getDescricao());
            instrucao.setDouble(3, produto.getValor());
            instrucao.setInt(4, produto.getCategoriaProduto().getId());
            instrucao.setInt(5, produto.getId());

            int linhasAfetadasProduto = instrucao.executeUpdate();
            instrucao.close();

            instrucao = conexao.prepareStatement(atualizaQuantidadeEstoqueSQL);

            instrucao.setInt(1, produto.getQuantidadeEstoque(unidadeEmpresa));
            instrucao.setInt(2, produto.getId());
            instrucao.setInt(3, unidadeEmpresa.getId());

            int linhasAfetadasQuantidadeEstoque = instrucao.executeUpdate();
            instrucao.close();

            retorno = linhasAfetadasProduto > 0 || linhasAfetadasQuantidadeEstoque > 0;

        } catch (SQLException e) {
            System.out.println("Erro na operação de Atualização!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }

    public boolean excluirProduto(int id) {

        boolean retorno = false;

        String deletaProdutoSQL = "DELETE FROM produto WHERE id_produto = ?";
        String deletaQuantidadeEstoqueProdutoSQL = "DELETE FROM quantidade_estoque WHERE id_produto = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(deletaProdutoSQL);

            instrucao.setInt(1, id);

            int linhasAfetadasProduto = instrucao.executeUpdate();
            instrucao.close();

            instrucao = conexao.prepareStatement(deletaQuantidadeEstoqueProdutoSQL);

            instrucao.setInt(1, id);

            int linhasAfetadasQuantidadeEstoqueProduto = instrucao.executeUpdate();
            instrucao.close();
            retorno = linhasAfetadasProduto > 0 || (linhasAfetadasProduto > 0 && linhasAfetadasQuantidadeEstoqueProduto > 0);

        } catch (SQLException e) {
            System.out.println("Erro na operação de Exclusão!");
            throw new RuntimeException(e);

        } finally {
            return retorno;
        }

    }

    public Produto buscarProduto(int id) {
        String codigoSQL
                = "SELECT "
                + "produto.id_produto,"
                + "produto.nome,"
                + "produto.descricao,"
                + "produto.valor,"
                + "quantidade_estoque.quantidade_estoque,"
                + "quantidade_estoque.id_unidade_empresa,"
                + "produto.data_cadastro,"
                + "categoria_produto.id_categoria_produto,"
                + "categoria_produto.descricao "
                + "FROM produto "
                + "INNER JOIN categoria_produto ON "
                + "categoria_produto.id_categoria_produto = produto.id_categoria_produto "
                + "INNER JOIN quantidade_estoque ON "
                + "quantidade_estoque.id_produto = produto.id_produto "
                + "INNER JOIN unidade_empresa ON "
                + "unidade_empresa.id_unidade_empresa = quantidade_estoque.id_unidade_empresa"
                + "WHERE produto.id_produto = ?";

        try ( Connection conexao = new ConnectionFactory().getConnection()) {
            instrucao = conexao.prepareStatement(codigoSQL);
            ResultSet resultado = instrucao.executeQuery();

            Produto produto = new Produto(
                    resultado.getInt("produto.id_produto"),
                    resultado.getDouble("produto.valor"),
                    resultado.getString("produto.nome"),
                    resultado.getString("produto.descricao"),
                    resultado.getDate("produto.data_cadastro")
            );

            CategoriaProduto categoria = new CategoriaProduto(
                    resultado.getInt("categoria_produto.id_categoria_produto"),
                    resultado.getString("ccategoria_produto.descricao")
            );

            unidadeEmpresaDAO = new UnidadeEmpresaDAO();
            UnidadeEmpresa unidadeEmpresa = unidadeEmpresaDAO.buscaUnidadeEmpresa(
                    resultado.getInt("quantidade_estoque.id_unidade_empresa"));

            produto.setCategoriaProduto(categoria);
            produto.setQuantidadeEstoque(unidadeEmpresa,
                    resultado.getInt("quantidade_estoque.quantidade_estoque"));

            resultado.close();
            instrucao.close();
            conexao.close();

            return produto;
        } catch (SQLException e) {
            System.out.println("Erro na operação de Busca!");
            throw new RuntimeException(e);
        }
    }
}
