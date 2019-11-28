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
        <title>Cadastro de clientes</title>
</head>
<body>
	<form method="POST" action="ClienteServlet">
		 <input type="hidden" name="acao" value="CREATE"/>
		 <input type="hidden" name="IdCliente" value="${cliente.IdCliente}"/>
	     Codigo <input type="text" disabled name="IdCliente" value="${cliente.IdCliente}"/> <br>
	     Nome <input type="text" name="nome" value="${cliente.nome}"/> <br>
	     Cpf <input type="text"  name="cpf" value="${cliente.cpf}"/> <br>
	     Data de Nascimento <input type="text"  name="dtNascimento" value="${cliente.dataNascimento}"/> <br>
		 <input type="submit" value="Enviar" />
	</form>

	<font color="red"><h2>${mensagem}</h2></font>

	<h4>Clientes cadastrados</h4>
	<table border="1">
		<tr>
			<th>Codigo</th>
			<th>Nome </th>			
			<th>Cpf</th>
			
		</tr>
		<c:forEach var="cliente" items="${listaClientes}">
		<tr>
			<td>${cliente.IdCliente}</td>		
			<td>${cliente.nome}</td>
			<td>${cliente.cpf}</td>
                        <td>${cliente.dataNascimento}</td>
			<td><a href=clienteServlet?acao=RETRIEVE&IdCliente=${cliente.IdCliente}>Editar</a>
			<td><a href=clienteServlet?acao=DELETE&IdCliente=${cliente.IdCliente}>Excluir</a>
		</tr>	
		</c:forEach>	
	</table>
</body>
</html>
