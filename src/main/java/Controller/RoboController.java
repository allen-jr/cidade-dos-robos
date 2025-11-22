package Controller;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import Model.Robo;

/**
 * Classe cotroller do robô.
 */
public class RoboController {
    private static long ultimoMovimento = 0, intervaloMovimento = 500_000_000;
    private static ImageView sprite;
    private static GridPane gridCidade;


    /**
     * Método que faz a animação do robô.
     */
    public static void iniciarAnimacao(Robo roboModelo, boolean[][] matrizCidade, GridPane gridCity, ImageView imageRobo) {
        gridCidade = gridCity;
        sprite = imageRobo;
        new AnimationTimer() {
            @Override
            public void handle(long agora) {
                if (sprite != null && roboModelo.emMovimento() && (agora - ultimoMovimento) >= intervaloMovimento) {
                    roboModelo.mover(matrizCidade);

                    sprite.setImage(roboModelo.getSpriteAtual());

                    GridPane.setColumnIndex(sprite, roboModelo.getPosicaoX());
                    GridPane.setRowIndex(sprite, roboModelo.getPosicaoY());

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
    public static void clicarDestino(MouseEvent event, Robo robo, ImageView imageRobo, boolean[][] matrizCidade) {
        int destinoX = (int) (event.getX() / 30);
        int destinoY = (int) (event.getY() / 30);

        if (robo != null) {
            imageRobo.setStyle("");
            if (!matrizCidade[destinoY][destinoX]){
                definirDestino(robo,destinoX, destinoY);
            }
        }

    }

    public static void addRobo(ImageView robo, GridPane gridCidade, int posicaoY, int posicaoX) {
        gridCidade.add(robo, posicaoX, posicaoY);
    }

    /**
     * Getters.
     */
    public ImageView getRoboSprite() { return sprite; }
    public ImageView getSprite() { return sprite; }
}
