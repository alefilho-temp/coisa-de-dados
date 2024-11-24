import java.net.MalformedURLException;

import common.Utils;
import common.ViewController;
import components.ContainerComponent;
import components.ReturnBarComponent;
import cruds.ProductManagementCrud;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import views.HomeView;

public class App extends Application { 
    private ViewController ViewController;

    @Override
    public void start(Stage stage) throws MalformedURLException { 
        ViewController = new ViewController(stage);

        HomeView home = new HomeView();

        Scene mainScene = new Scene(home, 1080, 720);

        mainScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().toString().equals("F2")) {
                ContainerComponent productManeger = new ContainerComponent() {
                    {
                        body.getChildren().add(new ReturnBarComponent());
                        body.getChildren().add(new ProductManagementCrud());
                    }
                };

                event.consume(); 

                ViewController.navigate(productManeger);
            }
        });
        
        stage.setScene(mainScene);

        stage.getIcons().add(Utils.getImage("assets/icon.png"));
        stage.setTitle("MarketPlace");
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
