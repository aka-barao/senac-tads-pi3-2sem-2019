<%-- 
    Document   : CadastroLogin
    Created on : 27/11/2019, 15:42:08
    Author     : samue
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Usuario</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/bootstrap/css/simple-sidebar.css" rel="stylesheet">

        <style>

            .container{
                position: relative;
                top:100px;

            }

        </style>
    </head>
    <body>
        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-light border-right" id="sidebar-wrapper">
                <div class="sidebar-heading">Scorpions </div>
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action bg-light">Home</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Venda</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Cliente</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Funcionário</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Relatório</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Produto</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Usuario</a>
                </div>

                <br>
                <h5>&nbspCliente</h5>
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action bg-light">Cadastrar Usuario</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Usuarios Cadastrados</a>

                </div>
            </div>
            <!-- /#sidebar-wrapper -->


            <div id="page-content-wrapper">         

                <nav class="nav bg-light justify-content-end">

                    <a class="nav-link" href="#">Sair</a>
                </nav>

                <!-- CONTAINER-->        
                <div class="container">

                    <!-- H1-->        
                    <h1>Cadastro Cliente</h1>

                    <!-- INICIO DO FORM-->        
                    <form method="POST" action="UsuarioServlet">
                        <input type="hidden" name="acao" value="CRIAR"/>
                        <input type="hidden" name="IdUsuario" value="${usuario.Id_usuario}"/>


                        <!-- COMEÇO / NOME E CPF de Funcionario -->       
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Codigo</label>
                                <input type="text" class="form-control" placeholder="Codigo" disabled name="IdUsuario" value="${usuario.Id_usuario}" >
                            </div>
                            
                            <div class="form-group col-md-6">
                                <label>CPF</label>
                                <input type="text" class="form-control" placeholder="CPF" name="cpf" value="${funcionario.cpf}">
                            </div>
                            
                        </div>
                        <!-- FIM / NOME E SOBRENOME -->    

                        <!-- COMEÇO/ Nome de Usuario E Senha -->  
                        <div class="form-row">
                            
                            <div class="form-group col-md-6">
                                <label>Nome de Usuario</label>
                                <input type="text" class="form-control" placeholder="Nome de Usuario" name="nomeUsuario" value="${usuario.nomeUsuario}">
                            </div>
                            
                            
                            <div class="form-group col-md-6">
                                <label>Senha</label>
                                <input type="text" class="form-control" placeholder="Senha" name="senhaUsuario" value="${usuario.senhaUsuario}">
                            </div>

                           
                        </div>
                        <!-- FIM / Nome de Usuario E Senha -->

                          

                        <!-- BOTOES /  INSERIR E FECHAR-->        
                        <button type="submit" value="Enviar" class="btn btn-primary">Inserir</button>
                        <button type="button" value="" class="btn btn-primary">Fechar</button>
                    </form>

                    <br><br>


                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Codigo</th>
                                <th scope="col">Nome de Usuario</th>
                                <th scope="col">Senha</th>
                                <th scope="col">Editar</th>
                                <th scope="col">Deletar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuario" items="${listaUsuarios}">
                            <tr>
                                <td><c:out value="${usuario.Id_usuario}" /></td>	
                                <td><c:out value="${usuario.nomeUsuario}" /></td>	
                                <td><c:out value="${usuario.senhaUsuario}" /></td>		
                                <td><a href=UsuarioServlet?acao=EDITAR&IdUsuario=${usuario.Id_usuario}>Editar</a></td>
                                <td><a href=UsuarioServlet?acao=DELETAR&IdUsuario=${usuario.Id_usuario}>Excluir</a></td>
                            </tr>
                            </c:forEach>	
                        </tbody>
                    </table>

                </div>
                <!-- container -->   

            </div>
            <!-- /#page-content-wrapper -->
        </div>
        <!-- /#wrapper -->

        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Menu Toggle Script -->
        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
        </script>
    </body>
</html>
