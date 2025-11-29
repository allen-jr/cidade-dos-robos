package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bateria {
    private int porcentagem = 100;
    private ImageView carregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/carregado.png")));
    private ImageView metade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/metade.png")));
    private ImageView metadeDaMetade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/MetadeDaMetade.png")));
    private ImageView descarregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/descarregado.png")));

    public void recarregar() {
        this.porcentagem = 100;
    }

    public void diminuir() {
        if (this.porcentagem >=  25) {
            this.porcentagem -= 25;
        }
    }

    public int getPorcentagem() {
        return this.porcentagem;
    }

    public ImageView getBateria() {
        if (porcentagem >= 51) {
            return this.carregada;
        } else if (porcentagem >= 26) {
            return this.metade;
        } else if (porcentagem >= 1) {
            return this.metadeDaMetade;
        } else {
            return this.descarregada;
        }
    }
}
