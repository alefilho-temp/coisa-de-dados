package models;

/**
 * Classe que representa uma categoria de produtos.
 * 
 * Esta classe armazena informações sobre a categoria, incluindo um identificador único,
 * nome, caminho da imagem e cor associada.
 */
public class CategoryModel {
    private int id = -1; // Identificador da categoria

    /**
     * Obtém o identificador da categoria.
     * 
     * @return O identificador da categoria.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da categoria.
     * 
     * @param id O identificador a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    private String name; // Nome da categoria

    /**
     * Obtém o nome da categoria.
     * 
     * @return O nome da categoria.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome da categoria.
     * 
     * @param name O nome a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    private String imagePath; // Caminho da imagem da categoria

    /**
     * Obtém o caminho da imagem da categoria.
     * 
     * @return O caminho da imagem.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Define o caminho da imagem da categoria.
     * 
     * @param imagePath O caminho da imagem a ser definido.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    private String color; // Cor associada à categoria

    /**
     * Obtém a cor associada à categoria.
     * 
     * @return A cor da categoria.
     */
    public String getColor() {
        return color;
    }

    /**
     * Define a cor associada à categoria.
     * 
     * @param color A cor a ser definida.
     */
    public void setColor(String color) {
        this.color = color;
    }

    public CategoryModel() {
        
    }

    /**
     * Construtor da classe CategoryModel.
     * 
     * @param id O identificador da categoria.
     * @param name O nome da categoria.
     * @param imagePath O caminho da imagem da categoria.
     * @param color A cor associada à categoria.
     */
    public CategoryModel(int id, String name, String imagePath, String color) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.color = color;
    }
}
