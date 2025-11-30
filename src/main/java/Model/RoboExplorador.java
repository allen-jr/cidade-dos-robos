package Model;
import javafx.scene.image.Image;

/**Classe do robô explorador da cidade
 */
public class RoboExplorador extends Robo{

    /**Construtor do robô explorador
     */
    public RoboExplorador() {
        //coloca a posição X como 22
        setPosicaoX(22);
        //coloca a posição Y como 16
        setPosicaoY(16);
        //coloca o destino X como 22
        setDestinoX(22);
        //coloca o destino Y como 16
        setDestinoY(16);
        //Muda o nome para Explorador
        setNome("Explorador");
        //coloca ele como fabricado (ele é colocado na cidade assim que cria)
        setFabricado();
        //Adiciona o sprite 1 do robô explorador
        setSprite1(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-verde-1.png")));
        //Adiciona o sprite 2 do robô explorador
        setSprite2(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-verde-2.png")));
        //cria o stackpane do robô
        criarStackRobo();
    }

    /**Método para fazer a ação do robô (pegar recursos)
     */
    @Override
    public void acaoRobo(){
        //define o destino do robo
        super.destino(45,20);
        //adiciona os recursos na cidade
        BancoDeDados.getCidade().adicionarRecursos(20.0);
    }
}
