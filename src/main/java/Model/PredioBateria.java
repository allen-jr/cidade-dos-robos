package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PredioBateria extends PredioGeral{
    public PredioBateria(int posicaoX, int posicaoY){
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setNome("Fábrica de Baterias");
        super.setImage(new ImageView(new Image(getClass().getResourceAsStream("/sprites/Predios/FabricaDeBateria.png"))));
        super.contruirStackPredio();
    }
}
