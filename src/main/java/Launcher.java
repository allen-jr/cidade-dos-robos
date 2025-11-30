import Model.BancoDeDados;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**Classe principal do projeto.
 */
public class Launcher extends Application {

    /**Método que faz as configurações iniciais do JavaFx.
     * @param stage stage principal que será exibido
     * @throws IOException tratamento de erros
     */
    @Override
    public void start(Stage stage) throws IOException {
        //carrega os dados
        BancoDeDados.carregar();
        //carrega o fxml
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("cidade.fxml"));
        //cria o scene com o fxml e com as dimensões
        Scene scene = new Scene(fxmlLoader.load(), 1280, 700);
        //coloca o título do stage
        stage.setTitle("ROBOTS CITY");
        //adiciona o scene no stage
        stage.setScene(scene);
        //mostra o stage
        stage.show();
        //evento de clique para fechar o stage
        stage.setOnCloseRequest(event -> {
            //salva os dados da cidade
            BancoDeDados.salvar();
            //fecha o stage
            Platform.exit();
            System.exit(0);
        });
    }

    /**Método que carrega o JavaFX.
     * @param args argumentos
     */
    public static void main(String[] args) {
        //inicia o jogo
        launch(args);
    }
}
