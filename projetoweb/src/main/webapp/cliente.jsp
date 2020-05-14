<!DOCTYPE html>
<%@page import="br.com.projetoweb.model.Cliente"%>
<%@page import="java.util.List"%>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<title>Cadastrar cliente</title>
</head>
	<body>
		<div>
			<% 
			String msg = (String) request.getAttribute("msg");
			if(msg != null) {
				out.println("<p>" + request.getAttribute("msg") + "</p>");
			}
			%>
		</div>
	
		<form action="cliente" method="POST">
			<label for="email">E-mail:</label>
			<input type="email" id="email" value="" name="email" />
			<input type="submit" value="Salvar"/>
		</form>
		
		<%
			@SuppressWarnings("unchecked")
			List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
			
			for(Cliente cliente : clientes) {
				out.println("<p>" + cliente.getEmail() + "</p>");
			}
		%>
	</body>
</html>