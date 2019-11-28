/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import dao.PessoaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Pessoa;

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
            try {
			String acao = request.getParameter("acao");
			if (acao != null) {
				if (acao.equals("CREATE")) {
					Cliente cliente = criaCliente(request);
					/*
                                        try {
						cliente.valida();
					} catch (ValidacaoException e) {
						request.setAttribute("mensagem", "Erro de Validacao dos Campos: " + e.getMessage());
						request.setAttribute("cliente", cliente);
					}
                                        */
					if (cliente.getIdCliente()== null) {
						clienteDAO.inserirNovoCliente(cliente);
						request.setAttribute("mensagem", "Cliente salvo com sucesso");
					} else {
						clienteDAO.atualizarCliente(cliente);
						request.setAttribute("mensagem", "Cliente atualizado com sucesso");
					}
				} else if (acao.equals("RETRIEVE")) {
					String Id = request.getParameter("IdCliente");
					Integer IdCliente = Integer.parseInt(Id);
					Cliente cliente = clienteDAO.buscarClientePorID(IdCliente);
					request.setAttribute("cliente", cliente);
	
				} else if (acao.equals("DELETE")) {
					String Id = request.getParameter("IdCliente");
					Integer IdCliente = Integer.parseInt(Id);
					clienteDAO.excluir(IdCliente);
					request.setAttribute("mensagem", "Cliente excluido");
				}
			}
			request.setAttribute("ListaClientes", clienteDAO.listarClientes());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/CadastroCliente.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException | ClassNotFoundException | IllegalArgumentException e) {
			request.setAttribute("mensagem", "Erro: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/erros/erro.jsp");
			dispatcher.forward(request, response);
		}
        /*
        try {
            String action = request.getParameter("action");

            if (action != null) {
                switch (action) {
                    case "/clientes/novo_cliente":
                        //  mostrarFormularioNovoCliente(request, response);
                        break;
                    case "/clientes/inserir_cliente":
                        inserirCliente(request, response);
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
                    case "/clientes/listar_cliente":
                        listarClientes(request, response);
                        break;
                    default:
                        response.getWriter().append("Erro Action + ").append(request.getContextPath());
                        break;
                }
            } else {
                listarClientes(request, response);
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("Mensagem", "Erro de Banco de Dados");
        }
        
        try {    
            listarClientes(request, response);
        } catch (SQLException ex) {
            request.setAttribute("Mensagem", "Erro de Banco de Dados");
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

    private Cliente criaCliente(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        String CPF = request.getParameter("CPF");
        Date DataNasc = Date.valueOf(request.getParameter("dtNascimento"));
        String IdCliente = request.getParameter("IdCliente");
        
        Cliente cliente = new Cliente(null, nome, DataNasc, CPF);
        if (IdCliente != null && !IdCliente.equals("")) {
            cliente.setIdCliente(Integer.parseInt(IdCliente));
        }
        return cliente;
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Cliente> listaClientes = clienteDAO.listarClientes();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/lista_cliente.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNovoCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cliente/cliente.jsp");
        dispatcher.forward(request, response);
    }

    private void inserirCliente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String nome = request.getParameter("nome");
        String CPF = request.getParameter("CPF");
        Date DataNasc = Date.valueOf(request.getParameter("dtNascimento"));

        //String DataNasc = request.getParameter("dtNascimento");
        //LocalDate dtNasc = LocalDate.parse(DataNasc);
        //Date DtNascimento = Date.valueOf(dtNasc);
        /*
        String DataNasc = request.getParameter("dtNascimento");
        response.getWriter().append(nome).append(request.getContextPath());
        response.getWriter().append(CPF).append(request.getContextPath());
        response.getWriter().append(DataNasc).append(request.getContextPath());
         */
        Cliente cliente = new Cliente(nome, DataNasc, CPF);
        // String nasc = String.valueOf(cliente.getDataNascimento());
        // response.getWriter().append(nasc).append(request.getContextPath());
        try {
            if (clienteDAO.inserirNovoCliente(cliente) == false) {
                response.getWriter().append("ERRO - Cadastro de Nova Pessoa");
            }
        } catch (java.lang.NullPointerException nullex) {
            response.getWriter().append("Erro - Cadastro de Cliente - Null");
        }
        response.sendRedirect("/WEB-INF/cliente/cliente.jsp");
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
