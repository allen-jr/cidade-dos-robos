package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**Classe da fábrica de robôs da cidade
 */
public class FabricaRobo extends PredioGeral {

    /**Construtor da fábrica de robôs da cidade
     * @param posicaoX posição X para colocar o prédio
     * @param posicaoY posição Y para colocar o prédio
     */
    public FabricaRobo(int posicaoX, int posicaoY){
        //coloca a posição X do prédio
        super.setPosicaoX(posicaoX);
        //coloca a posição Y do prédio
        super.setPosicaoY(posicaoY);
        //Muda o nome para Fábrica de Robôs
        super.setNome("Fábrica de Robôs");
        //Adiciona a imagem da fábrica de robôs
        super.setImage(new ImageView(new Image(getClass().getResourceAsStream("/sprites/Predios/FabricaDeRobos.png"))));
        //Cria a stackpane da fábrica de robôs
        super.contruirStackPredio();
    }
}
