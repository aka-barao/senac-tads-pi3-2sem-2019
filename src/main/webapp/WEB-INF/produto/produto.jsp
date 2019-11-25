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
                    <a href="#" class="list-group-item list-group-item-action bg-light">Home</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Venda</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Cliente</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Funcionário</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Relatório</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Produto</a>
                </div>

                <br>
                <h5>&nbspCliente</h5>
                <div class="list-group list-group-flush">
                    <a href="#" class="list-group-item list-group-item-action bg-light">Cadastrar Cliente</a>
                    <a href="#" class="list-group-item list-group-item-action bg-light">Clientes Cadastrados</a>

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
                        <input type="hidden" name="acao" value="CREATE"/>
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
                        <!-- FIM / NOME E SOBRENOME -->    

                        <!-- COMEÇO/ CPF E RG -->  
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

                        <!-- FIM / CPF E RG -->

                        <!-- COMEÇO / DATA DE NASCIMENTO E SEXO
                            <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="txtDtNascimento">Data de nascimento</label>
                                            <input type="date" class="form-control" name="dtNascimento">
                                            <div class="erro-input">
                                                Digite uma data válida.
                                            </div>
                                    </div>
                             
                                     <div class="form-group col-md-6">
                                        <label for="exampleFormControlSelect1">Sexo</label>
                                        <select class="form-control">
                                            <option>Feminino</option>
                                            <option>Masculino</option>
                                        </select>
                                    </div>
                            </div>
                        <!-- FIM / DATA DE NASCIMENTO E SEXO-->            

                        <!-- COMEÇO / ENDERECO          
                                    <div class="form-group">
                                          <label for="inputAddress">Endereço</label>
                                          <input type="text" class="form-control" placeholder="Ex. Rua Ipanema, nº 0">
                                    </div>
                        <!-- FIM / ENDERECO-->                


                        <!-- COMEÇO / BAIRRO E CEP      
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                          <label for="inputAddress2">Bairro</label>
                                          <input type="text" class="form-control" placeholder="Ex. Jardim Veneza">
                                    </div>
                                    
                                    <div class="form-group col-md-6">
                                      <label for="inputAddress2">CEP</label>
                                      <input type="text" class="form-control" placeholder="CEP">
                                    </div>
                                    
                                </div>
                        <!-- FIM / BAIRRO E CEP-->     


                        <!-- COMEÇO /  CIDADE, ESTADO E CEP           
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="inputCity">Cidade</label>
                                        <input type="text" class="form-control" placeholder="Ex. São Paulo">
                                    </div>
                                    
                                    <div class="form-group col-md-4">
                                        <label for="inputCity">Estado</label>
                                        <input type="text" class="form-control" placeholder="Ex. SP">
                                    </div>
                                    
                                    <div class="form-group col-md-2">
                                        <label for="inputCEP">CEP</label>
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                        <!-- FIM / CIDADE, ESTADO E CEP-->    

                        <!-- BOTOES /  INSERIR E FECHAR-->        
                        <button type="submit" value="Enviar" class="btn btn-primary">Inserir</button>
                        <button type="button" value="" class="btn btn-primary">Fechar</button>
                    </form>




                    <!-- FORM ANTIGO         
                    <head>
                        <link rel="stylesheet" href="Css/StyleCadastroProduto.css" type="text/css">
                        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                        <title>Cadastro de Produto</title>
                    </head>
                    <body>
                        <header class="Menu">
                            <a class="logo" href="index.html">
                                <img id="logoTades" src="Imagens/logo.png" alt="Logo da Empresa Tades">
                            </a>
                            <ul class="MenuLinks">
                                <li id="Login"><a href="Login.html">Login</a></li>
                            </ul>
                        </header>
                        <div class ="Container_Form">
                            <form class="" method="post" action="/pi3-empresa-tades-1.0-SNAPSHOT/ProdutoServlet" >
                                <input type="hidden" name="acao" value="CREATE"/>
                                <div>
                                    <h1>Cadastro de Produto</h1>
                                </div>
                                <input type="hidden" name="idProduto" value="${produto.id}"/>
                                ID <input type="text" disabled name="idProduto" value="${produto.id}"/> <br>
                                Nome <input type="text" name="nome" value="${produto.nome}"/> <br>
                                Valor <input type="text" name="valor" value="${produto.valor}"/> <br>
                                Descricao <input type="text" name="descricao" value="${produto.descricao}"/> <br>
                
                                <input type="submit" value="Enviar" />
                            </form>
                    <!-- FORM ANTIGO -->        





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
                                <td><a href=ProdutoServlet?acao=RETRIEVE&idProduto=${produto.id}>Editar</a></td>
                                <td><a href=ProdutoServlet?acao=DELETE&idProduto=${produto.id}>Excluir</a></td>
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
