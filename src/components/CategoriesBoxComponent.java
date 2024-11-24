package components;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;

public class CategoriesBoxComponent extends FlowPane {
    public CategoriesBoxComponent() {
        setAlignment(javafx.geometry.Pos.CENTER);
        setColumnHalignment(javafx.geometry.HPos.CENTER);
        setHgap(60.0);
        setVgap(20.0);
        setPadding(new Insets(20.0));

        // getChildren().add(vBox4);
    }
}
