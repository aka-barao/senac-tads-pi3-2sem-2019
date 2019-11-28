<%-- 
    Document   : CadastroFuncionario
    Created on : 19/11/2019, 23:10:21
    Author     : Gabrielle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Funcionario</title>
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/simple-sidebar.css" rel="stylesheet">

        <style>

            .container{
                position: relative;
                top:100px;

            }

        </style>
    </head>
    <body>
        <!-- MENU--> 
        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="bg-light border-right" id="sidebar-wrapper">
                <div class="sidebar-heading">Scorpions </div>
                <div class="list-group list-group-flush">
                    <a href="index.html" class="list-group-item list-group-item-action bg-light">Home</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Venda</a>
                    <a href="ClienteServlet" class="list-group-item list-group-item-action bg-light">Cliente</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Relatório</a>
                    <a href="ProdutoServlet" class="list-group-item list-group-item-action bg-light">Produto</a>
                </div>

                <br>
                <h5>&nbspFuncionário</h5>
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action bg-light">Cadastrar Funcionário</a>
                    <a href="FuncionarioServlet" class="list-group-item list-group-item-action bg-light">Lista de Funcionários</a>

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
                    <h1>Cadastro Funcionário</h1>

                    <!-- INICIO DO FORM-->  
                    <form class="" method="post" action="/pi3-empresa-tades-1.0-SNAPSHOT/FuncionarioServlet" >
                        <input type="hidden" name="acao" value="CRIAR"/>
                        <input type="hidden" name="IdFuncionario" value="${funcinario.IdFuncionario}"/> 


                        <div>
                            <div class="form-group col-md-6">
                                <label for="inputPassword4">ID</label>
                                <input type="text" class="form-control" id="inputPassword4" placeholder="ID" disabled name="idProduto" value="${produto.id}">
                            </div>
                        </div>
                            
                        <!-- COMEÇO / NOME E CPF -->                  
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail4">Nome</label>
                                <input type="text" class="form-control" placeholder="Nome" name="nome" value="${funcinario.nome}">
                            </div>


                            <div class="form-group col-md-6">
                                <label for="inputEmail4">CPF</label>
                                <input type="text" class="form-control"  placeholder="CPF" name="cpf" value="${funcinario.cpf}">
                            </div>
                        </div>
                        <!-- FIM / NOME E CPF -->



                        <!-- COMEÇO / DATA DE NASCIMENTO-->        
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="txtDtNascimento">Data de nascimento</label>

                                <div>
                                    <input type="date" class="form-control" name="dtNascimento" value="${funcinario.dataNascimento}" >
                                    <div class="erro-input">
                                        Digite uma data válida.
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- FIM / DATA DE NASCIMENTO E SEXO-->             


                        <!-- BOTOES /  INSERIR E FECHAR-->
                        <button type="submit" class="btn btn-primary">Inserir</button>
                        <button type="button" class="btn btn-primary">Fechar</button>
                    </form>


                    <br><br>

                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Codigo</th>
                                <th scope="col">Nome</th>
                                <th scope="col">Descricao</th>
                                <th scope="col">Valor</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Editar</th>
                                <th scope="col">Deletar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="funcionario" items="${listaFuncionario}">
                            <tr>
                                <td><c:out value="${produto.id}" /></td>	
                                <td><c:out value="${produto.nome}" /></td>
                                <td><c:out value="${produto.descricao}" /></td>
                                <td><c:out value="${produto.valor}" /></td>
                                <td><c:out value="${produto.categoriaProduto.descricao}" /></td>
                                <td><a href=ProdutoServlet?acao=EDITAR&idProduto=${produto.id}>Editar</a></td>
                                <td><a href=ProdutoServlet?acao=DELETAR&idProduto=${produto.id}>Excluir</a></td>
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
