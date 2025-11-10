package model;
import javafx.scene.image.Image;

/**Classe do prédio central (ou prefeitura)
 */
public class PredioCentral extends PredioGeral{
    private int level = 1;

    public PredioCentral(int posicaoX, int posicaoY){
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setImage(new Image(getClass().getResourceAsStream("/sprites/predioPrincipal.png")));
    }

    public void setLevel() {
        this.level++;
    }

    public int getLevel() {
        return level;
    }
}
