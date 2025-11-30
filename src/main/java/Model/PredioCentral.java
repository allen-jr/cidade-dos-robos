package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**Classe do prédio central (ou prefeitura)
 */
public class PredioCentral extends PredioGeral {


    public PredioCentral() {
        super.setPosicaoX(25);
        super.setPosicaoY(16);
        super.setNome("Prédio Central");
        super.setConstruido();
        super.setImage(new ImageView(new Image(getClass().getResourceAsStream("/sprites/Predios/predioPrincipal.png"))));
        super.contruirStackPredio();
    }
}
