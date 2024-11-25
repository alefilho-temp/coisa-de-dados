package components;

import common.Utils;
import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import models.ProductModel;
import models.SellerModel;
import views.SearchView;
import views.SearchView.SearchType;

/**
 * Componente que exibe os detalhes de um produto.
 * 
 * Este componente inclui a imagem do produto, seu nome, preços,
 * descrição, informações do vendedor e botões de ação.
 */
public class ProductDetailsComponent extends TilePane {

    protected final ImageView productImage; // Imagem do produto
    protected final VBox detailsBox; // Caixa que contém os detalhes do produto
    protected final TextFlow productNameBox; // Caixa para o nome do produto
    protected final Text productName; // Nome do produto
    protected final HBox priceBox; // Caixa que contém os preços
    protected final Text oldPrice; // Preço antigo do produto
    protected final Text price; // Preço atual do produto
    protected final Text descount; // Texto de desconto
    protected final WebView description; // Descrição do produto
    protected final VBox buttonsBox; // Caixa para os botões de ação
    protected final TextFlow genericInfoBox; // Caixa para informações genéricas
    protected final Text genericInfo; // Texto de informações genéricas
    protected final TextFlow stockAvailabilityBox; // Caixa para disponibilidade em estoque
    protected final Text stockAvailability; // Texto de disponibilidade em estoque
    protected final TextFlow quantityBox; // Caixa para quantidade disponível
    protected final Text quantity; // Texto de quantidade disponível
    protected final HBox buyButtonBox; // Caixa para o botão de compra
    protected final Button buyButton; // Botão de compra
    protected final HBox addToCartButtonBox; // Caixa para o botão de adicionar ao carrinho
    protected final Button addToCartButton; // Botão de adicionar ao carrinho
    protected final Text sellerName; // Nome do vendedor

