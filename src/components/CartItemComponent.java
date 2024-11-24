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
import views.ProductView;

public class CartItemComponent extends HBox {

    protected final ImageView productImage;
    protected final VBox infoBox;
    protected final TextFlow productNameBox;
    protected final Text productName;
    protected final HBox priceBox;
    protected final Text productOldPrice;
    protected final Text productPrice;
    protected final HBox buttonsBox;
    protected final Button removeButton;
    protected final Text productQuantity;
    protected final Button addButton;

    public CartItemComponent(CartItemModel item) {

        setPrefHeight(200.0);
        setStyle("-fx-background-color: rgb(230, 229, 229); -fx-background-radius: 10;");


        
        infoBox = new VBox();
        infoBox.setPadding(new Insets(20.0));


        productNameBox = new TextFlow();



        productImage = new ImageView();

        productImage.setFitHeight(200.0);
        productImage.setFitWidth(200.0);
        productImage.setPickOnBounds(true);
        productImage.setPreserveRatio(true);
        productImage.setImage(Utils.getImage(item.getProduct().getImagePath()));
        HBox.setMargin(productImage, new Insets(20.0));
        productImage.setCursor(Cursor.HAND);
        productImage.setOnMouseClicked(event -> {
            ViewController.navigate(new ProductView(item.getProduct()));
        });




        productName = new Text();

        productName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productName.setStrokeWidth(0.0);
        productName.setText(item.getProduct().getName());
        productName.setFont(new Font(24.0));
        productName.setCursor(Cursor.HAND);
        productName.setOnMouseClicked(event -> {
            ViewController.navigate(new ProductView(item.getProduct()));
        });





        priceBox = new HBox();

        priceBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        priceBox.setPrefHeight(100.0);
        priceBox.setPrefWidth(200.0);
        VBox.setMargin(priceBox, new Insets(20.0, 0.0, 20.0, 0.0));








        productOldPrice = new Text();

        if (item.getProduct().getDiscount() > 0) {

            productOldPrice.setFill(javafx.scene.paint.Color.RED);
            productOldPrice.setStrikethrough(true);
            productOldPrice.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
            productOldPrice.setStrokeWidth(0.0);
            productOldPrice.setText("R$" + String.format("%.2f", item.getProduct().getPrice() - (item.getProduct().getPrice() * item.getProduct().getDiscount())));
            HBox.setMargin(productOldPrice, new Insets(0.0, 0.0, 10.0, 0.0));
            priceBox.getChildren().add(productOldPrice);
        }


        productPrice = new Text();

        productPrice.setFill(javafx.scene.paint.Color.valueOf("#499e35"));
        productPrice.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productPrice.setStrokeWidth(0.0);
        productPrice.setText("R$" + String.format("%.2f", item.getProduct().getPrice()));
        productPrice.setFont(new Font(20.0));








        buttonsBox = new HBox();

        buttonsBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        buttonsBox.setPrefHeight(100.0);
        buttonsBox.setPrefWidth(200.0);









        removeButton = new Button();

        removeButton.setMnemonicParsing(false);
        removeButton.setPrefHeight(30.0);
        removeButton.setPrefWidth(30.0);
        removeButton.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
        removeButton.setText("-");
        removeButton.setFont(new Font(14.0));
        removeButton.setCursor(Cursor.HAND);

        // TODO: Colocar a função do botao de remover





        productQuantity = new Text();

        productQuantity.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productQuantity.setStrokeWidth(0.0);
        productQuantity.setText(String.valueOf(item.getQuantity()));
        productQuantity.setFont(new Font(14.0));
        HBox.setMargin(productQuantity, new Insets(15.0));






        addButton = new Button();

        addButton.setMnemonicParsing(false);
        addButton.setPrefHeight(30.0);
        addButton.setPrefWidth(30.0);
        addButton.setStyle("-fx-background-color: rgb(252, 251, 240); -fx-background-radius: 10;");
        addButton.setText("+");
        addButton.setFont(new Font(14.0));
        addButton.setCursor(Cursor.HAND);
        

        // TODO: Colocar a função do botao de adicionar






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
}
