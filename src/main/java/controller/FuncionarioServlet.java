/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CargoDAO;
import dao.DepartamentoDAO;
import dao.FuncionarioDAO;
import dao.UnidadeEmpresaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cargo;
import model.Departamento;
import model.Funcionario;
import model.UnidadeEmpresa;

/**
 *
 * @author samue
 */
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

    
    private FuncionarioDAO funcionarioDAO;
    private CargoDAO cargoDAO;
    private UnidadeEmpresaDAO unidadeEmpresaDAO;
    private DepartamentoDAO departamentoDAO;
    
    @Override
    public void init() {
        funcionarioDAO = new FuncionarioDAO();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FuncionarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FuncionarioServlet at " + request.getContextPath() + "</h1>");
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
					Funcionario funcionario = criaFuncionario(request);
					/*
                                        try {
						cliente.valida();
					} catch (ValidacaoException e) {
						request.setAttribute("mensagem", "Erro de Validacao dos Campos: " + e.getMessage());
						request.setAttribute("cliente", cliente);
					}
                                        */
					if (funcionario.getIdFuncionario() == null) {
						funcionarioDAO.inserirFuncionario(funcionario);
						request.setAttribute("mensagem", "Cliente salvo com sucesso");
					} else {
						funcionarioDAO.atualizarCliente(funcionario);
						request.setAttribute("mensagem", "Cliente atualizado com sucesso");
					}
				} else if (acao.equals("RETRIEVE")) {
					String Id = request.getParameter("IdFuncionario");
					Integer IdFuncionario = Integer.parseInt(Id);
					Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorID(IdFuncionario);
					request.setAttribute("funcionario", funcionario);
	
				} else if (acao.equals("DELETE")) {
					String Id = request.getParameter("IdFuncionario");
					Integer IdFuncionario = Integer.parseInt(Id);
					funcionarioDAO.excluir(IdFuncionario);
					request.setAttribute("mensagem", "Funcionario excluido");
				}
			}
			request.setAttribute("funcionarios", funcionarioDAO.listarClientes());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/funcionario/CadastroFuncionario.jsp");
			dispatcher.forward(request, response);
		} catch (IllegalArgumentException e) {
			request.setAttribute("mensagem", "Erro: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/erros/erro.jsp");
			dispatcher.forward(request, response);
		}
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    private Funcionario criaFuncionario(HttpServletRequest request) {
        String nome = request.getParameter("nome");
        String CPF = request.getParameter("CPF");
        Date DataNasc = Date.valueOf(request.getParameter("dtNascimento"));
        String IdFuncionario = request.getParameter("IdFuncionario");
        
        String cargo = request.getParameter("cargo");
        Cargo cargoObj = cargoDAO.buscaCargo(cargo);
        
        String unidadeEmpresa = request.getParameter("unidadeEmpresa");
        UnidadeEmpresa unidadeEmpresaObj = unidadeEmpresaDAO.buscaUnidadeEmpresa(unidadeEmpresa);
        
        String departamento = request.getParameter("departamento");
        Departamento departamentoObj = departamentoDAO.buscaDepartamento(departamento);
        
        Funcionario funcionario = new Funcionario(null, nome, DataNasc, CPF, cargoObj, unidadeEmpresaObj, departamentoObj);
        if (IdFuncionario != null && !IdFuncionario.equals("")) {
            funcionario.setIdFuncionario(Integer.parseInt(IdFuncionario));
        }
        return funcionario;
    }
    
}
