package views;
import common.DataHolder;
import common.Utils;
import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import models.Cliente;
import service.ClienteException;

public class EnterClientNameView extends FlowPane {

    protected final HBox hBox;
    protected final TextField textField;
    protected final Button button;

    public EnterClientNameView() {
        hBox = new HBox();
        textField = new TextField();
        button = new Button();

        setAlignment(javafx.geometry.Pos.CENTER);
        setColumnHalignment(javafx.geometry.HPos.CENTER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        hBox.setAlignment(javafx.geometry.Pos.CENTER);

        textField.setPrefHeight(40.0);
        textField.setPrefWidth(250.0);
        textField.setPromptText("Digite seu nome");
        textField.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 10 0 0 10;");
        textField.setFont(new Font(14.0));

        button.setMnemonicParsing(false);
        button.setStyle("-fx-background-color: gray; -fx-border-radius: 0 10 10 0; -fx-border-color: black; -fx-background-radius: 0 10 10 0;");
        button.setText("OK");
        button.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setPadding(new Insets(10.0));
        button.setOnMouseClicked(event -> {
            Cliente client;
            try {
                client = Utils.getClientByName(textField.getText());
                if (client != null) {
                    DataHolder.setClient(client);
                    ViewController.navigate(new HomeView()); 
                } else {
                    ViewController.showAlert("Erro", "Usuario Invalido");
                }
           
            } catch (ClienteException e) {
                e.printStackTrace();
            }
        });

        hBox.getChildren().add(textField);
        hBox.getChildren(). add(button);
        getChildren().add(hBox);
    }
}