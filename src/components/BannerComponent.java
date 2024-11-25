package components;

import common.Utils;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

/**
 * Componente de Banner que exibe imagens em um layout horizontal.
 * 
 * Este componente é um TextFlow que contém um HBox com duas imagens de banner.
 */
public class BannerComponent extends TextFlow {

    protected final HBox bannerBox; // Caixa que contém as imagens do banner
    protected final ImageView bannerImage; // Primeira imagem do banner
    protected final ImageView bannerImage2; // Segunda imagem do banner

    /**
     * Construtor do componente BannerComponent.
     * 
     * Este construtor inicializa o layout e as imagens do banner.
     */
    public BannerComponent() {
        // Configuração do HBox
        setPrefHeight(200.0);
        setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        setPadding(new Insets(0, -20, 0, -20));
        VBox.setMargin(this, new Insets(10, -20, 0, -20));

        // Criação da caixa do banner
        bannerBox = new HBox();
        bannerBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        // Criação da primeira imagem do banner
        bannerImage = new ImageView();
        bannerImage.setFitHeight(200.0);
        bannerImage.setPickOnBounds(true);
        bannerImage.setPreserveRatio(true);
        bannerImage.setImage(Utils.getImage("assets/banner.jpg")); // Carrega a imagem do banner

        // Adiciona a primeira imagem do banner à caixa
        bannerBox.getChildren().add(bannerImage);

        // Criação da segunda imagem do banner
        bannerImage2 = new ImageView();
        bannerImage2.setFitHeight(200.0);
        bannerImage2.setPickOnBounds(true);
        bannerImage2.setPreserveRatio(true);
        bannerImage2.setImage(Utils.getImage("assets/banner.jpg")); // Carrega a imagem do banner

        // Adiciona a segunda imagem do banner à caixa
        bannerBox.getChildren().add(bannerImage2);
        
        // Adiciona a caixa do banner ao componente
        getChildren().add(bannerBox);
    }
}
