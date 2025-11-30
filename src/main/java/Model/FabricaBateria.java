package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**Classe da fábrica de baterias da cidade
 */
public class FabricaBateria extends PredioGeral{
    /**Construtor da fábrica de baterias da cidade
     * @param posicaoX posição X para colocar o prédio
     * @param posicaoY posição Y para colocar o prédio
     */
    public FabricaBateria(int posicaoX, int posicaoY){
        //coloca a posição X do prédio
        super.setPosicaoX(posicaoX);
        //coloca a posição Y do prédio
        super.setPosicaoY(posicaoY);
        //Muda o nome para Fábrica de Baterias
        super.setNome("Fábrica de Baterias");
        //Adiciona a imagem da fábrica de baterias
        super.setImage(new ImageView(new Image(getClass().getResourceAsStream("/sprites/Predios/FabricaDeBateria.png"))));
        //Cria o stackpane da fábrica de baterias
        super.contruirStackPredio();
    }
}
