package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PredioFabrica extends PredioGeral {

    public PredioFabrica(int posicaoX, int posicaoY){
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setNome("Fábrica de Robôs");
        super.setImage(new ImageView(new Image(getClass().getResourceAsStream("/sprites/Predios/FabricaDeRobos.png"))));
        super.contruirStackPredio();
    }
}
