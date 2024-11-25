package models;

/**
 * Classe que representa um produto.
 * 
 * Esta classe armazena informações sobre um produto, incluindo um identificador único,
 * nome, preço, desconto, caminho da imagem, descrição, identificador da categoria e
 * identificador do vendedor.
 */
public class ProductModel {
    private int id = -1; // Identificador do produto

    /**
     * Obtém o identificador do produto.
     * 
     * @return O identificador do produto.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do produto.
     * 
     * @param id O identificador a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    private String name; // Nome do produto

    /**
     * Obtém o nome do produto.
     * 
     * @return O nome do produto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do produto.
     * 
     * @param name O nome a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    private float price; // Preço do produto

    /**
     * Obtém o preço do produto.
     * 
     * @return O preço do produto.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Define o preço do produto.
     * 
     * @param price O preço a ser definido.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    private float discount; // Desconto do produto

    /**
     * Obtém o desconto do produto.
     * 
     * @return O desconto do produto.
     */
    public float getDiscount() {
        return discount;
    }

    /**
     * Define o desconto do produto.
     * 
     * @param discount O desconto a ser definido.
     */
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    private String imagePath; // Caminho da imagem do produto

    /**
     * Obtém o caminho da imagem do produto.
     * 
     * @return O caminho da imagem.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Define o caminho da imagem do produto.
     * 
     * @param imagePath O caminho da imagem a ser definido.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String description; // Descrição do produto

    /**
     * Obtém a descrição do produto.
     * 
     * @return A descrição do produto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição do produto.
     * 
     * @param description A descrição a ser definida.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    private int categoryId; // Identificador da categoria do produto

    /**
     * Obtém o identificador da categoria do produto.
     * 
     * @return O identificador da categoria.
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Define o identificador da categoria do produto.
     * 
     * @param categoryId O identificador da categoria a ser definido.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private int sellerId; // Identificador do vendedor do produto

    /**
     * Obtém o identificador do vendedor do produto.
     * 
     * @return O identificador do vendedor.
     */
    public int getSellerId() {
        return sellerId;
    }

    /**
     * Define o identificador do vendedor do produto.
     * 
     * @param sellerId O identificador do vendedor a ser definido.
     */
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * Construtor padrão da classe ProductModel.
     */
    public ProductModel() {
        // Construtor vazio
    }

    /**
     * Construtor da classe ProductModel.
     * 
     * @param id O identificador do produto.
     * @param name O nome do produto.
     * @param price O preço do produto.
     * @param discount O desconto do produto.
     * @param imagePath O caminho da imagem do produto.
     * @param description A descrição do produto.
     * @param categoryId O identificador da categoria do produto.
     * @param sellerId O identificador do vendedor do produto.
     */
    public ProductModel(int id, String name, float price, float discount, String imagePath, String description, int categoryId, int sellerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.imagePath = imagePath;
        this.description = description;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
    }
}
