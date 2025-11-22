package Model;

import Controller.RoboController;
import javafx.scene.image.Image;

public class RoboExplorador extends Robo{
    /**
     * Construtor da classe, define as posições, o destino, se o robô está em movimento, carrega os sprites e define o sprite inicial.
     * @param posicaoX
     * @param posicaoY
     */
    public RoboExplorador(int posicaoX, int posicaoY) {
        setPosicaoX(posicaoX);
        setPosicaoY(posicaoY);
        setDestinoX(posicaoX);
        setDestinoY(posicaoY);
        setSprite1(new Image(getClass().getResourceAsStream("/sprites/robo-verde-1.png")));
        setSprite2(new Image(getClass().getResourceAsStream("/sprites/robo-verde-2.png")));
        setSpriteAtual(getSprite1());
    }

    @Override
    public void acaoRobo(){
        super.destino(45,24);
    }
}
