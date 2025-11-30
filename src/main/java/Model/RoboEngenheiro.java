package Model;
import javafx.scene.image.Image;

/**Classe do robô engenheiro da cidade
 */
public class RoboEngenheiro extends Robo{

    /**Construtor do robô
     * @param posicaoX posição X para colocar o robô
     * @param posicaoY posição Y para colocar o robô
     */
    public RoboEngenheiro(int posicaoX, int posicaoY) {
        //coloca a posição X do robô
        setPosicaoX(posicaoX);
        //coloca a posição Y do robô
        setPosicaoY(posicaoY);
        //Adiciona o destino X do robô como a posição X
        setDestinoX(posicaoX);
        //Adiciona o destino Y do robô como a posição Y
        setDestinoY(posicaoY);
        //muda o nome do robô para Engenheiro
        setNome("Engenheiro");
        //Adiciona o sprite 1 do robô engenheiro
        setSprite1(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-azul-1.png")));
        //Adiciona o sprite 2 do robô engenheiro
        setSprite2(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-azul-2.png")));
        //Cria o stackpane do robô
        criarStackRobo();
    }
}
