<%-- 
    Document   : cliente
    Created on : 17/10/2019, 19:54:05
    Author     : gabrielle.csilva11
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Dados Cadastrado com sucesso</h1>
            <p><c:out value="${id}"/></p>
            <p><c:out value="${nome}"/></p>
            <p><c:out value="${cpf}"/></p>
            
    </body>
</html>
