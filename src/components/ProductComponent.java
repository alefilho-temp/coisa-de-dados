package components;

import common.Utils;
import common.ViewController;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.ProductModel;
import views.ProductView;

/**
 * Componente que representa um produto na interface do usuário.
 * 
 * Este componente exibe a imagem do produto, seu nome e preços,
 * incluindo o preço antigo e o preço com desconto, se aplicável.
 */
public class ProductComponent extends VBox {

    protected final ImageView productImage; // Imagem do produto
    protected final Text productName; // Nome do produto
    protected final HBox priceBox; // Caixa que contém os preços
    protected final Text oldPrice; // Preço antigo do produto
    protected final Text price; // Preço atual do produto

    /**
     * Construtor do componente ProductComponent.
     * 
     * Este construtor inicializa as propriedades do componente de produto,
     * incluindo a imagem, o nome e os preços.
     * 
     * @param product O modelo do produto a ser exibido.
     */
    public ProductComponent(ProductModel product) {
        setAlignment(javafx.geometry.Pos.TOP_CENTER);
        setMaxWidth(200.0);
        setPrefHeight(200.0);
        setPrefWidth(200.0);
        setSpacing(10.0);
        setPadding(new Insets(10.0));
        setStyle("-fx-background-color: transparent; -fx-background-radius: 10;");

        // Criação da imagem do produto
        productImage = new ImageView();
        productImage.setFitHeight(200.0);
        productImage.setFitWidth(200.0);
        productImage.setPickOnBounds(true);
        productImage.setPreserveRatio(true);
        productImage.setImage(Utils.getImage(product.getImagePath()));

        // Criação do nome do produto
        productName = new Text();
        productName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productName.setStrokeWidth(0.0);
        productName.setText(product.getName());
        productName.setWrappingWidth(200.0);
        productName.setFont(new Font(16.0));

        // Criação da caixa de preços
        priceBox = new HBox();
        priceBox.setPrefHeight(100.0);
        priceBox.setPrefWidth(200.0);

        // Criação dos textos de preços
        oldPrice = new Text();
        price = new Text();

        // Verifica se o produto tem desconto e ajusta os preços
        if (product.getDiscount() > 0) {
            oldPrice.setFill(javafx.scene.paint.Color.RED);
            oldPrice.setStrikethrough(true);
            oldPrice.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
            oldPrice.setStrokeWidth(0.0);
            oldPrice.setText("R$" + String.format("%.2f", product.getPrice()));
            priceBox.getChildren().add(oldPrice); // Adiciona o preço antigo à caixa de preços
        } 

        price.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        price.setStrokeWidth(0.0);
        price.setText("R$" + String.format("%.2f", product.getPrice() - (product.getPrice() * product.getDiscount())));
        price.setFont(new Font(20.0));

        // Adiciona os componentes ao layout
        getChildren().add(productImage);
        getChildren().add(productName);
        priceBox.getChildren().add(price);
        getChildren().add(priceBox);

        // Efeito de escala ao passar o mouse
        setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), this);
            scaleTransition.setToX(1.05);
            scaleTransition.setToY(1.05);
            scaleTransition.play();
            setStyle("-fx-background-color: white; -fx-background-radius: 10;"); // Altera a cor de fundo ao passar o mouse
        });

        setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), this);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
            setStyle("-fx-background-color: transparent; -fx-background-radius: 10;"); // Reverte a cor de fundo
        });

        setCursor(Cursor.HAND); // Define o cursor como mão ao passar sobre o componente
        setOnMouseClicked(event -> {
            ViewController.navigate(new ProductView(product )); // Navega para a visualização do produto ao clicar no componente
        });
    }
}
