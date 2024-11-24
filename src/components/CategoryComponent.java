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

public class CategoryComponent extends VBox {

    protected final ImageView imageView;

    public CategoryComponent(CategoryModel category) {
        setAlignment(javafx.geometry.Pos.CENTER);
        setMaxHeight(100.0);
        setMaxWidth(100.0);
        setPrefHeight(100.0);
        setPrefWidth(100.0);
        setStyle("-fx-background-radius: 50%; -fx-background-color: " + category.getColor() + ";");

        imageView = new ImageView();

        imageView.setFitHeight(100.0);
        imageView.setFitWidth(100.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(Utils.getImage(category.getImagePath()));

        getChildren().add(imageView);

         // Scale effect on hover
        setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), this);
            scaleTransition.setToX(1.05);
            scaleTransition.setToY(1.05);
            scaleTransition.play();
            setStyle("-fx-background-radius: 50%; -fx-background-color: yellow;"); // Change background color on hover
        });

        setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), this);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
            setStyle("-fx-background-radius: 50%; -fx-background-color: " + category.getColor() + ";"); // Revert background color
        });


        setCursor(Cursor.HAND);
        setOnMouseClicked(event -> {
            ViewController.navigate(new SearchView(category.getName(), SearchType.category));
        });
    }
}
