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
			<input required type="email" id="email" value="" name="email" />
			<input type="submit" value="Salvar"/>
		</form>
		
		<%
			@SuppressWarnings("unchecked")
			List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
			
			for(Cliente cliente : clientes) {
				out.println("<div>");
				out.println(cliente.getEmail() + "   <a href='javascript:confirma(\"" + cliente.getEmail() + "\")'>Excluir</a>");
				out.println("</div>");
			}
		%>
		<script>
			function confirma(email) {
				if(window.confirm("Tem certeza que deseja excluir?")) {
					window.location.href="cliente?cliente=" + email;
				}
			}
		</script>
	</body>
</html>