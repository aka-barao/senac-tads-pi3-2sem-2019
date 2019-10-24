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
    </head>
    <body>
        <h1>Hello World!</h1>
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
                                    <a href="/editar_produto?id=<c:out value='${produto.id}' />">Editar</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="/deletar_produto?id=<c:out value='${produto.id}' />">Deletar</a>                     
                                </td>
                            </tr>
                        </c:forEach>
                        </table>

                        </body>
                        </html>