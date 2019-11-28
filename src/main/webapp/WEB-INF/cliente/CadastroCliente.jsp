<%-- 
    Document   : CadastroCliente
    Created on : 19/11/2019, 23:08:37
    Author     : Gabrielle
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Cliente</title>
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
                    <a href="index.html" class="list-group-item list-group-item-action bg-light">Home</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Venda</a>
                    <a href="FuncionarioServlet" class="list-group-item list-group-item-action bg-light">Funcionário</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Relatório</a>
                    <a href="ProdutoServlet" class="list-group-item list-group-item-action bg-light">Produto</a>
                </div>

                <br>
                <h5>&nbspCliente</h5>
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action bg-light">Cadastrar Cliente</a>
                    <a href="ClienteServlet" class="list-group-item list-group-item-action bg-light">Lista de Clientes </a>

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
                    <form method="POST" action="ClienteServlet">
                        <input type="hidden" name="acao" value="CRIAR"/>
                        <input type="hidden" name="IdCliente" value="${cliente.IdCliente}"/>


                        <!-- COMEÇO / NOME E SOBRENOME -->       
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Codigo</label>
                                <input type="text" class="form-control" placeholder="Codigo" disabled name="IdCliente" value="${cliente.IdCliente}" >
                            </div>

                            <div class="form-group col-md-6">
                                <label>Nome</label>
                                <input type="text" class="form-control" placeholder="Nome" name="nome" value="${cliente.nome}">
                            </div>
                        </div>
                        <!-- FIM / NOME E SOBRENOME -->    

                        <!-- COMEÇO/ CPF E RG -->  
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>CPF</label>
                                <input type="text" class="form-control" placeholder="CPF" name="cpf" value="${cliente.cpf}">
                            </div>

                            <div class="form-group col-md-6">
                                <label>RG</label>
                                <input type="text" class="form-control" placeholder="RG" >
                            </div>
                        </div>
                        <!-- FIM / CPF E RG -->

                        <!-- COMEÇO / DATA DE NASCIMENTO E SEXO--> 
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="txtDtNascimento">Data de nascimento</label>
                                <input type="date" class="form-control" name="dtNascimento" value="${cliente.dataNascimento}">
                                <div class="erro-input">
                                    Digite uma data válida.
                                </div>
                            </div>

                            <div class="form-group col-md-6">
                                <label>Sexo</label>
                                <select class="form-control" placeholder="Selecione uma opção">
                                    <option></option>
                                    <option>Feminino</option>
                                    <option>Masculino</option>
                                </select>
                            </div>
                        </div>
                        <!-- FIM / DATA DE NASCIMENTO E SEXO-->            

                        <!-- COMEÇO / ENDERECO-->           
                        <div class="form-group">
                            <label>Endereço</label>
                            <input type="text" class="form-control" placeholder="Ex. Rua Ipanema, nº 0">
                        </div>
                        <!-- FIM / ENDERECO-->                


                        <!-- COMEÇO / BAIRRO E CEP-->        
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Bairro</label>
                                <input type="text" class="form-control" placeholder="Ex. Jardim Veneza">
                            </div>

                            <div class="form-group col-md-6">
                                <label>CEP</label>
                                <input type="text" class="form-control" placeholder="CEP">
                            </div>

                        </div>
                        <!-- FIM / BAIRRO E CEP-->     


                        <!-- COMEÇO /  CIDADE, ESTADO E CEP-->            
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label>Cidade</label>
                                <input type="text" class="form-control" placeholder="Ex. São Paulo">
                            </div>

                            <div class="form-group col-md-4">
                                <label>Estado</label>
                                <input type="text" class="form-control" placeholder="Ex. SP">
                            </div>

                            <div class="form-group col-md-2">
                                <label>CEP</label>
                                <input type="text" class="form-control">
                            </div>
                        </div>
                        <!-- FIM / CIDADE, ESTADO E CEP-->    

                        <!-- BOTOES /  INSERIR E FECHAR-->        
                        <button type="submit" value="Enviar" class="btn btn-primary">Inserir</button>
                        <button type="button" value="" class="btn btn-primary">Fechar</button>
                    </form>

                    <br><br>


                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Codigo</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Data de Nascimento</th>
                                <th scope="col">Editar</th>
                                <th scope="col">Deletar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cliente" items="${listaClientes}">
                            <tr>
                                <td><c:out value="${cliente.IdCliente}" /></td>	
                                <td><c:out value="${cliente.nome}" /></td>	
                                <td><c:out value="${cliente.cpf}" /></td>	
                                <td><c:out value="${cliente.dataNascimento}" /></td>	
                                <td><a href=clienteServlet?acao=EDITAR&IdCliente=${cliente.IdCliente}>Editar</a></td>
                                <td><a href=clienteServlet?acao=DELETAR&IdCliente=${cliente.IdCliente}>Excluir</a></td>
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
