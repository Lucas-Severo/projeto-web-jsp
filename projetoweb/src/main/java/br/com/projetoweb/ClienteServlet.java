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
		String id = req.getParameter("id");
		String action = req.getParameter("action");
		
		Cliente cliente = new Cliente(0L);
		cliente.setEmail("");
		
		// definir se o cliente vai ser deletado ou editado
		if(id != null && id != "" && action != null && action != "") {
			
			if(action.equals("delete")) {
				clienteService.excluirCliente(Long.parseLong(id));
			}
			
			if(action.equals("edit")) {
				try {
					cliente = ClienteService.getById(Long.parseLong(id));
				} catch (Exception exception) {
					System.err.println(exception.getMessage());
				}
			}

		}
		
		var dispatcher = req.getRequestDispatcher("/cliente.jsp");
		
		req.setAttribute("clientes", clienteService.getClientes());
		req.setAttribute("cliente", cliente);
		req.setAttribute("id", cliente.getId());
		
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		
		Cliente cliente;
		if(id != null && !id.equals("null") && !id.equals("0")) {
			cliente = new Cliente(Long.parseLong(id));
		} else {
			cliente = new Cliente();
		}
		cliente.setEmail(email);
		
		try {
			clienteService.salvar(cliente);
		} catch (Exception err) {
			System.err.println(err.getMessage());
		}
		
		Cliente cliente1 = new Cliente(0L);
		cliente1.setEmail("");
		
		var dispatcher = req.getRequestDispatcher("/cliente.jsp");
		req.setAttribute("msg", "Cadastrado com sucesso!");
		req.setAttribute("clientes", clienteService.getClientes());
		req.setAttribute("cliente", cliente1);
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
