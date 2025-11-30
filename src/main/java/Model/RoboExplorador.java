package Model;
import javafx.scene.image.Image;

public class RoboExplorador extends Robo{
    /**
     * Construtor da classe, define as posições, o destino, se o robô está em movimento, carrega os sprites e define o sprite inicial.
     */
    public RoboExplorador() {
        setPosicaoX(22);
        setPosicaoY(16);
        setDestinoX(22);
        setDestinoY(16);
        setNome("Explorador");
        setFabricado();
        setSprite1(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-verde-1.png")));
        setSprite2(new Image(getClass().getResourceAsStream("/sprites/Robos/robo-verde-2.png")));
        criarStackRobo();
    }

    @Override
    public void acaoRobo(){
        super.destino(45,20);
        BancoDeDados.getCidade().adicionarRecursos(20.0);
    }
}
