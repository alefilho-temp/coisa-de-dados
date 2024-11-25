package components;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Componente que representa um contêiner rolável.
 * 
 * Este componente utiliza um ScrollPane para permitir que seu conteúdo
 * seja rolável verticalmente, mantendo um layout em VBox.
 */
public class ContainerComponent extends ScrollPane {

    protected final VBox body; // Corpo do contêiner

    /**
     * Construtor do componente ContainerComponent.
     * 
     * Este construtor inicializa as propriedades do ScrollPane e do VBox
     * que servirá como corpo do contêiner.
     */
    public ContainerComponent() {
        setFitToWidth(true); // Ajusta a largura do ScrollPane para caber no espaço disponível
        setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER); // Desativa a barra de rolagem horizontal
        setMaxHeight(USE_PREF_SIZE); // Define a altura máxima como o tamanho preferido
        setMaxWidth(USE_PREF_SIZE); // Define a largura máxima como o tamanho preferido
        setMinHeight(USE_PREF_SIZE); // Define a altura mínima como o tamanho preferido
        setMinWidth(USE_PREF_SIZE); // Define a largura mínima como o tamanho preferido

        // Inicializa o corpo do contêiner
        body = new VBox();
        body.setAlignment(javafx.geometry.Pos.CENTER); // Alinha o conteúdo ao centro
        body.setMaxHeight(1080.0); // Define a altura máxima do corpo
        body.setMaxWidth(1920.0); // Define a largura máxima do corpo
        body.setSpacing(20.0); // Define o espaçamento entre os filhos
        body.setPadding(new Insets(20.0)); // Define o preenchimento ao redor do corpo

        setContent(body); // Define o corpo como conteúdo do ScrollPane

        // Adicionar Margem
        // body.getChildren().add(new MarginBottomComponent()); // Exemplo de adição de componente (comentado)
    }
}
