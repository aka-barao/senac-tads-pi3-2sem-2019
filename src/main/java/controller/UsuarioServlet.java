/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Usuario;

/**
 *
 * @author samue
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;
    private FuncionarioDAO funcionarioDAO;
    
    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
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
            out.println("<title>Servlet UsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioServlet at " + request.getContextPath() + "</h1>");
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
                    Usuario usuario = CriaUsuario(request);
                    /*
                                        try {
						cliente.valida();
					} catch (ValidacaoException e) {
						request.setAttribute("mensagem", "Erro de Validacao dos Campos: " + e.getMessage());
						request.setAttribute("cliente", cliente);
					}
                     */
                    if (usuario.getId_usuario() == null) {
                        usuarioDAO.inserirNovoUsuario(usuario);
                        request.setAttribute("mensagem", "Cliente salvo com sucesso");
                    } else {
                        usuarioDAO.atualizarUsuario(usuario);
                        request.setAttribute("mensagem", "Cliente atualizado com sucesso");
                    }

                } else if (acao.equals("RETRIEVE")) {
                    String IdUsuario = request.getParameter("IdUsuario");
                    int Id = Integer.parseInt(IdUsuario);
                    Usuario usuario = usuarioDAO.buscarUsuarioPorID(Id);
                    request.setAttribute("produto", usuario);

                } else if (acao.equals("DELETE")) {
                    String IdUsuario = request.getParameter("IdUsuario");
                    int Id = Integer.parseInt(IdUsuario);
                    usuarioDAO.excluirUsuario(Id);
                    request.setAttribute("mensagem", "Cliente excluido");
                }
            }
            request.setAttribute("listaUsuarios", usuarioDAO.listarUsuarios());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/usuario/CadastroUsuarios.jsp");
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
    
    
    
    private Usuario CriaUsuario(HttpServletRequest request) {
        String IdUsuario = request.getParameter("IdUsuario");
        String nomeUsuario = request.getParameter("nomeUsuario");
        String senhaUsuario = request.getParameter("senhaUsuario");

        String cpfFuncionario = request.getParameter("cpf");
        Funcionario funcionario = funcionarioDAO.buscarFuncionarioPorCPF(cpfFuncionario);

        Usuario usuario = new Usuario(null, nomeUsuario, senhaUsuario, funcionario.getIdFuncionario());
        if (IdUsuario != null && !IdUsuario.equals("")) {
            usuario.setId_usuario(Integer.parseInt(IdUsuario));
        }
        return usuario;
    }
    
    
    
}
