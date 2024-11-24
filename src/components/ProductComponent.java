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

public class ProductComponent extends VBox  {
    protected final ImageView productImage;
    protected final Text productName;
    protected final HBox priceBox;
    protected final Text oldPrice;
    protected final Text price;

    public ProductComponent(ProductModel product) {

        setAlignment(javafx.geometry.Pos.TOP_CENTER);
        setMaxWidth(200.0);
        setPrefHeight(200.0);
        setPrefWidth(200.0);
        setSpacing(10.0);
        setPadding(new Insets(10.0));
        setStyle("-fx-background-color: transparent; -fx-background-radius: 10;");

        productImage = new ImageView();

        productImage.setFitHeight(200.0);
        productImage.setFitWidth(200.0);
        productImage.setPickOnBounds(true);
        productImage.setPreserveRatio(true);
        productImage.setImage(Utils.getImage(product.getImagePath()));



        productName = new Text();

        productName.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        productName.setStrokeWidth(0.0);
        productName.setText(product.getName());
        productName.setWrappingWidth(200.0);
        productName.setFont(new Font(16.0));




        priceBox = new HBox();

        priceBox.setPrefHeight(100.0);
        priceBox.setPrefWidth(200.0);

        oldPrice = new Text();
        price = new Text();

        if (product.getDiscount() > 0) {
            oldPrice.setFill(javafx.scene.paint.Color.RED);
            oldPrice.setStrikethrough(true);
            oldPrice.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
            oldPrice.setStrokeWidth(0.0);
            oldPrice.setText("R$" + String.format("%.2f", product.getPrice()));

            priceBox.getChildren().add(oldPrice);
        } 

        price.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        price.setStrokeWidth(0.0);
        price.setText("R$" + String.format("%.2f", product.getPrice() - (product.getPrice() * product.getDiscount())));
        price.setFont(new Font(20.0));





        getChildren().add(productImage);
        getChildren().add(productName);
        
        priceBox.getChildren().add(price);
        getChildren().add(priceBox);


        // Scale effect on hover
        setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), this);
            scaleTransition.setToX(1.05);
            scaleTransition.setToY(1.05);
            scaleTransition.play();
            setStyle("-fx-background-color: white; -fx-background-radius: 10;"); // Change background color on hover
        });

        setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), this);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
            setStyle("-fx-background-color: transparent; -fx-background-radius: 10;"); // Revert background color
        });


        setCursor(Cursor.HAND);
        setOnMouseClicked(event -> {
            ViewController.navigate(new ProductView(product));
        });
    }
}
