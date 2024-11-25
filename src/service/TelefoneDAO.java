package service;

import java.util.List;

import models.TelefoneModel;

public interface TelefoneDAO {

	public void excluir(TelefoneModel c) throws Exception;
	public void inserir(TelefoneModel c) throws Exception;
	public void atualizar(TelefoneModel c) throws Exception;
    public List<TelefoneModel> pesquisarTodos() throws Exception;
    public List<TelefoneModel> pesquisarNumero(int telefone) throws Exception;
}