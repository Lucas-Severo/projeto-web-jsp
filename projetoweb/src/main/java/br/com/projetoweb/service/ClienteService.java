package br.com.projetoweb.service;

import java.util.ArrayList;
import java.util.List;

import br.com.projetoweb.model.Cliente;

public class ClienteService {

	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public void cadastrar(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void excluirCliente(String email) {
		Cliente clienteC = new Cliente();
		clienteC.setEmail(email);
		
		if(clientes.contains(clienteC))
			clientes.remove(clienteC);

	}
	
}
