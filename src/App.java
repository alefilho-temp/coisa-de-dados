import common.DBConnection;
import common.Utils;
import common.ViewController;
import components.ContainerComponent;
import components.ReturnBarComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import views.HomeView;
import views.ProductManagementView;

public class App extends Application {
    private ViewController viewController;

    @Override
    public void start(Stage stage) {
        initializeDatabaseConnection();

        viewController = new ViewController(stage);
        HomeView home = new HomeView();

        Scene mainScene = new Scene(home, 1080, 720);
        setupKeyEventFilter(mainScene);

        stage.setScene(mainScene);
        stage.getIcons().add(Utils.getImage("assets/icon.png"));
        stage.setTitle("MarketPlace");
        stage.show();
        stage.centerOnScreen();
    }

    private void initializeDatabaseConnection() {
        try {
            DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("Erro de conexão");
            e.printStackTrace();
        }
    }

    private void setupKeyEventFilter(Scene mainScene) {
        mainScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().toString().equals("F2")) {
                ContainerComponent productManager = createProductManager();
                event.consume();
                viewController.navigate(productManager);
            }
        });
    }

    private ContainerComponent createProductManager() {
        return new ContainerComponent() {{
            body.getChildren().add(new ReturnBarComponent());
            body.getChildren().add(new ProductManagementView());
        }};
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
