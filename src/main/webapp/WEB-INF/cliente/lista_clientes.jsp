<%-- 
    Document   : lista_clientes
    Created on : 24/10/2019, 15:52:00
    Author     : samue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Css/StyleCadastroCliente.css" type="text/css">
        <title>JSP Page</title>
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
                        <li><a href="ClienteServlet">Lista produtos</a></li>
			<li id="Login"><a href="Login.html">Login</a></li>
            </ul>
	</header>
        <table>
            <caption><h2>Lista de Clientes Cadastrados<h2></caption>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CPF</th>
                        </tr>

                        <c:forEach var="cliente" items="${listaClientes}">
                            <tr>
                                <td><c:out value="${cliente.idCliente}" /></td>
                                <td><c:out value="${cliente.nome}" /></td>
                                <td><c:out value="${cliente.CPF}" /></td>
                                <td>
                                    <a href="/clientes/editar_cliente?id=<c:out value='${cliente.idCliente}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="/clientes/atualizar_cliente?id=<c:out value='${cliente.idCliente}' />">Delete</a>    
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
    </body>
</html>
