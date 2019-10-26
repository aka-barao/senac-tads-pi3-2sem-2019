/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author samue
 */
public class ClienteServlet extends HttpServlet {

    
    private ClienteDAO clienteDAO;
    

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ClienteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteServlet at " + request.getContextPath() + "</h1>");
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
                case "/clientes/novo_cliente":
                  //  mostrarFormularioNovoCliente(request, response);
                    break;
                case "/clientes/inserir_cliente":
                  //  inserirCliente(request, response);
                    break;
                case "/clientes/deletar_cliente":
                  //  deletarCliente(request, response);
                    break;
                case "/clientes/editar_cliente":
                   // mostrarFormularioEditarCliente(request, response);
                    break;
                case "/clientes/atualizar_cliente":
                   // atualizarCliente(request, response);
                    break;
                default:
                    listarClientes(request, response);
                    break;
                    
                    
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("Mensagem", "Erro de Banco de Dados");
        }
        */
        try {    
            listarClientes(request, response);
        } catch (SQLException ex) {
            request.setAttribute("Mensagem", "Erro de Banco de Dados");
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

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Cliente> listaClientes = clienteDAO.listarClientes();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/cliente/lista_clientes.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("novo_cliente.jsp");
        dispatcher.forward(request, response);
    }

    private void inserirCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        String nome = request.getParameter("nome");
        String CPF = request.getParameter("CPF");
        Date DataNasc = Date.valueOf(request.getParameter("DtNasc"));

        Cliente cliente = new Cliente(nome, DataNasc, CPF);
        clienteDAO.cadastrarNovoCliente(cliente);

        response.sendRedirect("lista_clientes");
    }
    /*
    private void deletarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        pessoaDAO.excluirCliente(id);
        response.sendRedirect("lista_cliente");

    }
    */
    
    private void mostrarFormularioEditarCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = clienteDAO.buscarClientePorID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editar_produto.jsp");
        request.setAttribute("cliente", cliente);
        dispatcher.forward(request, response);

    }
    /*
    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String CPF = request.getParameter("CPF");
        Date DataNasc = Date.valueOf(request.getParameter("DtNasc"));
        
        Cliente cliente = new Cliente(id, nome, DataNasc, CPF);


        pessoaDAO.atualizarCliente(cliente);
        response.sendRedirect("lista_cliente");
    }
    */
}
