package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Cidade;
import model.Robo;

/**
 * Classe cotroller do robô.
 */
public class RoboController {

    /**
     * Atributos da classe, robo, imageView e outros relacionados a animação do robô.
     */

    static ImageView imageView;
    private static long ultimoMovimento = 0, intervaloMovimento = 500_000_000;

    /**
     * Construtor da classe que recebe as posições iniciais do robô e o GridPane da cidade. Faz as configurações do robô, o posiciona no Grid e chama a sua animação.
     * @param posicaoInicialX
     * @param posicaoInicialY
     * @param gridCidade
     */
    public static void MoverRobo(int posicaoInicialX, int posicaoInicialY, GridPane gridCidade, Cidade cidade) {
        imageView = new ImageView(cidade.getRobos().get(0).getSpriteAtual());

        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        gridCidade.add(imageView, posicaoInicialX, posicaoInicialY);

        iniciarAnimacao(cidade.getRobos().get(0));
    }

    /**
     * Método que faz a animação do robô.
     */
    private static void iniciarAnimacao(Robo robo) {
        new AnimationTimer() {
            @Override
            public void handle(long agora) {
                if (agora - ultimoMovimento >= intervaloMovimento) {
                    robo.mover();

                    GridPane.setColumnIndex(imageView, robo.getPosicaoX());
                    GridPane.setRowIndex(imageView, robo.getPosicaoY());

                    imageView.setImage(robo.getSpriteAtual());

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
    public static void definirDestino(int destinoX, int destinoY, Robo robo) {
        robo.destino(destinoX, destinoY);
    }

    /**
     * Getters.
     */
    public ImageView getImageView() { return imageView; }
}
