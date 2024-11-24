package components;

import common.Utils;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

public class BannerComponent extends TextFlow {

    protected final HBox bannerBox;
    protected final ImageView bannerImage;
    protected final ImageView bannerImage2;

    public BannerComponent() {
        // HBox Config
        
        setPrefHeight(200.0);
        setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        setPadding(new Insets(0, -20, 0, -20));
        VBox.setMargin(this, new Insets(10, -20, 0, -20));
        // Create Banner Box 

        bannerBox = new HBox();
        bannerBox.setAlignment(javafx.geometry.Pos.CENTER);
        

        // Create Banner Image

        bannerImage = new ImageView();

        bannerImage.setFitHeight(200.0);
        bannerImage.setPickOnBounds(true);
        bannerImage.setPreserveRatio(true);
        bannerImage.setImage(Utils.getImage("assets/banner.jpg")); // assets/banner.png

        // Add Banner Image
        bannerBox.getChildren().add(bannerImage);

        bannerImage2 = new ImageView();

        bannerImage2.setFitHeight(200.0);
        bannerImage2.setPickOnBounds(true);
        bannerImage2.setPreserveRatio(true);
        bannerImage2.setImage(Utils.getImage("assets/banner.jpg"));

        bannerBox.getChildren().add(bannerImage2);
        
        // Add Banner Box

        getChildren().add(bannerBox);

    }
}



/*
 * 
 *package components;

import common.Utils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

public class BannerComponent extends HBox {

    protected final TextFlow bannerBox;
    protected final ImageView bannerImage;
    protected final ImageView bannerImage2;

    public BannerComponent() {
        // HBox Config
        
        setAlignment(javafx.geometry.Pos.TOP_CENTER);

        // Create Banner Box 

        bannerBox = new TextFlow();
        
        bannerBox.setPrefHeight(200.0);
        bannerBox.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Create Banner Image

        bannerImage = new ImageView();

        bannerImage.setFitHeight(200.0);
        bannerImage.setPickOnBounds(true);
        bannerImage.setPreserveRatio(true);
        bannerImage.setImage(Utils.getImage("assets/banner.jpg")); // assets/banner.png

        // Add Banner Image
        bannerBox.getChildren().add(bannerImage);

        bannerImage2 = new ImageView();

        bannerImage2.setFitHeight(200.0);
        bannerImage2.setPickOnBounds(true);
        bannerImage2.setPreserveRatio(true);
        bannerImage2.setImage(Utils.getImage("assets/banner.jpg"));

        bannerBox.getChildren().add(bannerImage2);
        
        // Add Banner Box

        getChildren().add(bannerBox);

    }
}

 */