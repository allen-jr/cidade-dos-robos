package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bateria {
    private int porcentagem = 100;
    private ImageView carregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/carregado.png")));
    private ImageView metade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/metade.png")));;
    private ImageView descarregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/descarregado.png")));;

    public void recarregar() {
        this.porcentagem = 100;
    }

    public void diminuir() {
        if (this.porcentagem >=  20) {
            this.porcentagem -= 20;
        }
    }

    public ImageView getBateria() {
        if (porcentagem >= 50) {
            return this.carregada;
        } else if (porcentagem >= 1) {
            return this.metade;
        } else {
            return this.descarregada;
        }
    }
}
