package br.com.projetoweb.service;

import java.util.ArrayList;
import java.util.List;

import br.com.projetoweb.model.Cliente;

public class ClienteService {

	private static List<Cliente> clientes = new ArrayList<Cliente>();
	
	public void cadastrar(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public void salvar(Cliente cliente) throws Exception{
		if(findById(cliente.getId())) {
			int index = clientes.indexOf(cliente);
			clientes.remove(index);
			clientes.add(index, cliente);
		} else {
			clientes.add(cliente);
		}
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	public void excluirCliente(Long id) {
		Cliente clienteC = new Cliente(id);
		
		if(clientes.contains(clienteC))
			clientes.remove(clienteC);

	}
	
	public static boolean findById(Long id) {
		return clientes.indexOf(new Cliente(id)) != -1;
	}

	public static Cliente getById(long id) throws Exception{
		Cliente cliente = new Cliente(id);
		
		int index = clientes.indexOf(cliente);
		if(index != -1) {
			return clientes.get(index);
		}
		
		throw new Exception("Cliente inexistente");
	}
	
}
