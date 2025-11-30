package Model;
import javafx.scene.image.Image;

/**Classe do robô construtor da cidade
 */
public class RoboConstrutor extends Robo{

    /**Construtor do robô
     * @param posicaoX posição X para colocar o robô
     * @param posicaoY posição Y para colocar o robô
     */
    public RoboConstrutor(int posicaoX, int posicaoY) {
        //coloca a posição X do robô
        setPosicaoX(posicaoX);
        //coloca a posição Y do robô
        setPosicaoY(posicaoY);
        //Adiciona o destino X do robô como a posição X
        setDestinoX(posicaoX);
        //Adiciona o destino Y do robô como a posição Y
        setDestinoY(posicaoY);
        //muda o nome do robô para construtor
        setNome("Construtor");
        //Adiciona o sprite 1 do robô construtor
        setSprite1(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-construtor-1.png")));
        //Adiciona o sprite 2 do robô construtor
        setSprite2(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-construtor-2.png")));
        //Cria o stackpane do robô
        criarStackRobo();
    }
}
