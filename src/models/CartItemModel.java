package models;

/**
 * Classe que representa um item no carrinho de compras.
 * 
 * Esta classe armazena informações sobre um produto, sua quantidade e um identificador único.
 */
public class CartItemModel {
    private int id = -1; // Identificador do item no carrinho

    /**
     * Obtém o identificador do item no carrinho.
     * 
     * @return O identificador do item.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do item no carrinho.
     * 
     * @param id O identificador a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    private ProductModel product; // Produto associado ao item do carrinho

    /**
     * Obtém o produto associado ao item do carrinho.
     * 
     * @return O produto associado.
     */
    public ProductModel getProduct() {
        return product;
    }

    /**
     * Define o produto associado ao item do carrinho.
     * 
     * @param product O produto a ser definido.
     */
    public void setProduct(ProductModel product) {
        this.product = product;
    }

    private int quantity; // Quantidade do produto no carrinho

    /**
     * Obtém a quantidade do produto no carrinho.
     * 
     * @return A quantidade do produto.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Define a quantidade do produto no carrinho.
     * 
     * @param quantity A quantidade a ser definida.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Construtor da classe CartItemModel.
     * 
     * @param id O identificador do item no carrinho.
     * @param product O produto associado ao item.
     * @param quantity A quantidade do produto no carrinho.
     */
    public CartItemModel(int id, ProductModel product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }
}
