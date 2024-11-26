package views;

import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class MenuView extends FlowPane {

    protected final VBox vBox;

    public MenuView() {
        // Inicializa os componentes
        vBox = new VBox();

        // Configurações do FlowPane
        setAlignment(javafx.geometry.Pos.CENTER);
        setColumnHalignment(javafx.geometry.HPos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        // Configurações do VBox
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setPrefHeight(200.0);
        vBox.setPrefWidth(100.0);

        Button castroButton = new Button();
        castroButton.setMnemonicParsing(false);
        castroButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        castroButton.setText("Cadastro");
        castroButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        castroButton.setPadding(new Insets(10.0));
        castroButton.setOnMouseClicked(event -> {
            ViewController.navigate(new CadastroView()); 
        });
        vBox.getChildren().add(castroButton);

        Button castroClienteButton = new Button();
        castroClienteButton.setMnemonicParsing(false);
        castroClienteButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        castroClienteButton.setText("Cadastro Cliente");
        castroClienteButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        castroClienteButton.setPadding(new Insets(10.0));
        castroClienteButton.setOnMouseClicked(event -> {
            ViewController.navigate(new ClienteView()); 
        });
        vBox.getChildren().add(castroClienteButton);

        Button castroVendedorButton = new Button();
        castroVendedorButton.setMnemonicParsing(false);
        castroVendedorButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        castroVendedorButton.setText("Cadastro Vendedor");
        castroVendedorButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        castroVendedorButton.setPadding(new Insets(10.0));
        castroVendedorButton.setOnMouseClicked(event -> {
            ViewController.navigate(new VendedorView()); 
        });
        vBox.getChildren().add(castroVendedorButton);

        Button castroVendedorEmpresarialButton = new Button();
        castroVendedorEmpresarialButton.setMnemonicParsing(false);
        castroVendedorEmpresarialButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        castroVendedorEmpresarialButton.setText("Cadastro Vendedor Empresarial");
        castroVendedorEmpresarialButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        castroVendedorEmpresarialButton.setPadding(new Insets(10.0));
        castroVendedorEmpresarialButton.setOnMouseClicked(event -> {
            ViewController.navigate(new Vendedor_EmpresarialView()); 
        });
        vBox.getChildren().add(castroVendedorEmpresarialButton);

        Button castroVendedorPessoalButton = new Button();
        castroVendedorPessoalButton.setMnemonicParsing(false);
        castroVendedorPessoalButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        castroVendedorPessoalButton.setText("Cadastro Vendedor Pessoal");
        castroVendedorPessoalButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        castroVendedorPessoalButton.setPadding(new Insets(10.0));
        castroVendedorPessoalButton.setOnMouseClicked(event -> {
            ViewController.navigate(new Vendedor_PessoalView()); 
        });
        vBox.getChildren().add(castroVendedorPessoalButton);

        Button produtoButton = new Button();
        produtoButton.setMnemonicParsing(false);
        produtoButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        produtoButton.setText("Cadastro Produto");
        produtoButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        produtoButton.setPadding(new Insets(10.0));
        produtoButton.setOnMouseClicked(event -> {
            ViewController.navigate(new ProductManagementView()); 
        });
        vBox.getChildren().add(produtoButton);

        Button cartaoButton = new Button();
        cartaoButton.setMnemonicParsing(false);
        cartaoButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        cartaoButton.setText("Cadastro Cartao");
        cartaoButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        cartaoButton.setPadding(new Insets(10.0));
        cartaoButton.setOnMouseClicked(event -> {
            ViewController.navigate(new CartaoView()); 
        });
        vBox.getChildren().add(cartaoButton);

        Button categoriaButton = new Button();
        categoriaButton.setMnemonicParsing(false);
        categoriaButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        categoriaButton.setText("Cadastro Categoria");
        categoriaButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        categoriaButton.setPadding(new Insets(10.0));
        categoriaButton.setOnMouseClicked(event -> {
            ViewController.navigate(new CategoryView()); 
        });
        vBox.getChildren().add(categoriaButton);

        Button telefoneButton = new Button();
        telefoneButton.setMnemonicParsing(false);
        telefoneButton.setStyle("-fx-background-color: rgb(242, 221, 137); -fx-border-radius: 10; -fx-border-color: transparent; -fx-background-radius: 10;");
        telefoneButton.setText("Cadastro Telefone");
        telefoneButton.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        telefoneButton.setPadding(new Insets(10.0));
        telefoneButton.setOnMouseClicked(event -> {
            ViewController.navigate(new TelefoneView()); 
        });
        vBox.getChildren().add(telefoneButton);

        getChildren().add(vBox);
    }
}

// cadastro 
// cadastro cliente
// cadastro vendedor
// cadastro vendedor pessoal
// cadastro vendedor empresarial
// produto
// cartao
// telefone
// categoria
