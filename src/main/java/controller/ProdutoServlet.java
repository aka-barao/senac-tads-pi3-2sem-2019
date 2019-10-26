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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProdutoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProdutoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
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
        */
        try{
            response.getWriter().append("Served at: ").append(request.getContextPath());
        } catch (Exception ex){
            request.setAttribute("Mensagem", "Erro banco de dados");
        }
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
}