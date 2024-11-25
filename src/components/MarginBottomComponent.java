package components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Componente que representa um círculo utilizado como margem inferior.
 * 
 * Este componente é um círculo que pode ser utilizado para criar
 * um espaço visual ou uma margem em interfaces gráficas.
 */
public class MarginBottomComponent extends Circle {

    /**
     * Construtor do componente MarginBottomComponent.
     * 
     * Este construtor inicializa as propriedades do círculo,
     * definindo a cor de preenchimento, o raio, a cor da borda
     * e a visibilidade do componente.
     */
    public MarginBottomComponent() {
        setFill(Color.DODGERBLUE); // Define a cor de preenchimento do círculo
        setRadius(50.0); // Define o raio do círculo
        setStroke(Color.BLACK); // Define a cor da borda do círculo
        setStrokeType(javafx.scene.shape.StrokeType.INSIDE); // Define o tipo de borda
        setVisible(false); // Define o círculo como invisível por padrão
    }
}
