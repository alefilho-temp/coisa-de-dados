package models;

/**
 * Classe que representa um vendedor.
 * 
 * Esta classe armazena informações sobre um vendedor, incluindo um identificador único
 * e o nome do vendedor.
 */
public class SellerModel {
    private int id = -1; // Identificador do vendedor

    /**
     * Obtém o identificador do vendedor.
     * 
     * @return O identificador do vendedor.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do vendedor.
     * 
     * @param id O identificador a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    private String name; // Nome do vendedor

    /**
     * Obtém o nome do vendedor.
     * 
     * @return O nome do vendedor.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do vendedor.
     * 
     * @param name O nome a ser definido.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Construtor da classe SellerModel.
     * 
     * @param id O identificador do vendedor.
     * @param name O nome do vendedor.
     */
    public SellerModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
