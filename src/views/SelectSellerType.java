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
public class SelectSellerType extends FlowPane {

    /**
     * Botão para acesso à área do cliente.
     */
    protected final Button bussinessSellerButton;
    /**
     * Botão para acesso à área do vendedor.
     */
    protected final Button personalSellerButton;
    /**
     * Separador entre os botões.
     */
    protected final VBox separator;
    /**
     * Contenedor horizontal para os botões.
     */
    protected final HBox hBox;

    /**
     * Construtor da classe SelectSellerType.
     */
    public SelectSellerType() {

        // Inicializa os elementos da interface
        hBox = new HBox();
        bussinessSellerButton = new Button();
        separator = new VBox();
        personalSellerButton = new Button();

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
        bussinessSellerButton.setMnemonicParsing(false);
        bussinessSellerButton.setStyle("-fx-background-color: gray;");
        bussinessSellerButton.setText("Sou Vendedor Empresarial");
        bussinessSellerButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        bussinessSellerButton.setTextFill(javafx.scene.paint.Color.WHITE);
        bussinessSellerButton.setPadding(new Insets(10.0));
        bussinessSellerButton.setCursor(Cursor.HAND);
        personalSellerButton.setOnMouseClicked(event -> {
            ViewController.navigate(new EnterSellerNameView()); 
        });

        // Configura as propriedades do separador
        separator.setAlignment(javafx.geometry.Pos.CENTER);
        separator.setPrefHeight(100.0);
        separator.setPrefWidth(1.0);
        separator.setStyle("-fx-background-color: black;");
        HBox.setMargin(separator, new Insets(0.0, 50.0, 0.0, 50.0));

        // Configura as propriedades do botão "Sou Vendedor"
        personalSellerButton.setMnemonicParsing(false);
        personalSellerButton.setStyle("-fx-background-color: gray;");
        personalSellerButton.setText("Sou Vendedor Pessoal");
        personalSellerButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        personalSellerButton.setTextFill(javafx.scene.paint.Color.WHITE);
        personalSellerButton.setPadding(new Insets(10.0));
        personalSellerButton.setCursor(Cursor.HAND);
        personalSellerButton.setOnMouseClicked(event -> {
            ViewController.navigate(new EnterSellerNameView()); 
        });

        // Adiciona os botões e o separador ao HBox
        hBox.getChildren().add(bussinessSellerButton);
        hBox.getChildren().add(separator);
        hBox.getChildren().add(personalSellerButton);
        
        // Adiciona o HBox ao FlowPane
        getChildren().add(hBox);
    }
}
