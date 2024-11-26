import common.DBConnection;
import common.Utils;
import common.ViewController;
import components.ContainerComponent;
import components.ReturnBarComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import views.CategoryView;
import views.InitialScreenView;
import views.MenuView;
import views.ProductManagementView;

/**
 * Classe principal da aplicação, responsável por inicializar e gerenciar a interface gráfica.
 */
public class App extends Application { 
    private ViewController viewController; // Controlador de visualização

    /**
     * Método que inicia a aplicação.
     * 
     * @param stage O palco principal da aplicação.
     */
    @Override
    public void start(Stage stage) { 
        try {
            DBConnection.getConnection(); // Estabelece conexão com o banco de dados
        } catch (Exception e) {
            System.out.println("Erro de conexão");
            System.out.println(e);
        }

        viewController = new ViewController(stage); // Inicializa o controlador de visualização

        InitialScreenView initialScreen = new InitialScreenView(); // Cria a visualização inicial (HomeView)

        Scene mainScene = new Scene(initialScreen, 1080, 720); // Cria a cena principal

        // Adiciona um filtro para eventos de teclado
        mainScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().toString().equals("F2")) { // Verifica se a tecla F2 foi pressionada
                ContainerComponent productManager = new ContainerComponent() {
                    {
                        body.getChildren().add(new ReturnBarComponent()); // Adiciona a barra de retorno
                        body.getChildren().add(new MenuView()); // Adiciona a visualização de gerenciamento de produtos
                    }
                };

                event.consume(); // Consome o evento para evitar propagação

                viewController.navigate(productManager); // Navega para a nova visualização
            }
        });

        stage.setScene(mainScene); // Define a cena principal no palco

        stage.getIcons().add(Utils.getImage("assets/icon.png")); // Define o ícone da aplicação
        stage.setTitle("MarketPlace"); // Define o título da janela
        stage.show(); // Exibe o palco
        stage.centerOnScreen(); // Centraliza o palco na tela
    }

    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        Application.launch(App.class, args); // Lança a aplicação
    }
}
