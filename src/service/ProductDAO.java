package service;

import java.util.List;
import models.ProductModel;

/**
 * Interface que define as operações de acesso a dados para produtos.
 * 
 * Esta interface declara métodos para as operações de CRUD (Criar, Ler, Atualizar e Deletar)
 * relacionadas aos produtos.
 */
public interface ProductDAO {

    /**
     * Adiciona um novo produto à base de dados.
     * 
     * @param product O produto a ser adicionado.
     * @throws Exception Se ocorrer um erro ao adicionar o produto.
     */
    void addProduct(ProductModel product) throws Exception;

    /**
     * Recupera todos os produtos da base de dados.
     * 
     * @return Uma lista de todos os produtos.
     * @throws Exception Se ocorrer um erro ao recuperar os produtos.
     */
    List<ProductModel> getAllProducts() throws Exception;

    /**
     * Atualiza as informações de um produto na base de dados.
     * 
     * @param product O produto a ser atualizado.
     * @throws Exception Se ocorrer um erro ao atualizar o produto.
     */
    void updateProduct(ProductModel product) throws Exception;

    /**
     * Deleta um produto da base de dados.
     * 
     * @param product O produto a ser deletado.
     * @throws Exception Se ocorrer um erro ao deletar o produto.
     */
    void deleteProduct(ProductModel product) throws Exception;
}
