package components;

import javafx.scene.shape.Circle;

public class MarginBottomComponent extends Circle {
    public MarginBottomComponent() {
        setFill(javafx.scene.paint.Color.DODGERBLUE);
        setRadius(50.0);
        setStroke(javafx.scene.paint.Color.BLACK);
        setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        setVisible(false);
    }
}
