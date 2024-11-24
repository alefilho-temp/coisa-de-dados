package components;

import common.Utils;
import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

public class ProductDetailsComponent extends TilePane {

    protected final ImageView productImage;
    protected final VBox detailsBox;
    protected final TextFlow productNameBox;
    protected final Text productName;
    protected final HBox priceBox;
    protected final Text oldPrice;
    protected final Text price;
    protected final Text descount;
    protected final WebView description;
    protected final VBox buttonsBox;
    protected final TextFlow genericInfoBox;
    protected final Text genericInfo;
    protected final TextFlow stockAvailabilityBox;
    protected final Text stockAvailability;
    protected final TextFlow quantityBox;
    protected final Text quantity;
    protected final HBox buyButtonBox;
    protected final Button buyButton;
    protected final HBox addToCartButtonBox;
    protected final Button addToCartButton;
    protected final Text sellerName;

    public ProductDetailsComponent(ProductModel product, SellerModel seller) {
        setAlignment(javafx.geometry.Pos.CENTER);
        setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        setPadding(new Insets(20));
        setVgap(30);
        setHgap(30);



        productImage = new ImageView();

        productImage.setFitWidth(300.0);
        productImage.setPickOnBounds(true);
        productImage.setPreserveRatio(true);
        productImage.setImage(Utils.getImage(product.getImagePath()));



        detailsBox = new VBox();

        detailsBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        detailsBox.setPrefWidth(400.0);
        detailsBox.setSpacing(50.0);


        productNameBox = new TextFlow();


        
        productName = new Text();

        productName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productName.setStrokeWidth(0.0);
        productName.setText(product.getName());
        productName.setFont(new Font(24.0));



        priceBox = new HBox();



        oldPrice = new Text();

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





        // description = new Text();

        // description.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        // description.setStrokeWidth(0.0);
        // description.setText(product.getDescription());
        // description.setFont(new Font(14.0));

        description = new WebView();
        description.getEngine().loadContent("<html><head><style>* { font-family: roboto }</style></head><body>" + product.getDescription() + "</body></html>");
        description.setPrefWidth(USE_COMPUTED_SIZE);
        description.setPrefHeight(300);




        buttonsBox = new VBox();

        buttonsBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonsBox.setSpacing(30.0);
        buttonsBox.setPadding(new Insets(30.0));
        VBox.setMargin(buttonsBox, new Insets(30.0));
        buttonsBox.setStyle("-fx-border-color: black; -fx-border-radius: 20;");

        genericInfoBox = new TextFlow();

        genericInfoBox.setPrefWidth(100.0);



        genericInfo = new Text();

        genericInfo.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        genericInfo.setStrokeWidth(0.0);
        genericInfo.setText("Chegará grátis entre terça-feira e quinta-feira por ser sua primeira compra");
        genericInfo.setWrappingWidth(100.0);
        genericInfo.setFont(new Font(16.0));



        stockAvailabilityBox = new TextFlow();
        stockAvailability = new Text();

        stockAvailability.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        stockAvailability.setStrokeWidth(0.0);
        stockAvailability.setText("Estoque disponível:");
        stockAvailability.setFont(new Font(16.0));

        // TODO: Colocar estoque e disponibilidade


        quantityBox = new TextFlow();
        VBox.setMargin(quantityBox, (new Insets(-25, 0, 0, 0)));

        quantity = new Text();

        quantity.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        quantity.setStrokeWidth(0.0);
        quantity.setText("\t50 unidades disponíveis");
        quantity.setFont(new Font(14.0));



        buyButtonBox = new HBox();

        buyButtonBox.setAlignment(javafx.geometry.Pos.CENTER);


        

        buyButton = new Button();

        buyButton.setMnemonicParsing(false);
        buyButton.setStyle("-fx-background-color: rgb(253, 228, 42); -fx-background-radius: 10;");
        buyButton.setText("Comprar Agora");
        buyButton.setFont(new Font(18.0));
        buyButton.setPadding(new Insets(10.0, 50.0, 10.0, 50.0));
        buyButton.setCursor(Cursor.HAND);


        addToCartButtonBox = new HBox();

        addToCartButtonBox.setAlignment(javafx.geometry.Pos.CENTER);




        addToCartButton = new Button();

        addToCartButton.setMnemonicParsing(false);
        addToCartButton.setStyle("-fx-background-color: rgb(255, 244, 164); -fx-background-radius: 10;");
        addToCartButton.setText("Adicionar ao Carrinho");
        addToCartButton.setFont(new Font(18.0));
        addToCartButton.setPadding(new Insets(10.0, 50.0, 10.0, 50.0));
        addToCartButton.setCursor(Cursor.HAND);


        sellerName = new Text();

        sellerName.setFill(javafx.scene.paint.Color.valueOf("#004fff"));
        sellerName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        sellerName.setStrokeWidth(0.0);
        sellerName.setText(seller.getName());
        sellerName.setFont(new Font(16.0));
        sellerName.setCursor(Cursor.HAND);
        sellerName.setOnMouseClicked(event -> {
            ViewController.navigate(new SearchView(seller.getName(), SearchType.seller));
        });




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
