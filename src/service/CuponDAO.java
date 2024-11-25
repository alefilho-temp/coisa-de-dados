package service;

import java.util.List;

import models.Cupon;

public interface CuponDAO {
    void inserir(Cupon c) throws Exception;
    void atualizar(Cupon c) throws Exception;
    void remover(Cupon c) throws Exception;
    List<Cupon> pesquisarPorNome(int porc) throws Exception;
    List<Cupon> pesquisarTodos() throws Exception;
}