package views;
import common.DataHolder;
import common.Utils;
import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import models.Cliente;
import models.Vendedor;

public class EnterSellerNameView extends FlowPane {

    protected final HBox hBox;
    protected final TextField textField;
    protected final Button button;

    public EnterSellerNameView() {
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
        textField.setPromptText("Digite seu login");
        textField.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 10 0 0 10;");
        textField.setFont(new Font(14.0));

        button.setMnemonicParsing(false);
        button.setStyle("-fx-background-color: gray; -fx-border-radius: 0 10 10 0; -fx-border-color: black; -fx-background-radius: 0 10 10 0;");
        button.setText("OK");
        button.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setPadding(new Insets(10.0));
        button.setCursor(Cursor.HAND);
        button.setOnMouseClicked(event -> {
            Vendedor seller = Utils.getSellerByName(textField.getText());
            if (seller != null) {
                DataHolder.setSeller(seller);
                ViewController.navigate(new MenuView()); // mandar para a tela dos crud
            } else {
                ViewController.showAlert("Erro", "Vendedor Invalido");
            }
        });

        hBox.getChildren().add(textField);
        hBox.getChildren(). add(button);
        getChildren().add(hBox);
    }
}
