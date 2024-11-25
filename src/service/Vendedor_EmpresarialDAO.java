package service;

import java.util.List;

import models.Vendedor_Empresarial;

public interface Vendedor_EmpresarialDAO {
	void inserir(Vendedor_Empresarial v);
	void atualizar(Vendedor_Empresarial v);
	void remover(Vendedor_Empresarial v);
	List<Vendedor_Empresarial> pesquisarPorCNPJ(int CNPJ);
	List<Vendedor_Empresarial> pesquisarTodos();	
}