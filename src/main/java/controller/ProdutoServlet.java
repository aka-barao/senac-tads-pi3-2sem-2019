/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriaProdutoDAO;
import dao.ProdutoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaProduto;
import model.Produto;

/**
 *
 * @author samue
 */
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO produtoDAO;
    private CategoriaProdutoDAO categoriaProdutoDAO;

    @Override
    public void init() {
        produtoDAO = new ProdutoDAO();
        categoriaProdutoDAO = new CategoriaProdutoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");
            if (acao != null) {
                if (acao.equals("CREATE")) {
                    Produto produto = CriaProduto(request);
                    /*
                                        try {
						cliente.valida();
					} catch (ValidacaoException e) {
						request.setAttribute("mensagem", "Erro de Validacao dos Campos: " + e.getMessage());
						request.setAttribute("cliente", cliente);
					}
                     */
                    if (produto.getId() == null) {
                        produtoDAO.inserirNovoProduto(produto);
                        request.setAttribute("mensagem", "Cliente salvo com sucesso");
                    } else {
                        produtoDAO.atualizarProduto(produto);
                        request.setAttribute("mensagem", "Cliente atualizado com sucesso");
                    }

                } else if (acao.equals("RETRIEVE")) {
                    String IdProduto = request.getParameter("idProduto");
                    int Id = Integer.parseInt(IdProduto);
                    Produto produto = produtoDAO.buscarProdutoPorID(Id);
                    request.setAttribute("produto", produto);

                } else if (acao.equals("DELETE")) {
                    String IdProduto = request.getParameter("idProduto");
                    int Id = Integer.parseInt(IdProduto);
                    produtoDAO.excluirProduto(Id);
                    request.setAttribute("mensagem", "Cliente excluido");
                }
            }
            request.setAttribute("listaProdutos", produtoDAO.listarProdutos());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/produto/CadastroProduto.jsp");
            dispatcher.forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("mensagem", "Erro: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/erros/erro.jsp");
            dispatcher.forward(request, response);
        }
        /*try {
            String action = request.getParameter("action");
            if (action == null) {
                listarProdutos(request, response);
            } else {
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
            }
        } catch (SQLException ex) {

        }
         */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Produto CriaProduto(HttpServletRequest request) {
        String IdProduto = request.getParameter("IdProduto");
        double valor = Double.parseDouble(request.getParameter("valor"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        String CategoriaProduto = request.getParameter("categoria");
        CategoriaProduto categoriaProduto = categoriaProdutoDAO.buscaCategoriaProduto(CategoriaProduto);

        Produto produto = new Produto(null, valor, nome, descricao, categoriaProduto);
        if (IdProduto != null && !IdProduto.equals("")) {
            produto.setId(Integer.parseInt(IdProduto));
        }
        return produto;
    }
    
    /*
    private void listarProdutos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Produto> listaProdutos = produtoDAO.listarProdutos();
        request.setAttribute("listaProdutos", listaProdutos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/produto/lista_produtos.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovoProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("novo_produto.jsp");
        dispatcher.forward(request, response);
    }

    private void inserirProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        double valor = Double.parseDouble(request.getParameter("valor"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        int idCategoriaProduto = Integer.parseInt(request.getParameter("id_categoria_produto"));
        CategoriaProduto categoriaProduto = categoriaProdutoDAO.buscaCategoriaProduto(idCategoriaProduto);

        Produto produto = new Produto(valor, nome, descricao, categoriaProduto);
        produtoDAO.inserirNovoProduto(produto);

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
        Produto produto = produtoDAO.buscarProdutoPorID(id);
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

        // Futuramente pegar dados da unidade empresa do funcionário da sessão
        int idUnidadeEmpresa = Integer.parseInt(request.getParameter("id_unidade_empresa"));
        String descricaoUnidadeEmpresa = request.getParameter("descricao_unidade_empresa");
        int tipoUnidadeEmpresa = Integer.parseInt(request.getParameter("tipo_unidade_empresa"));

        int idCategoriaProduto = Integer.parseInt(request.getParameter("id_categoria_produto"));
        CategoriaProduto categoriaProduto = categoriaProdutoDAO.buscaCategoriaProduto(idCategoriaProduto);

        Produto produto = new Produto(valor, nome, descricao, categoriaProduto);

        produtoDAO.atualizarProduto(produto);
        response.sendRedirect("lista_produtos");
    }
    */
}
