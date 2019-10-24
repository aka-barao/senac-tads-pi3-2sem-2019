/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.ClienteDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Pessoa;

/**
 *
 * @author gabrielle.csilva11
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/clientes"})
public class ClienteController extends HttpServlet {
    
    private ClienteDAO clienteDAO;
    

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
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
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Cliente> listaClientes = clienteDAO.listarClientes();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista_clientes.jsp");
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
