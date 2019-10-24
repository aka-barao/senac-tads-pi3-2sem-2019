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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
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
                                                
                            </td>
                            </tr>
                        </c:forEach>
                        </table>
    </body>
</html>
