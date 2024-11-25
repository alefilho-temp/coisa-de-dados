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

/**
 * Componente de navegação que representa a barra de navegação da aplicação.
 * 
 * Este componente contém um logotipo, uma barra de pesquisa e um ícone de carrinho.
 */
public class NavComponent extends HBox {

    protected final ImageView imageLogo; // Imagem do logotipo
    protected final HBox inputBox; // Caixa de entrada para pesquisa
    protected final VBox textFieldBox; // Caixa para o campo de texto
    protected final AutoCompleteComponent autoCompleteComponent; // Componente de autocompletar
    public AutoCompleteComponent getAutoCompleteComponent() {
        return autoCompleteComponent;
    }
    protected final Button searchButton; // Botão de pesquisa
    protected final ImageView imageLupa; // Ícone da lupa para pesquisa
    protected final ImageView imageCarrinho; // Ícone do carrinho

    /**
     * Construtor do componente NavComponent.
     * 
     * Este construtor inicializa as propriedades da barra de navegação,
     * incluindo o logotipo, a caixa de pesquisa e o ícone do carrinho.
     */
    public NavComponent() {
        // Configurações do HBox
        setAlignment(javafx.geometry.Pos.CENTER);
        setPrefHeight(100.0);
        setPrefWidth(200.0);
        VBox.setMargin(this, new Insets(-20));
        setPadding(new Insets(20.0, 0.0, 20.0, 0.0));
        setStyle("-fx-border-color: transparent transparent gray transparent; -fx-border-width: 1;");

        // Criação da imagem do logotipo
        imageLogo = new ImageView();
        imageLogo.setFitHeight(150.0);
        imageLogo.setFitWidth(100.0);
        imageLogo.setPickOnBounds(true);
        imageLogo.setPreserveRatio(true);
        imageLogo.setImage(Utils.getImage("assets/logo-02.png"));
        HBox.setMargin(imageLogo, new Insets(10.0, 20.0, 0.0, 20.0));
        imageLogo.setCursor(Cursor.HAND);
        imageLogo.setOnMouseClicked(event -> {
            ViewController.navigate(new HomeView()); // Navega para a visualização inicial ao clicar no logotipo
        });

        // Criação da caixa de entrada
        inputBox = new HBox();
        HBox.setHgrow(inputBox, javafx.scene.layout.Priority.ALWAYS);
        inputBox.setAlignment(javafx.geometry.Pos.CENTER);
        inputBox.setPrefHeight(100.0);
        inputBox.setPrefWidth(200.0);

        // Criação da caixa para o campo de texto
        textFieldBox = new VBox();
        HBox.setHgrow(textFieldBox, javafx.scene.layout.Priority.ALWAYS);
        textFieldBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Criação do campo de texto com autocompletar
        autoCompleteComponent = new AutoCompleteComponent(
            "Pesquise Aqui...", 
            List.of(
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
                ViewController.navigate(new SearchView(autoCompleteComponent.getTextField().getText())); // Navega para a visualização de busca ao pressionar Enter
            }
        });

        // Criação do botão de pesquisa
        searchButton = new Button();
        HBox.setHgrow(searchButton, javafx.scene.layout.Priority.ALWAYS);
        searchButton.setMnemonicParsing(false);
        searchButton.setStyle("-fx-border-width: 0; -fx-background-color: rgb(252, 166, 45); -fx-background-radius: 0px 10px 10px 0px;");
        HBox.setMargin(searchButton, new Insets(-3.0, 0.0, 0.0, 0.0));

        // Criação da imagem da lupa
        imageLupa = new ImageView();
        imageLupa.setFitHeight(30.0);
        imageLupa.setFitWidth(30.0);
        imageLupa.setPickOnBounds(true);
        imageLupa.setPreserveRatio(true);
        imageLupa.setImage(Utils.getImage("assets/lupa.png"));
        searchButton.setGraphic(imageLupa);
        searchButton.setCursor(Cursor.HAND);
        searchButton.setOnMouseClicked(event -> {
            ViewController.navigate(new SearchView(autoCompleteComponent.getTextField().getText())); // Navega para a visualização de busca ao clicar no botão de pesquisa
        });

        // Criação da imagem do carrinho
        imageCarrinho = new ImageView();
        imageCarrinho.setFitHeight(50.0);
        imageCarrinho.setPickOnBounds(true);
        imageCarrinho.setPreserveRatio(true);
        imageCarrinho.setImage(Utils.getImage("assets/cart-02.png"));
        HBox.setMargin(imageCarrinho, new Insets(0.0, 20.0, 0.0, 20.0));
        imageCarrinho.setCursor(Cursor.HAND);
        imageCarrinho.setOnMouseClicked(event -> {
            ViewController.navigate(new CartView()); // Navega para a visualização do carrinho ao clicar no ícone do carrinho
        });

        // Adiciona os componentes à barra de navegação
        getChildren().add(imageLogo);
        textFieldBox.getChildren().add(autoCompleteComponent); // Adiciona o componente de autocompletar à caixa de texto
        inputBox.getChildren().add(textFieldBox); // Adiciona a caixa de texto à caixa de entrada
        inputBox.getChildren().add(searchButton); // Adiciona o botão de pesquisa à caixa de entrada
        getChildren().add(inputBox); // Adiciona a caixa de entrada à barra de navegação
        getChildren().add(imageCarrinho); // Adiciona o ícone do carrinho à barra de navegação
    }
}