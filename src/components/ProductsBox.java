package components;

import javafx.scene.layout.FlowPane;

public class ProductsBox extends FlowPane {
    public ProductsBox() {
        setAlignment(javafx.geometry.Pos.TOP_CENTER);
        setColumnHalignment(javafx.geometry.HPos.CENTER);
        setHgap(30.0);
        setPrefHeight(200.0);
        setPrefWidth(200.0);
        setMaxWidth(1000);
        setVgap(30.0);
    }
}
