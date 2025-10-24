package controller;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Robo;

/**
 * Classe cotroller do robô.
 */
public class RoboController {

    /**
     * Atributos da classe, robo, imageView e outros relacionados a animação do robô.
     */
    private Robo robo;
    private Robo robo2;
     ImageView imageView;
    private ImageView image2;
    private long ultimoMovimento = 0, intervaloMovimento = 500_000_000;

    /**
     * Construtor da classe que recebe as posições iniciais do robô e o GridPane da cidade. Faz as configurações do robô, o posiciona no Grid e chama a sua animação.
     * @param posicaoInicialX
     * @param posicaoInicialY
     * @param gridCidade
     */
    public RoboController(int posicaoInicialX, int posicaoInicialY, GridPane gridCidade) {
        this.robo = new Robo(posicaoInicialX, posicaoInicialY);
        this.robo2 = new Robo(3,3);
        this.imageView = new ImageView(robo.getSpriteAtual());
        this.image2 = new ImageView(robo2.getSpriteAtual());

        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        image2.setFitWidth(32);
        image2.setFitHeight(32);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);

        gridCidade.add(imageView, posicaoInicialX, posicaoInicialY);
        gridCidade.add(image2,robo2.getPosicaoX(),robo2.getPosicaoY());

        iniciarAnimacao();
    }

    /**
     * Método que faz a animação do robô.
     */
    private void iniciarAnimacao() {
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
    public void definirDestino(int destinoX, int destinoY) {
        robo.destino(destinoX, destinoY);
    }

    /**
     * Getters.
     */
    public Robo getRobo() { return robo; }
    public ImageView getImageView() { return imageView; }
}
