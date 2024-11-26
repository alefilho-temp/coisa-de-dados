package views;

import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Tela inicial do sistema.
 * 
 * @author Your Name
 */
public class InitialScreenView extends FlowPane {

    /**
     * Botão para acesso à área do cliente.
     */
    protected final Button clientButton;
    /**
     * Botão para acesso à área do vendedor.
     */
    protected final Button sellerButton;
    /**
     * Separador entre os botões.
     */
    protected final VBox separator;
    /**
     * Contenedor horizontal para os botões.
     */
    protected final HBox hBox;

    /**
     * Construtor da classe InitialScreenView.
     */
    public InitialScreenView() {

        // Inicializa os elementos da interface
        hBox = new HBox();
        clientButton = new Button();
        separator = new VBox();
        sellerButton = new Button();

        // Configura as propriedades do FlowPane
        setAlignment(javafx.geometry.Pos.CENTER);
        setColumnHalignment(javafx.geometry.HPos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        // Configura as propriedades do HBox
        hBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Configura as propriedades do botão "Sou Cliente"
        clientButton.setMnemonicParsing(false);
        clientButton.setStyle("-fx-background-color: gray;");
        clientButton.setText("Sou Cliente");
        clientButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        clientButton.setTextFill(javafx.scene.paint.Color.WHITE);
        clientButton.setPadding(new Insets(10.0));
        clientButton.setCursor(Cursor.HAND);
        clientButton.setOnMouseClicked(event -> {
            ViewController.navigate(new EnterClientNameView()); 
        });

        // Configura as propriedades do separador
        separator.setAlignment(javafx.geometry.Pos.CENTER);
        separator.setPrefHeight(100.0);
        separator.setPrefWidth(1.0);
        separator.setStyle("-fx-background-color: black;");
        HBox.setMargin(separator, new Insets(0.0, 50.0, 0.0, 50.0));

        // Configura as propriedades do botão "Sou Vendedor"
        sellerButton.setMnemonicParsing(false);
        sellerButton.setStyle("-fx-background-color: gray;");
        sellerButton.setText("Sou Vendedor");
        sellerButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        sellerButton.setTextFill(javafx.scene.paint.Color.WHITE);
        sellerButton.setPadding(new Insets(10.0));
        sellerButton.setCursor(Cursor.HAND);
        sellerButton.setOnMouseClicked(event -> {
            ViewController.navigate(new SelectSellerType()); 
        });

        // Adiciona os botões e o separador ao HBox
        hBox.getChildren().add(clientButton);
        hBox.getChildren().add(separator);
        hBox.getChildren().add(sellerButton);
        
        // Adiciona o HBox ao FlowPane
        getChildren().add(hBox);
    }
}
