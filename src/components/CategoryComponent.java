package components;

import common.Utils;
import common.ViewController;
import javafx.animation.ScaleTransition;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import models.CategoryModel;
import views.SearchView;
import views.SearchView.SearchType;

/**
 * Componente que representa uma categoria.
 * 
 * Este componente exibe uma imagem representativa da categoria
 * e aplica efeitos visuais quando o mouse passa sobre ele.
 */
public class CategoryComponent extends VBox {

    protected final ImageView imageView; // Imagem da categoria

    /**
     * Construtor do componente CategoryComponent.
     * 
     * @param category O modelo da categoria que contém informações
     *                 sobre a cor e a imagem da categoria.
     */
    public CategoryComponent(CategoryModel category) {
        setAlignment(javafx.geometry.Pos.CENTER); // Alinha o conteúdo ao centro
        setMaxHeight(100.0); // Define a altura máxima
        setMaxWidth(100.0); // Define a largura máxima
        setPrefHeight(100.0); // Define a altura preferencial
        setPrefWidth(100.0); // Define a largura preferencial
        setStyle("-fx-background-radius: 50%; -fx-background-color: " + category.getColor() + ";"); // Estilo de fundo arredondado

        // Inicializa a imagem da categoria
        imageView = new ImageView();
        imageView.setFitHeight(100.0); // Define a altura da imagem
        imageView.setFitWidth(100.0); // Define a largura da imagem
        imageView.setPickOnBounds(true); // Permite clicar na imagem
        imageView.setPreserveRatio(true); // Mantém a proporção da imagem
        imageView.setImage(Utils.getImage(category.getImagePath())); // Carrega a imagem da categoria

        getChildren().add(imageView); // Adiciona a imagem ao componente

        // Efeito de escala ao passar o mouse sobre o componente
        setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), this);
            scaleTransition.setToX(1.05); // Aumenta a escala em X
            scaleTransition.setToY(1.05); // Aumenta a escala em Y
            scaleTransition.play(); // Executa a animação
            setStyle("-fx-background-radius: 50%; -fx-background-color: yellow;"); // Muda a cor de fundo ao passar o mouse
        });

        setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), this);
            scaleTransition.setToX(1.0); // Reverte a escala em X
            scaleTransition.setToY(1.0); // Reverte a escala em Y
            scaleTransition.play(); // Executa a animação
            setStyle("-fx-background-radius: 50%; -fx-background-color: " + category.getColor() + ";"); // Reverte a cor de fundo
        });

        setCursor(Cursor.HAND); // Define o cursor como mão
        setOnMouseClicked(event -> {
            ViewController.navigate(new SearchView(category.getName(), SearchType.category, category.getId())); // Navega para a visualização de busca da categoria
        });
    }
}
