package Model;

import javafx.scene.image.Image;

public class RoboConstrutor extends Robo{

    public RoboConstrutor(int posicaoX, int posicaoY) {
        setPosicaoX(posicaoX);
        setPosicaoY(posicaoY);
        setDestinoX(posicaoX);
        setDestinoY(posicaoY);
        setNome("Construtor");
        setSprite1(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-construtor-1.png")));
        setSprite2(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-construtor-2.png")));
        criarStackRobo();
    }
}
