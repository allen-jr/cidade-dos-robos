package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        HBox root = new HBox(10); // Espaçamento de 10 pixels
        Button jogar = new Button("Clique-me!");

        // Adicionar o componente ao layout
        root.getChildren().add(jogar);

        Scene scene = new Scene(root,300,300);
        stage.setScene(scene);
        stage.setTitle("MenuPrincipal");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
