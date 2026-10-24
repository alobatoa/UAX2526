package grafica.experiencial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/grafica/experiencial/principal-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Gestión Northwind");
        stage.setScene(scene);
        stage.setWidth(900);
        stage.setHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
