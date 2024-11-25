package service;

import java.util.List;

import models.Vendedor_Pessoal;

public interface Vendedor_PessoalDAO {
	void inserir(Vendedor_Pessoal v);
	void atualizar(Vendedor_Pessoal v);
	void remover(Vendedor_Pessoal v);
	List<Vendedor_Pessoal> pesquisarPorCPF(int CPF);
	List<Vendedor_Pessoal> pesquisarTodos();	
}