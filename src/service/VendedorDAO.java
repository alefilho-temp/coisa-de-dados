package service;

import java.util.List;

import models.Vendedor;

public interface VendedorDAO {
	void inserir(Vendedor v);
	void atualizar(Vendedor v);
	void remover(Vendedor v);
	List<Vendedor> pesquisarPorNome(String nome);
	List<Vendedor> pesquisarTodos();	
}