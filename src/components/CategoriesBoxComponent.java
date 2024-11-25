package components;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;

/**
 * Componente que representa uma caixa de categorias.
 * 
 * Este componente utiliza um FlowPane para organizar visualmente
 * as categorias de forma fluida e responsiva.
 */
public class CategoriesBoxComponent extends FlowPane {

    /**
     * Construtor do componente CategoriesBoxComponent.
     * 
     * Este construtor inicializa o layout do FlowPane, definindo
     * alinhamentos, espaçamentos e preenchimentos.
     */
    public CategoriesBoxComponent() {
        setAlignment(javafx.geometry.Pos.CENTER); // Alinha os filhos ao centro
        setColumnHalignment(javafx.geometry.HPos.CENTER); // Alinha as colunas ao centro
        setHgap(60.0); // Define o espaçamento horizontal entre os filhos
        setVgap(20.0); // Define o espaçamento vertical entre os filhos
        setPadding(new Insets(20.0)); // Define o preenchimento ao redor do FlowPane

        // getChildren().add(vBox4); // Exemplo de adição de componentes (comentado)
    }
}
