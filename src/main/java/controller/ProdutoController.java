/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProdutoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produto;

/**
 *
 * @author vinicius
 */
public class ProdutoController extends HttpServlet {

    private ProdutoDAO produtoDAO;

    @Override
    public void init() {
        produtoDAO = new ProdutoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getServletPath();

            switch (action) {
                case "/produtos/novo_produto":
                    mostrarFormularioNovoProduto(request, response);
                    break;
                case "/produtos/inserir_produto":
                    inserirProduto(request, response);
                    break;
                case "/produtos/deletar_produto":
                    deletarProduto(request, response);
                    break;
                case "/produtos/editar_produto":
                    mostrarFormularioEditarProduto(request, response);
                    break;
                case "/produtos/atualizar_produto":
                    atualizarProduto(request, response);
                    break;
                default:
                    listarProdutos(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarProdutos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Produto> listaProdutos = produtoDAO.listarProdutos();
        request.setAttribute("listaProdutos", listaProdutos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista_produtos.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovoProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("novo_produto.jsp");
        dispatcher.forward(request, response);
    }

    private void inserirProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        double valor = Double.parseDouble(request.getParameter("valor"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Produto produto = new Produto(valor, nome, descricao);
        produtoDAO.inserirProduto(produto);
        response.sendRedirect("lista_produtos");
    }
    
    private void deletarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        produtoDAO.excluirProduto(id);
        response.sendRedirect("lista_produtos");
 
    }
    
    private void mostrarFormularioEditarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto produto = produtoDAO.buscaProduto(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editar_produto.jsp");
        request.setAttribute("produto", produto);
        dispatcher.forward(request, response);
 
    }
    
    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        double valor = Double.parseDouble(request.getParameter("valor"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
 
        Produto produto = new Produto(valor, nome, descricao);
        produtoDAO.atualizarProduto(produto);
        response.sendRedirect("lista_produtos");
    }

}