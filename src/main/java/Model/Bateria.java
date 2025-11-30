package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bateria {
    private int porcentagem = 100;
    private transient ImageView carregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/carregado.png")));
    private transient ImageView metade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/metade.png")));
    private transient ImageView metadeDaMetade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/MetadeDaMetade.png")));
    private transient ImageView descarregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/descarregado.png")));

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

    public ImageView getImageBateria() {
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

    public void recuperarImagem(){
        this.carregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/carregado.png")));
        this.metade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/metade.png")));
        this.metadeDaMetade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/MetadeDaMetade.png")));
        this.descarregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/descarregado.png")));
    }
}
