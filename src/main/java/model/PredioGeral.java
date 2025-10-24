package model;
import javafx.scene.image.Image;

/**Classe abstrata para os prédios derivados
 * @author Tairone Lima
 */
public class PredioGeral {
    private int posicaoX;
    private int posicaoY;
    private Image image;

    public void setPosicaoX(int posicaoX){
        this.posicaoX = posicaoX;
    }
    public void setPosicaoY(int posicaoY){
        this.posicaoY = posicaoY;
    }
    public void setImage(Image image){
        this.image = image;
    }

    public int getPosicaoX() {
        return this.posicaoX;
    }
    public int getPosicaoY() {
        return this.posicaoY;
    }
    public Image getImage() {
        return this.image;
    }
}
