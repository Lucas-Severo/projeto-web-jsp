<!DOCTYPE html>
<%@page import="br.com.projetoweb.model.Cliente"%>
<%@page import="java.util.List"%>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<title>Cadastrar cliente</title>
</head>
	<body>
		<form action="cliente" method="POST">
			<label for="email">E-mail:</label>
			<input type="email" id="email" value="" name="email" />
			<input type="submit" value="Salvar"/>
		</form>
		
		<%
			List<Cliente> lista = (List<Cliente>) request.getAttribute("clientes");
			for(Cliente cliente: lista) {
				out.print("<p>" + cliente.getEmail() + "</p>");
			}
		%>
	</body>
</html>