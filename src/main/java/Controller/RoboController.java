package Controller;

import Model.BancoDeDados;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import Model.Robo;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe cotroller do robô.
 */
public class RoboController {
    private static long ultimoMovimento = 0, intervaloMovimento = 500_000_000;
    private static GridPane gridCidade;


    /**
     * Método que faz a animação do robô.
     */
    public static void iniciarAnimacao(Robo roboModelo, boolean[][] matrizCidade, GridPane gridCity) {
        gridCidade = gridCity;
        StackPane stackrobo = roboModelo.getRoboStack();
        new AnimationTimer() {
            @Override
            public void handle(long agora) {
                if (stackrobo != null && roboModelo.emMovimento() && (agora - ultimoMovimento) >= intervaloMovimento) {
                    roboModelo.mover(matrizCidade);
                    GridPane.setColumnIndex(stackrobo, roboModelo.getPosicaoX());
                    GridPane.setRowIndex(stackrobo, roboModelo.getPosicaoY());

                    ultimoMovimento = agora;
                }
            }
        }.start();
    }

    /**
     * Método que envia o destino do robô a seu modelo.
     * @param destinoX
     * @param destinoY
     */
    public static void definirDestino(Robo roboModelo, int destinoX, int destinoY) {
        roboModelo.destino(destinoX, destinoY);
    }

    /**
     * Método chamado ao clicar em uma célula do gridCidade, envia as posições da célula clicada ao roboController.
     * @param event
     */
    public static void clicarDestino(MouseEvent event, Robo robo, boolean[][] matrizCidade) {
        int destinoX = (int) (event.getX() / 30);
        int destinoY = (int) (event.getY() / 30);

        if (robo != null ) {
            robo.getRoboStack().setStyle("");
            if (robo.getPorcentoBateria() >= 1){
                if (!matrizCidade[destinoY][destinoX]){
                    definirDestino(robo,destinoX, destinoY);
                }
            } else {
                exibirMensagem();
            }
        }

    }

    public static void addRobo(StackPane robo, GridPane gridCidade, int posicaoY, int posicaoX) {
        gridCidade.add(robo, posicaoX, posicaoY);
    }

    public static void mostrarInfoRobo(Robo robo){
        Stage stage = new Stage();
        stage.setTitle("Informações do Robo");

        VBox rootVBox = new VBox(15);
        rootVBox.setAlignment(Pos.TOP_LEFT);
        rootVBox.setPadding(new Insets(15));

        Label nomeRobo = new Label("NOME: Robô "+robo.getNome());
        nomeRobo.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: Black;");

        ImageView imageRosto = new ImageView( new Image(RoboController.class.getResourceAsStream("/sprites/Robos/rostofeliz.png")));
        imageRosto.setFitWidth(50);
        imageRosto.setFitHeight(50);
        imageRosto.setPreserveRatio(true);
        imageRosto.setSmooth(true);

        Button botaoAcao = new Button("TRABALHAR");
        botaoAcao.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: Green;");

        Button mover = new Button("MOVER");
        mover.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: Black;");

        HBox informacao1 = new HBox(15);
        informacao1.setAlignment(Pos.CENTER);

        HBox informacao2 = new HBox(15);
        informacao2.setAlignment(Pos.CENTER);

        StackPane stackBateria = new StackPane();
        stackBateria.setAlignment(Pos.CENTER_LEFT);

        StackPane stackRosto = new StackPane();
        stackRosto.setAlignment(Pos.CENTER_LEFT);

        ImageView imagemBateria = new ImageView(robo.getImagemBateria().getImage());
        imagemBateria.setFitHeight(90);
        imagemBateria.setFitWidth(90);
        imagemBateria.setPreserveRatio(true);
        imagemBateria.setPreserveRatio(true);

        Button recarregar = new Button("RECARREGAR");
        recarregar.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: Blue;");
        stackBateria.getChildren().addAll(imagemBateria, recarregar);
        StackPane.setMargin(recarregar, new Insets(0, 0, 0, +200));

        StackPane.setMargin(mover, new Insets(0, 0, 0, +120));
        StackPane.setMargin(botaoAcao, new Insets(0, 0, 0, +200));
        stackRosto.getChildren().addAll(imageRosto,mover, botaoAcao);

        recarregar.setOnAction( actionEvent -> {
            if (BancoDeDados.getCidade().getBaterias() >= 0){
                robo.recarregarBateria();
                stage.close();
            };
        });

        botaoAcao.setOnAction( actionEvent -> {
            robo.acaoRobo();
            stage.close();
        });

        mover.setOnAction( actionEvent -> {
            stage.close();
        });

        informacao1.getChildren().addAll(stackBateria);
        informacao2.getChildren().addAll(stackRosto);
        rootVBox.getChildren().addAll(nomeRobo, informacao1,informacao2);
        stage.setScene(new Scene(rootVBox, 350, 150));
        stage.setResizable(false);
        stage.showAndWait();
    }

    public static void exibirMensagem() {
        //cria um novo stage
        Stage alertaStage = new Stage();
        alertaStage.setTitle("Alerta de Bateria");
        // Obtém a referência do Stage principal
        Stage stagePrincipal = (Stage) gridCidade.getScene().getWindow();
        // Define o Stage principal como Owner (dono)
        alertaStage.initOwner(stagePrincipal);
        // Define a modalidade como WINDOW_MODAL, serve para evitar que receba cliques na cidade
        alertaStage.initModality(Modality.WINDOW_MODAL);
        //cria um vbox para organizar os nodes certinho
        VBox rootVBox = new VBox(15);
        rootVBox.setPadding(new Insets(20));
        rootVBox.setAlignment(Pos.CENTER);
        // Label com a mensagem de falta de bateria
        Label mensagemLabel = new Label("O robo não possui energia suficiente");
        mensagemLabel.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold; -fx-text-fill: red;");
        //botão para fechar o stage
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> alertaStage.close());
        // Adiciona os componentes ao VBox
        rootVBox.getChildren().addAll(mensagemLabel, okButton);
        // crie, coloca o vbox e define as dimensões do scene
        Scene cena = new Scene(rootVBox, 350, 150);
        alertaStage.setScene(cena);
        //não deixa o stage ser colocado como full e mostra o stage
        alertaStage.setResizable(false);
        alertaStage.showAndWait();
    }
}
