package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Robo;

/**
 * Classe cotroller do robô.
 */
public class RoboController {
    private long ultimoMovimento = 0, intervaloMovimento = 500_000_000;
    private Robo roboModelo;
    private GridPane gridCidade;
    private ImageView sprite;
    private boolean[][] matrizCidade;

    /**
     * Construtor da classe que recebe as posições iniciais do robô e o GridPane da cidade. Faz as configurações do robô, o posiciona no Grid e chama a sua animação.
     * @param posicaoInicialX
     * @param posicaoInicialY
     * @param gridCidade
     */
    public RoboController(int posicaoInicialX, int posicaoInicialY, GridPane gridCidade, boolean[][] matrizCidade, Robo robo) {
        this.roboModelo = robo;
        this.gridCidade = gridCidade;
        this.sprite = new ImageView(robo.getSpriteAtual());
        this.matrizCidade = matrizCidade;

        sprite.setFitWidth(32);
        sprite.setFitHeight(32);
        sprite.setPreserveRatio(true);
        sprite.setSmooth(true);

        gridCidade.add(sprite, posicaoInicialX, posicaoInicialY);
        iniciarAnimacao();
    }

    /**
     * Método que faz a animação do robô.
     */
    private void iniciarAnimacao() {
        new AnimationTimer() {
            @Override
            public void handle(long agora) {
                if (roboModelo.emMovimento() && (agora - ultimoMovimento) >= intervaloMovimento) {
                    roboModelo.mover(matrizCidade);

                    GridPane.setColumnIndex(sprite, roboModelo.getPosicaoX());
                    GridPane.setRowIndex(sprite, roboModelo.getPosicaoY());

                    sprite.setImage(roboModelo.getSpriteAtual());

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
    public void definirDestino(int destinoX, int destinoY) {
        roboModelo.destino(destinoX, destinoY);
    }

    /**
     * Getters.
     */
    public ImageView getRoboSprite() { return sprite; }
    public ImageView getSprite() { return sprite; }
}
