package model;
import javafx.scene.image.Image;

/**Classe do prédio central (ou prefeitura)
 */
public class PredioCentral extends PredioGeral{
    private int level = 1;

    PredioCentral(int posicaoX, int posicaoY, Image image){
        super.setPosicaoX(posicaoX);
        super.setPosicaoY(posicaoY);
        super.setImage(image);
    }

    public void setLevel() {
        this.level++;
    }

    public int getLevel() {
        return level;
    }
}
