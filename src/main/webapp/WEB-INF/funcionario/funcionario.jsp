<%-- 
    Document   : funcionario
    Created on : 19/11/2019, 14:25:15
    Author     : samue
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
	<form method="POST" action="FuncionarioServlet">
		 <input type="hidden" name="acao" value="CREATE"/>
		 <input type="hidden" name="IdFuncionario" value="${funcinario.IdFuncionario}"/>
	     Codigo <input type="text" disabled name="IdFuncionario" value="${funcinario.IdFuncionario}"/> <br>
	     Nome <input type="text" name="nome" value="${funcinario.nome}"/> <br>
	     Cpf <input type="text"  name="cpf" value="${funcinario.cpf}"/> <br>
	     Data de Nascimento <input type="text"  name="dtNascimento" value="${funcinario.dataNascimento}"/> <br>
		 <input type="submit" value="Enviar" />
	</form>

	<font color="red"><h2>${mensagem}</h2></font>

	<h4>Clientes cadastrados</h4>
	<table border="1">
		<tr>
			<th>Codigo</th>
			<th>Nome </th>			
			<th>Cpf</th>
                        <th>cargo</th>
                        <th>unidadeEmpresa</th>
                        <th>departamento</th>
			
		</tr>
		<c:forEach var="cliente" items="${listaFuncionario}">
		<tr>
			<td>${funcinario.IdFuncionario}</td>		
			<td>${funcinario.nome}</td>
			<td>${funcinario.cpf}</td>
                        <td>${funcinario.dataNascimento}</td>
                        <td>${funcinario.cargo}</td>
                        <td>${funcinario.unidadeEmpresa}</td>
                        <td>${funcinario.departamento}</td>
			<td><a href=FuncionarioServlet?acao=RETRIEVE&IdFuncionario=${funcinario.IdFuncionario}>Editar</a>
			<td><a href=FuncionarioServlet?acao=DELETE&IdFuncionario=${funcinario.IdFuncionario}>Excluir</a>
		</tr>	
		</c:forEach>	
	</table>
</body>
</html>
