package br.com.projetoweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.projetoweb.model.Cliente;
import br.com.projetoweb.service.ClienteService;

@WebServlet(urlPatterns = { "/cliente", "/clienteServlet", "/clienteController" })
public class ClienteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7371218363017857325L;
	
	private ClienteService clienteService;
	
	public ClienteServlet() {
		clienteService = new ClienteService();
		System.out.println("Construindo o servlet...");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("Inicializando o Servlet");
		super.init();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Chamando o Servlet");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cliente = req.getParameter("cliente");
		if(cliente != null) {
			clienteService.excluirCliente(cliente);
		}
		
		var dispatcher = req.getRequestDispatcher("/cliente.jsp");
		
		req.setAttribute("clientes", clienteService.getClientes());
		
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		
		Cliente cliente = new Cliente();
		cliente.setEmail(email);
			
		clienteService.cadastrar(cliente);
		
		var dispatcher = req.getRequestDispatcher("/cliente.jsp");
		req.setAttribute("msg", "Cadastrado com sucesso!");
		req.setAttribute("clientes", clienteService.getClientes());
		dispatcher.forward(req, resp);
		
		// resp.sendRedirect("cliente");
		
		//resp.setCharacterEncoding("UTF-8");
		//resp.getWriter().print("Chamou pelo método post enviando e-mail: " + email);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print("Chamou pelo método delete");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print("Chamou pelo método PUT");
	}
	
	@Override
	public void destroy() {
		System.out.println("Destruindo Servlet...");
		super.destroy();
	}
	
}
