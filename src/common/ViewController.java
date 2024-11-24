package common;

import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Classe responsável pelo controle de navegação de visualizações em uma aplicação JavaFX.
 */
public class ViewController {
    private static Stage stage;
    private static ArrayList<Parent> history;

    /**
     * Construtor da classe ViewController.
     *
     * @param stage a janela principal da aplicação
     */
    public ViewController(Stage stage) {
        this.stage = stage;
        this.history = new ArrayList<Parent>();
    }

    /**
     * Navega para uma nova visualização.
     *
     * Este método navega para a visualização especificada, armazenando a visualização atual na história.
     *
     * @param root o nó raiz da nova visualização
     */
    public static void navigate(Parent root) {
        navigate(root, false);
    }

    /**
     * Navega para uma nova visualização, indicando se está voltando ou não.
     *
     * Este método navega para a visualização especificada. Se o parâmetro back for verdadeiro,
     * a visualização atual não será adicionada à história.
     *
     * @param root o nó raiz da nova visualização
     * @param back indica se a navegação é uma ação de voltar
     */
    public static void navigate(Parent root, boolean back) {
        if (!back) history.add(stage.getScene().getRoot());
        stage.getScene().setRoot(root);
        System.out.println("Navegando");
    }

    /**
     * Volta para a visualização anterior, se houver.
     *
     * Este método navega de volta para a última visualização armazenada na história.
     * Se não houver visualizações anteriores, uma mensagem será exibida.
     */
    public static void navigateBack() {
        if (history.size() > 0) {
            navigate(history.remove(history.size() - 1), true);
            System.out.println("Voltando");
        } else {
            System.out.println("Não tem pra onde voltar. Voltando");
        }
    }

    /**
     * Retorna a janela principal da aplicação.
     *
     * @return o objeto Stage que representa a janela principal
     */
    public static Stage getStage() {
        return stage;
    }
}
