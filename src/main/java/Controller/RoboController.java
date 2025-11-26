package Controller;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import Model.Robo;
import javafx.scene.layout.StackPane;

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

        if (robo != null) {
            robo.getRoboStack().setStyle("");
            if (!matrizCidade[destinoY][destinoX]){
                definirDestino(robo,destinoX, destinoY);
            }
        }

    }

    public static void addRobo(StackPane robo, GridPane gridCidade, int posicaoY, int posicaoX) {
        gridCidade.add(robo, posicaoX, posicaoY);
    }
}
