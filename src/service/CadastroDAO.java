package service;

import java.sql.SQLException;
import java.util.List;

import models.Cadastro;

public interface CadastroDAO {
	void inserir(Cadastro c);
	void atualizar(Cadastro c);
	void remover(Cadastro c);
	List<Cadastro> pesquisarPorEmail(String email);
	List<Cadastro> pesquisarTodos();	
}