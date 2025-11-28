package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**Classe do prédio central (ou prefeitura)
 */
public class PredioCentral extends PredioGeral{


    public PredioCentral(int posicaoX, int posicaoY){
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setNome("Prédio Central");
        super.setImage(new ImageView(new Image(getClass().getResourceAsStream("/sprites/Predios/predioPrincipal.png"))));
        super.contruirStackPredio();
    }

}
