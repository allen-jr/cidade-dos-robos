import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principal do projeto.
 */
public class Launcher extends Application {

    /**
     * Método que faz as configurações iniciais do JavaFx.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("cidade.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 700);
        stage.setTitle("Cidade dos Robôs");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Método que carrega o JavaFX.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
