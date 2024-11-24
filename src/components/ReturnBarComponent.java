package components;

import common.ViewController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ReturnBarComponent extends FlowPane {

    protected final HBox returnButtonBox;
    protected final Text returnButton;

    public ReturnBarComponent() {
        setStyle("-fx-border-color: black; -fx-border-width: 1;");
        VBox.setMargin(this, (new Insets(0, -20, 0, -20)));

        returnButtonBox = new HBox();

        returnButtonBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        returnButtonBox.setPrefHeight(30.0);
        returnButtonBox.setPadding(new Insets(0.0, 0.0, 0.0, 20.0));

        getChildren().add(returnButtonBox);



        returnButton = new Text();

        returnButton.setFill(javafx.scene.paint.Color.valueOf("#009dff"));
        returnButton.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        returnButton.setStrokeWidth(0.0);
        returnButton.setText("Voltar");
        returnButton.setCursor(Cursor.HAND);
        returnButton.setOnMouseClicked(event -> {
            ViewController.navigateBack();
        });

        returnButtonBox.getChildren().add(returnButton);
    }
}