    /**
     * Construtor do componente ProductDetailsComponent.
     * 
     * Este construtor inicializa as propriedades do componente de detalhes do produto,
     * incluindo a imagem, o nome, preços, descrição e botões de ação.
     * 
     * @param product O modelo do produto a ser exibido.
     * @param seller O modelo do vendedor do produto.
     */
    public ProductDetailsComponent(ProductModel product, SellerModel seller) {
        setAlignment(javafx.geometry.Pos.CENTER);
        setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        setPadding(new Insets(20));
        setVgap(30);
        setHgap(30);

        // Criação da imagem do produto
        productImage = new ImageView();
        productImage.setFitWidth(300.0);
        productImage.setPickOnBounds(true);
        productImage.setPreserveRatio(true);
        productImage.setImage(Utils.getImage(product.getImagePath()));

        // Criação da caixa de detalhes
        detailsBox = new VBox();
        detailsBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        detailsBox.setPrefWidth(400.0);
        detailsBox.setSpacing(50.0);

        // Criação da caixa para o nome do produto
        productNameBox = new TextFlow();
        productName = new Text();
        productName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productName.setStrokeWidth(0.0);
        productName.setText(product.getName());
        productName.setFont(new Font(24.0));

        // Criação da caixa de preços
        priceBox = new HBox();
        oldPrice = new Text();

        // Verifica se o produto tem desconto e ajusta os preços
        if (product.getDiscount() > 0) {
            oldPrice.setFill(javafx.scene.paint.Color.RED);
            oldPrice.setStrikethrough(true);
            oldPrice.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
            oldPrice.setStrokeWidth(0.0);
            oldPrice.setText("R$" + String.format("%.2f", product.getPrice()));
            priceBox.getChildren().add(oldPrice);
        }

        price = new Text();
        price.setFill(javafx.scene.paint.Color.valueOf("#2dc928"));
        price.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        price.setStrokeWidth(0.0);
        price.setText("R$" + String.format("%.2f", product.getPrice() - (product.getPrice() * product.getDiscount())));
        price.setFont(new Font(20.0));
        priceBox.getChildren().add(price);

        descount = new Text();
        if (product.getDiscount() > 0) {
            descount.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
            descount.setStrokeWidth(0.0);
            descount.setText("(" + String.format("%.0f", (product.getDiscount() * 100)) + "% de desconto)");
            descount.setFont(new Font(14.0));
            HBox.setMargin(descount, new Insets(3.0, 0.0, 0.0, 10.0));
            priceBox.getChildren().add(descount);
        }

        // Criação da descrição do produto
        description = new WebView();
        description.getEngine().loadContent("<html><head><style>* { font-family: roboto }</style></head><body>" + product.getDescription() + "</body></html>");
        description.setPrefWidth(USE_COMPUTED_SIZE);
        description.setPrefHeight(300);

        // Criação da caixa de botões
        buttonsBox = new VBox();
        buttonsBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonsBox.setSpacing(30.0);
        buttonsBox.setPadding(new Insets(30.0));
        VBox.setMargin(buttonsBox, new Insets(30.0));
        buttonsBox.setStyle("-fx-border-color: black; -fx-border-radius: 20;");

        // Criação da caixa para informações genéricas
        genericInfoBox = new TextFlow();
        genericInfoBox.setPrefWidth(100.0);
        genericInfo = new Text();
        genericInfo.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        genericInfo.setStrokeWidth(0.0);
        genericInfo.setText("Chegará grátis entre terça-feira e quinta-feira por ser sua primeira compra");
        genericInfo.setWrappingWidth(100.0);
        genericInfo.setFont(new Font(16.0));

        // Criação da caixa de disponibilidade em estoque
        stockAvailabilityBox = new TextFlow();
        stockAvailability = new Text();
        stockAvailability.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        stockAvailability.setStrokeWidth(0.0);
        stockAvailability.setText("Estoque disponível:");
        stockAvailability.setFont(new Font(16.0));

        // Criação da caixa para quantidade disponível
        quantityBox = new TextFlow();
        VBox.setMargin(quantityBox, (new Insets(-25, 0, 0, 0)));
        quantity = new Text();
        quantity.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        quantity.setStrokeWidth(0.0);
        quantity.setText("\t50 unidades disponíveis");
        quantity.setFont(new Font(14.0));

        // Criação da caixa para o botão de compra
        buyButtonBox = new HBox();
        buyButtonBox.setAlignment(javafx.geometry.Pos.CENTER);
        buyButton = new Button();
        buyButton.setMnemonicParsing(false);
        buyButton.setStyle("-fx-background-color: rgb(253, 228, 42); -fx-background-radius: 10;");
        buyButton.setText("Comprar Agora");
        buyButton.setFont(new Font(18.0));
        buyButton.setPadding(new Insets(10.0, 50.0, 10.0, 50.0));
        buyButton.setCursor(Cursor.HAND);

        // Criação da caixa para o botão de adicionar ao carrinho
        addToCartButtonBox = new HBox();
        addToCartButtonBox.setAlignment(javafx.geometry.Pos.CENTER);
        addToCartButton = new Button();
        addToCartButton.setMnemonicParsing(false);
        addToCartButton.setStyle("-fx-background-color: rgb(255, 244, 164); -fx-background-radius: 10;");
        addToCartButton.setText("Adicionar ao Carrinho");
        addToCartButton.setFont(new Font(18.0));
        addToCartButton.setPadding(new Insets(10.0, 50.0, 10.0, 50.0));
        addToCartButton.setCursor(Cursor.HAND);

        // Criação do nome do vendedor
        sellerName = new Text();
        sellerName.setFill(javafx.scene.paint.Color.valueOf("#004fff"));
        sellerName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        sellerName.setStrokeWidth(0.0);
        sellerName.setText(seller.getName());
        sellerName.setFont(new Font(16.0));
        sellerName.setCursor(Cursor.HAND);
        sellerName.setOnMouseClicked(event -> {
            ViewController.navigate(new SearchView(seller.getName(), SearchType.seller)); // Navega para a visualização de busca do vendedor ao clicar no nome
        });

        // Adiciona todos os componentes ao layout
        getChildren().add(productImage);
        productNameBox.getChildren().add(productName);
        detailsBox.getChildren().add(productNameBox);
        detailsBox.getChildren().add(priceBox);
        detailsBox.getChildren().add(description);
        getChildren().add(detailsBox);
        genericInfoBox.getChildren().add(genericInfo);
        buttonsBox.getChildren().add(genericInfoBox);
        stockAvailabilityBox.getChildren().add(stockAvailability);
        buttonsBox.getChildren().add(stockAvailabilityBox);
        quantityBox.getChildren().add(quantity);
        buttonsBox.getChildren().add(quantityBox);
        buyButtonBox.getChildren().add(buyButton);
        buttonsBox.getChildren().add(buyButtonBox);
        addToCartButtonBox.getChildren().add(addToCartButton);
        buttonsBox.getChildren().add(addToCartButtonBox);
        buttonsBox.getChildren().add(sellerName);
        getChildren().add(buttonsBox);
    }
}
