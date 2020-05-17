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
			
			Long id = (Long) request.getAttribute("id");
			Cliente clienteRequest = (Cliente) request.getAttribute("cliente");
			
			%>
		</div>
	
		<form action="cliente" method="POST">
			<input name="id" style="display: none;" value=<%=id%> />
			<label for="email">E-mail:</label>
			<input required type="email" id="email" value="<%= clienteRequest.getEmail() %>" name="email" />
			<input type="submit" value="Salvar"/>
		</form>
		
		<%
			@SuppressWarnings("unchecked")
			List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
			
			for(Cliente cliente : clientes) {
			%>
			<div>
				<span><%=cliente.getEmail()%></span>
				<a href="javascript:confirma(<%=cliente.getId()%>)">Excluir</a>
				<a href="cliente?id=<%=cliente.getId()%>&action=edit">Editar</a>
			</div>
			<%
			}
		%>
		<script>
			function confirma(id) {
				if(window.confirm("Tem certeza que deseja excluir?")) {
					window.location.href="cliente?id=" + id + "&action=delete";
				}
			}
		</script>
	</body>
</html>