package Model;

import javafx.scene.image.Image;

public class RoboEngenheiro extends Robo{
    public RoboEngenheiro(int posicaoX, int posicaoY) {
        setPosicaoX(posicaoX);
        setPosicaoY(posicaoY);
        setDestinoX(posicaoX);
        setDestinoY(posicaoY);
        setNome("Engenheiro");
        setSprite1(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-azul-1.png")));
        setSprite2(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-azul-2.png")));
        criarStackRobo();
    }
}
