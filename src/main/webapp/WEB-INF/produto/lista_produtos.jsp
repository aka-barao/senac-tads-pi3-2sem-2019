<%-- 
    Document   : lista_produtos
    Created on : 17/10/2019, 20:09:01
    Author     : gabrielle.csilva11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de Produtos</title>
        <link rel="stylesheet" href="Css/StyleCadastroProduto.css" type="text/css">
    </head>
    <body>
        <header class="Menu">
            <a class="logo" href="index.html">
                <img id="logoTades" src="Imagens/logo.png" alt="Logo da Empresa Tades">
            </a>
            <ul class="MenuLinks">
			<li><a href="CadastroCliente.html">Cadastro de Clientes</a></li>
			<li><a href="CadastroFunc.html">Cadastro de Funcionario</a></li>
                        <li><a href="CadastroProduto.html">Cadastro de Produtos</a></li>
                        <li><a href="ProdutoServlet">Lista produtos</a></li>
                        <li><a href="ClienteServlet">Lista Cliente</a></li>
			<li id="Login"><a href="Login.html">Login</a></li>
            </ul>
	</header>
        <table>
            <caption><h2>Lista de Produtos Cadastrados<h2></caption>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Descrição</th>
                            <th>Valor</th>
                            <th>Data de Cadastro</th>
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
                                <td>
                                    <a href="ProdutoServlet?action=/produtos/editar_produto&id=${produto.id}">Editar</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="/deletar_produto?id=<c:out value='${produto.id}' />">Deletar</a>                     
                                </td>
                            </tr>
                        </c:forEach>
                        </table>

                        </body>
                        </html>