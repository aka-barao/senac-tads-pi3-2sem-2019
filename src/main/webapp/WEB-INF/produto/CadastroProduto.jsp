<%-- 
    Document   : produto
    Created on : 26/10/2019, 11:59:49
    Author     : samue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos</title>
        <link href="vendor/Css/bootstrap.min.css" rel="stylesheet">
        <link href="vendor/Css/simple-sidebar.css" rel="stylesheet">
        <style>

            .container{
                position: relative;
                top:100px;

            }

        </style>
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            
            <div class="bg-light border-right" id="sidebar-wrapper">
                <div class="sidebar-heading">Scorpions </div>
                <div class="list-group list-group-flush">
                    <a href="index.html" class="list-group-item list-group-item-action bg-light">Home</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Venda</a>
                    <a href="ClienteServlet" class="list-group-item list-group-item-action bg-light">Cliente</a>
                    <a href="FuncionarioServlet" class="list-group-item list-group-item-action bg-light">Funcionário</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Relatório</a>
                </div>

                <br>
                <h5>&nbspCliente</h5>
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action bg-light">Cadastrar produto</a>
                    <a href="ProdutoServlet" class="list-group-item list-group-item-action bg-light">Lista de Produtos</a>

                </div>
            </div>
            <!-- /#sidebar-wrapper -->

            <div id="page-content-wrapper">  

                <!-- CONTAINER-->        
                <div class="container">

                    <!-- H1-->        
                    <h1 class="col-md-6 offset-md-3">Cadastro de produtos</h1>

                    <!-- INICIO DO FORM-->        
                    <form class="" method="post" action="/pi3-empresa-tades-1.0-SNAPSHOT/ProdutoServlet" >
                        <input type="hidden" name="acao" value="CRIAR"/>
                        <input type="hidden" name="idProduto" value="${produto.id}"/>
                        
                        <div>
                            <div class="form-group col-md-6">
                                <label for="inputPassword4">ID</label>
                                <input type="text" class="form-control" id="inputPassword4" placeholder="ID" disabled name="idProduto" value="${produto.id}">
                            </div>
                        </div>

                        <!-- COMEÇO / NOME E DESCRICAO -->       
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail4">Nome</label>
                                <input type="text" class="form-control" id="inputEmail4" placeholder="Nome" name="nome" value="${produto.nome}">
                            </div>

                            <div class="form-group col-md-6">
                                <label for="inputEmail4">Descricao</label>
                                <input type="text" class="form-control" id="inputEmail4" placeholder="Descricao" name="descricao" value="${produto.descricao}">
                            </div>
                        </div>
                        <!-- FIM / NOME E DESCRICAO -->    

                        <!-- COMEÇO/ VALOR E CATEGORIA -->  
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail4">Valor</label>
                                <input type="text" class="form-control" id="inputEmail4" placeholder="Valor" name="valor" value="${produto.valor}">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="exampleFormControlSelect1">Categoria</label>
                                <select class="form-control" name="categoria" placeholder="${produto.categoriaProduto.descricao}" value="${produto.categoriaProduto.descricao}">
                                    <option>Smartphones</option>
                                    <option>Monitor</option>
                                    <option>Audio</option>
                                    <option>Notebooks</option>
                                    <option>Computadores</option>
                                    <option>Mouse</option>
                                    <option>Teclados</option>
                                </select>
                            </div>
                        </div>

                        <!-- FIM / VALOR E CATEGORIA -->
  

                        <!-- BOTOES /  INSERIR E FECHAR-->        
                        <button type="submit" value="Enviar" class="btn btn-primary">Inserir</button>
                        <button type="button" value="" class="btn btn-primary">Fechar</button>
                    </form>



                    <font color="red"><h2>${mensagem}</h2></font>
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
                            <c:forEach var="produto" items="${listaProdutos}">
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
                    <!-- TABLE ANTIGA - FUNCIONAL    
                    <h1>Lista de Produtos Cadastrados</h1>
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>			
                                <th>Descricao</th>
                                <th>Valor</th>
                                <th>dataCadastro</th>
                                <th>Categoria</th>
                                <th>Editar</th>
                                <th>Deletar</th>
                            </tr>
                        </thead>
                        <c:forEach var="produto" items="${listaProdutos}">
                            <tr>
                                <td><c:out value="${produto.id}" /></td>
                                <td><c:out value="${produto.nome}" /></td>
                                <td><c:out value="${produto.descricao}" /></td>
                                <td><c:out value="${produto.valor}" /></td>
                                <td><c:out value="${produto.dataCadastro}" /></td>
                                <td><c:out value="${produto.categoriaProduto.descricao}" /></td>
                                <td><a href=ProdutoServlet?acao=RETRIEVE&idProduto=${produto.id}>Editar</a>
                                <td><a href=ProdutoServlet?acao=DELETE&idProduto=${produto.id}>Excluir</a>
                            </tr>	
                        </c:forEach>	
                    </table>
                    <!-- TABLE ANTIGA - FUNCIONAL  -->  
                </div>
            </div>
        </div>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/js/bootstrap.bundle.min.js"></script>

        <!-- Menu Toggle Script -->
        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
        </script>
    </body>
</html>
