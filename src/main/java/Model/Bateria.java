package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**Classe da bateria dos robô
 */
public class Bateria {
    private int porcentagem = 100;
    private transient ImageView carregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/carregado.png")));
    private transient ImageView metade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/metade.png")));
    private transient ImageView metadeDaMetade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/MetadeDaMetade.png")));
    private transient ImageView descarregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/descarregado.png")));

    /**Método para recarregar a bateria do robô
     */
    public void recarregar() {
        this.porcentagem = 100;
    }

    /**Método para diminuir a bateria do robô
     */
    public void diminuir() {
        if (this.porcentagem >=  25) {
            this.porcentagem -= 25;
        }
    }

    /**Método para retornar a porcentagem da bateria do robô
     * @return retorna a porcentagem
     */
    public int getPorcentagem() {
        return this.porcentagem;
    }

    /**Método para retornar a ImageView atual da bateria do robô
     * @return retorna a ImageView atual da bateria do robô
     */
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

    /**Método para recuperar as imagens da porcentagem da bateria
     */
    public void recuperarImagem(){
        this.carregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/carregado.png")));
        this.metade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/metade.png")));
        this.metadeDaMetade = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/MetadeDaMetade.png")));
        this.descarregada = new ImageView(new Image(getClass().getResourceAsStream("/sprites/bateria/descarregado.png")));
    }
}
