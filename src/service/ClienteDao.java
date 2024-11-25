package service;

import java.util.List;

import models.Cliente;

public interface ClienteDao {

	void gravar(Cliente c) throws ClienteException;
	List<Cliente> pesquisar(String nome) throws ClienteException; 
	void atualizar(Cliente c) throws ClienteException;
	void remover(Cliente c) throws ClienteException ;
	List<Cliente> pesquisarTodos() throws ClienteException;
	

}
