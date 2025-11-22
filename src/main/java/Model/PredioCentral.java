package Model;
import javafx.scene.image.Image;

/**Classe do prédio central (ou prefeitura)
 */
public class PredioCentral extends PredioGeral{


    public PredioCentral(int posicaoX, int posicaoY){
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setImage(new Image(getClass().getResourceAsStream("/sprites/predioPrincipal.png")));
    }

}
