package components;

import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Componente que representa uma barra de retorno na interface do usuário.
 * 
 * Este componente permite que o usuário volte à tela anterior ao clicar no botão "Voltar".
 */
public class ReturnBarComponent extends FlowPane {

    protected final HBox returnButtonBox; // Caixa que contém o botão de retorno
    protected final Text returnButton; // Texto do botão de retorno

    /**
     * Construtor do componente ReturnBarComponent.
     * 
     * Este construtor inicializa as propriedades da barra de retorno,
     * incluindo estilo, alinhamento e funcionalidade do botão de retorno.
     */
    public ReturnBarComponent() {
        setStyle("-fx-border-color: black; -fx-border-width: 1;"); // Define o estilo da borda
        VBox.setMargin(this, new Insets(0, -20, 0, -20)); // Define as margens do componente

        returnButtonBox = new HBox(); // Criação da caixa para o botão de retorno
        returnButtonBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT); // Alinha o botão à esquerda
        returnButtonBox.setPrefHeight(30.0); // Define a altura preferencial da caixa
        returnButtonBox.setPadding(new Insets(0.0, 0.0, 0.0, 20.0)); // Define o preenchimento da caixa

        getChildren().add(returnButtonBox); // Adiciona a caixa de botão ao componente

        returnButton = new Text(); // Criação do texto do botão de retorno
        returnButton.setFill(javafx.scene.paint.Color.valueOf("#009dff")); // Define a cor do texto
        returnButton.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE); // Define o tipo de contorno do texto
        returnButton.setStrokeWidth(0.0); // Define a largura do contorno do texto
        returnButton.setText("Voltar"); // Define o texto do botão
        returnButton.setCursor(Cursor.HAND); // Define o cursor como mão ao passar sobre o botão

        // Define a ação ao clicar no botão de retorno
        returnButton.setOnMouseClicked(event -> {
            ViewController.navigateBack(); // Navega para a tela anterior
        });

        returnButtonBox.getChildren().add(returnButton); // Adiciona o texto do botão à caixa
    }
}