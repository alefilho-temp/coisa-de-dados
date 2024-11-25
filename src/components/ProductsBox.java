package components;

import javafx.scene.layout.FlowPane;

/**
 * Componente que representa uma caixa de produtos.
 * 
 * Este componente é um FlowPane que organiza os produtos de forma fluida,
 * permitindo que eles se ajustem ao espaço disponível na interface do usuário.
 */
public class ProductsBox extends FlowPane {

    /**
     * Construtor do componente ProductsBox.
     * 
     * Este construtor inicializa as propriedades do FlowPane, incluindo
     * alinhamento, espaçamento e dimensões.
     */
    public ProductsBox() {
        setAlignment(javafx.geometry.Pos.TOP_CENTER); // Alinha os itens no topo e ao centro
        setColumnHalignment(javafx.geometry.HPos.CENTER); // Alinha as colunas ao centro
        setHgap(30.0); // Espaçamento horizontal entre os itens
        setPrefHeight(200.0); // Altura preferencial da caixa
        setPrefWidth(200.0); // Largura preferencial da caixa
        setMaxWidth(1000); // Largura máxima da caixa
        setVgap(30.0); // Espaçamento vertical entre os itens
    }
}
