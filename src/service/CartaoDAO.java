package service;

import java.util.List;
import models.Cartao;

public interface CartaoDAO {
	
    void inserir(Cartao c) throws Exception;
    void atualizar(Cartao c) throws Exception;
    void remover(Cartao c) throws Exception;
    List<Cartao> pesquisarPorNome(String nome) throws Exception;
    List<Cartao> pesquisarTodos() throws Exception;

}