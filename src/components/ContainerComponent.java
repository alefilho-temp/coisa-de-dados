package components;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ContainerComponent extends ScrollPane {

    protected final VBox body;

    public ContainerComponent() {

        setFitToWidth(true);
        setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);

        body = new VBox();

        body.setAlignment(javafx.geometry.Pos.CENTER);
        body.setMaxHeight(1080.0);
        body.setMaxWidth(1920.0);
        body.setSpacing(20.0);
        body.setPadding(new Insets(20.0));

        setContent(body);

        // Add Margin
        // body.getChildren().add(new MarginBottomComponent());
    }
}
