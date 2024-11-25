package components;

import common.Utils;
import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import models.CartItemModel;
import views.CartView; // Importa a CartView para notificações
import views.ProductView;

/**
 * Componente que representa um item no carrinho de compras.
 * 
 * Este componente exibe a imagem do produto, nome, preço, quantidade e botões para
 * adicionar ou remover o item do carrinho.
 */
public class CartItemComponent extends HBox {
    protected final ImageView productImage; // Imagem do produto
    protected final VBox infoBox; // Caixa de informações do produto
    protected final TextFlow productNameBox; // Caixa para o nome do produto
    protected final Text productName; // Nome do produto
    protected final HBox priceBox; // Caixa para os preços
    protected final Text productOldPrice; // Preço antigo (com desconto)
    protected final Text productPrice; // Preço atual do produto
    protected final HBox buttonsBox; // Caixa para os botões de quantidade
    protected final Button removeButton; // Botão para remover o item
    protected final Text productQuantity; // Texto que exibe a quantidade do produto
    protected final Button addButton; // Botão para adicionar o item

    private final CartItemModel item; // Referência ao modelo do item
    private final CartView cartView; // Referência à CartView para notificações

    /**
     * Construtor do componente CartItemComponent.
     *
     * @param cartView Referência à CartView para notificações de atualização
     * @param item Modelo do item do carrinho
     */
    public CartItemComponent(CartView cartView, CartItemModel item) {
        this.item = item; // Inicializa o item
        this.cartView = cartView; // Inicializa a referência à CartView

        setPrefHeight(200.0);
        setStyle("-fx-background-color: rgb(230, 229, 229); -fx-background-radius: 10;");

        // Inicializa a caixa de informações
        infoBox = new VBox();
        infoBox.setPadding(new Insets(20.0));

        // Inicializa a caixa para o nome do produto
        productNameBox = new TextFlow();

        // Inicializa e configura a imagem do produto
        productImage = new ImageView();
        productImage.setFitHeight(200.0);
        productImage.setFitWidth(200.0);
        productImage.setPickOnBounds(true);
        productImage.setPreserveRatio(true);
        productImage.setImage(Utils.getImage(item.getProduct().getImagePath()));
        HBox.setMargin(productImage, new Insets(20.0));
        productImage.setCursor(Cursor.HAND);
        productImage.setOnMouseClicked(event -> {
            ViewController.navigate(new ProductView(item.getProduct())); // Navega para a visualização do produto
        });

        // Inicializa e configura o nome do produto
        productName = new Text();
        productName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productName.setStrokeWidth(0.0);
        productName.setText(item.getProduct().getName());
        productName.setFont(new Font(24.0));
        productName.setCursor(Cursor.HAND);
        productName.setOnMouseClicked(event -> {
            ViewController.navigate(new ProductView(item.getProduct())); // Navega para a visualização do produto
        });

        // Inicializa a caixa de preços
        priceBox = new HBox();
        priceBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        priceBox.setPrefHeight(100.0);
        priceBox.setPrefWidth(200.0);
        VBox.setMargin(priceBox, new Insets(20.0, 0.0, 20.0, 0.0));

        // Inicializa e configura o preço antigo (se houver desconto)
        productOldPrice = new Text();
        if (item.getProduct().getDiscount() > 0) {
            productOldPrice.setFill(javafx.scene.paint.Color.RED);
            productOldPrice.setStrikethrough(true);
            productOldPrice.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
            productOldPrice.setStrokeWidth(0.0);
            productOldPrice.setText("R$" + String.format("%.2f", item.getProduct().getPrice()));
            HBox.setMargin(productOldPrice, new Insets(0.0, 0.0, 10.0 , 0.0));
            priceBox.getChildren().add(productOldPrice);
        }

        // Inicializa e configura o preço atual do produto
        productPrice = new Text();
        productPrice.setFill(javafx.scene.paint.Color.valueOf("#499e35"));
        productPrice.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productPrice.setStrokeWidth(0.0);
        productPrice.setText("R$" + String.format("%.2f", item.getProduct().getPrice() - (item.getProduct().getPrice() * item.getProduct().getDiscount())));
        productPrice.setFont(new Font(20.0));

        // Inicializa a caixa de botões
        buttonsBox = new HBox();
        buttonsBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        buttonsBox.setPrefHeight(100.0);
        buttonsBox.setPrefWidth(200.0);

        // Inicializa e configura o botão de remover
        removeButton = new Button();
        removeButton.setMnemonicParsing(false);
        removeButton.setPrefHeight(30.0);
        removeButton.setPrefWidth(30.0);
        removeButton.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        removeButton.setText("-");
        removeButton.setFont(new Font(14.0));
        removeButton.setCursor(Cursor.HAND);

        // Ação do botão de remover
        removeButton.setOnAction(event -> {
            decrementQuantity(); // Chama o método para decrementar a quantidade
        });

        // Inicializa e configura o texto da quantidade do produto
        productQuantity = new Text();
        productQuantity.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productQuantity.setStrokeWidth(0.0);
        productQuantity.setText(String.valueOf(item.getQuantity()));
        productQuantity.setFont(new Font(14.0));
        HBox.setMargin(productQuantity, new Insets(15.0));

        // Inicializa e configura o botão de adicionar
        addButton = new Button();
        addButton.setMnemonicParsing(false);
        addButton.setPrefHeight(30.0);
        addButton.setPrefWidth(30.0);
        addButton.setStyle("-fx-background-color: rgb(252, 251, 240); -fx-background-radius: 10;");
        addButton.setText("+");
        addButton.setFont(new Font(14.0));
        addButton.setCursor(Cursor.HAND);

        // Ação do botão de adicionar
        addButton.setOnAction(event -> {
            incrementQuantity(); // Chama o método para incrementar a quantidade
        });

        // Adiciona os componentes à interface
        getChildren().add(productImage);
        productNameBox.getChildren().add(productName);
        infoBox.getChildren().add(productNameBox);
        priceBox.getChildren().add(productPrice);
        infoBox.getChildren().add(priceBox);
        buttonsBox.getChildren().add(removeButton);
        buttonsBox.getChildren().add(productQuantity);
        buttonsBox.getChildren().add(addButton);
        infoBox.getChildren().add(buttonsBox);
        getChildren().add(infoBox);
    }

    /**
     * Método para incrementar a quantidade do item.
     * Atualiza a quantidade e notifica a CartView.
     */
    private void incrementQuantity() {
        item.setQuantity(item.getQuantity() + 1);
        productQuantity.setText(String.valueOf(item.getQuantity()));
        cartView.updateItemQuantity(item); // Notifica a CartView para atualizar a interface
    }

    /**
     * Método para decrementar a quantidade do item.
     * Remove o item do carrinho se a quantidade chegar a zero.
     */
    private void decrementQuantity() {
        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            productQuantity.setText(String.valueOf(item.getQuantity()));
            cartView.updateItemQuantity(item); // Notifica a CartView para atualizar a interface
        } else {
            cartView.removeItem(item.getProduct().getId()); // Remove o item do carrinho
        }
    }
}
