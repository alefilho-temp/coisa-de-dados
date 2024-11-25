package service;

import java.util.List;

import models.CategoryModel;

public interface CategoryDAO {
	
	public void excluir(CategoryModel c) throws Exception;
	public void inserir(CategoryModel c) throws Exception;
	public void atualizar(CategoryModel c) throws Exception;
    public List<CategoryModel> pesquisarTodos() throws Exception;
    public List<CategoryModel> pesquisarNome(String nome) throws Exception;
	
}