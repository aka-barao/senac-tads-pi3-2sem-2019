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

            <font color="red"><h2>${mensagem}</h2></font>

            <table border="1">
                <caption><h2>Lista de Produtos Cadastrados<h2></caption>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>			
                                <th>Descricao</th>
                                <th>Valor</th>
                                <th>dataCadastro</th>
                                <th>Categoria</th>
                            </tr>
                            <c:forEach var="produto" items="${listaProdutos}">
                                <tr>
                                    <td><c:out value="${produto.id}" /></td>
                                    <td><c:out value="${produto.nome}" /></td>
                                    <td><c:out value="${produto.descricao}" /></td>
                                    <td><c:out value="${produto.valor}" /></td>
                                    <td><c:out value="${produto.dataCadastro}" /></td>
                                    <td><c:out value="${produto.categoriaProduto.descricao}" /></td>
                                    <td><a href=ProdutoServlet?acao=RETRIEVE&IdProduto=${produto.id}>Editar</a>
                                    <td><a href=ProdutoServlet?acao=DELETE&idProduto=${produto.id}>Excluir</a>
                                </tr>	
                            </c:forEach>	
                            </table>
                            </body>
                            </html>
