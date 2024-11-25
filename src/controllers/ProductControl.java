package controllers;

import daos.ProductDAO;
import daos.ProductDAOImpl;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.ProductModel;

/**
 * Classe responsável pelo controle de produtos.
 * 
 * Esta classe gerencia as operações de CRUD (Criar, Ler, Atualizar e Deletar)
 * para os produtos, interagindo com a camada de acesso a dados.
 */
public class ProductControl {
    private ObservableList<ProductModel> productList = FXCollections.observableArrayList(); // Lista observável de produtos
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0); // ID do produto
    private SimpleStringProperty name = new SimpleStringProperty(""); // Nome do produto
    private SimpleFloatProperty price = new SimpleFloatProperty(0); // Preço do produto
    private SimpleFloatProperty discount = new SimpleFloatProperty(0); // Desconto do produto
    private SimpleStringProperty imagePath = new SimpleStringProperty(""); // Caminho da imagem do produto
    private SimpleStringProperty description = new SimpleStringProperty(""); // Descrição do produto
    private SimpleIntegerProperty categoryId = new SimpleIntegerProperty(0); // ID da categoria do produto

    private ProductDAO productDAO; // Interface para acesso a dados de produtos

    /**
     * Construtor da classe ProductControl.
     * 
     * Este construtor inicializa a interface de acesso a dados e carrega todos os produtos.
     * 
     * @throws Exception Se ocorrer um erro ao inicializar o ProductDAO ou ao carregar os produtos.
     */
    public ProductControl() throws Exception {
        productDAO = new ProductDAOImpl(); // Inicializa o DAO de produtos
        loadAllProducts(); // Carrega todos os produtos
    }

    /**
     * Carrega todos os produtos da base de dados.
     * 
     * Este método limpa a lista de produtos atual e a preenche com os produtos
     * obtidos do DAO.
     * 
     * @throws Exception Se ocorrer um erro ao carregar os produtos.
     */
    public void loadAllProducts() throws Exception {
        productList.clear(); // Limpa a lista de produtos
        productList.addAll(productDAO.getAllProducts()); // Adiciona todos os produtos obtidos
    }

    /**
     * Adiciona um novo produto à base de dados.
     * 
     * @param product O produto a ser adicionado.
     * @throws Exception Se ocorrer um erro ao adicionar o produto.
     */
    public void addProduct(ProductModel product) throws Exception {
        productDAO.addProduct(product); // Adiciona o produto através do DAO
        loadAllProducts(); // Atualiza a lista de produtos após a adição
    }

    /**
     * Atualiza um produto existente na base de dados.
     * 
     * @param product O produto a ser atualizado.
     * @throws Exception Se ocorrer um erro ao atualizar o produto.
     */
    public void updateProduct(ProductModel product) throws Exception {
        productDAO.updateProduct(product); // Atualiza o produto através do DAO
        loadAllProducts(); // Atualiza a lista de produtos após a atualização
    }

    /**
     * Deleta um produto da base de dados.
     * 
     * @param product O produto a ser deletado.
     * @throws Exception Se ocorrer um erro ao deletar o produto.
     */
    public void deleteProduct(ProductModel product) throws Exception {
        productDAO.deleteProduct(product); // Deleta o produto através do DAO
        loadAllProducts(); // Atualiza a lista de produtos após a deleção
    }

    /**
     * Retorna a lista observável de produtos.
     * 
     * @return A lista de produtos.
     */
    public ObservableList<ProductModel> getProductList() {
        return productList; // Retorna a lista de produtos
    }

    // Métodos para obter as propriedades
    public SimpleIntegerProperty idProperty() {
        return id; // Retorna a propriedade ID
    }

    public SimpleStringProperty nameProperty() {
        return name; // Retorna a propriedade nome
    }

    public SimpleFloatProperty priceProperty() {
        return price; // Retorna a propriedade preço
    }

    public SimpleFloatProperty discountProperty() {
        return discount; // Retorna a propriedade desconto
    }

    public SimpleStringProperty imagePathProperty() {
        return imagePath; // Retorna a propriedade caminho da imagem
    }

    public SimpleStringProperty descriptionProperty() {
        return description; // Retorna a propriedade descrição
    }

    public SimpleIntegerProperty categoryIdProperty() {
        return categoryId; // Retorna a propriedade ID da categoria
    }
}
