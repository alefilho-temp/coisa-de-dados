package components;

import java.util.List;

import common.Utils;
import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views.CartView;
import views.HomeView;
import views.SearchView;

public class NavComponent extends HBox {

    protected final ImageView imageLogo;
    protected final HBox inputBox;
    protected final VBox textFieldBox;
    protected final AutoCompleteComponent autoCompleteComponent;
    public AutoCompleteComponent getAutoCompleteComponent() {
        return autoCompleteComponent;
    }

    protected final Button searchButton;
    protected final ImageView imageLupa;
    protected final ImageView imageCarrinho;

    public NavComponent() {
        // HBox Config

        setAlignment(javafx.geometry.Pos.CENTER);
        setPrefHeight(100.0);
        setPrefWidth(200.0);
        VBox.setMargin(this, (new Insets(-20)));
        setPadding(new Insets(20.0, 0.0, 20.0, 0.0));
        setStyle("-fx-border-color: transparent transparent gray transparent; -fx-border-width: 1;");
        // Create Image Logo

        imageLogo = new ImageView();

        imageLogo.setFitHeight(150.0);
        imageLogo.setFitWidth(100.0);
        imageLogo.setPickOnBounds(true);
        imageLogo.setPreserveRatio(true);
        imageLogo.setImage(Utils.getImage("assets/logo-02.png"));
        HBox.setMargin(imageLogo, new Insets(10.0, 20.0, 0.0, 20.0));
        imageLogo.setCursor(Cursor.HAND);
        imageLogo.setOnMouseClicked(event -> {
            ViewController.navigate(new HomeView());
        });

        // Create Input Box

        inputBox = new HBox();

        HBox.setHgrow(inputBox, javafx.scene.layout.Priority.ALWAYS);
        inputBox.setAlignment(javafx.geometry.Pos.CENTER);
        inputBox.setPrefHeight(100.0);
        inputBox.setPrefWidth(200.0);
        
        // Create T ext Field Box

        textFieldBox = new VBox();

        HBox.setHgrow(textFieldBox, javafx.scene.layout.Priority.ALWAYS);
        textFieldBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Create Text Field

        autoCompleteComponent = new AutoCompleteComponent(
            "Pesquise Aqui...", 
            List.of(
                "Galaxy Fold",
                "Galaxy Flip",
                "Galaxy Fold",
                "Galaxy Flip",
                "Galaxy Fold",
                "Galaxy Flip",
                "Galaxy Fold",
                "Galaxy Flip",
                "Xbox",
                "Celular",
                "Tv",
                "Air Fryer"
            )
        );

        autoCompleteComponent.getTextField().setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                ViewController.navigate(new SearchView(autoCompleteComponent.getTextField().getText()));
            }
        });

        // Create Search Button

        searchButton = new Button();

        HBox.setHgrow(searchButton, javafx.scene.layout.Priority.ALWAYS);
        searchButton.setMnemonicParsing(false);
        searchButton.setStyle("-fx-border-width: 0; -fx-background-color: rgb(252, 166, 45); -fx-border-insets: 0 0 0 0; -fx-border-radius: 0px 0px 0px 0px; -fx-background-radius: 0px 10px 10px 0px;");
        HBox.setMargin(searchButton, new Insets(-3.0, 0.0, 0.0, 0.0));



        // Create Imagem Lupa

        imageLupa = new ImageView();

        imageLupa.setFitHeight(30.0);
        imageLupa.setFitWidth(30.0);
        imageLupa.setPickOnBounds(true);
        imageLupa.setPreserveRatio(true);
        imageLupa.setImage(Utils.getImage("assets/lupa.png"));
        searchButton.setGraphic(imageLupa);
        searchButton.setCursor(Cursor.HAND);
        searchButton.setOnMouseClicked(event -> {
            ViewController.navigate(new SearchView(autoCompleteComponent.getTextField().getText()));
        });

        // Create Imagem Carrinho

        imageCarrinho = new ImageView();

        imageCarrinho.setFitHeight(50.0);
        imageCarrinho.setPickOnBounds(true);
        imageCarrinho.setPreserveRatio(true);
        imageCarrinho.setImage(Utils.getImage("assets/cart-02.png"));
        HBox.setMargin(imageCarrinho, new Insets(0.0, 20.0, 0.0, 20.0));
        imageCarrinho.setCursor(Cursor.HAND);
        imageCarrinho.setOnMouseClicked(event -> {
            ViewController.navigate(new CartView());
        });

        // Adds

        getChildren().add(imageLogo);
        // textFieldBox.getChildren().add(textField);
        textFieldBox.getChildren().add(autoCompleteComponent);
        inputBox.getChildren().add(textFieldBox);
        inputBox.getChildren().add(searchButton);
        getChildren().add(inputBox);
        getChildren().add(imageCarrinho);

    }
}
