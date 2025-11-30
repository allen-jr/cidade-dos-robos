package Model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**Classe do prédio central (ou prefeitura)
 */
public class PredioCentral extends PredioGeral {

    /**Construtor do prédio central da cidade
     */
    public PredioCentral() {
        //coloca a posição X do prédio
        super.setPosicaoX(25);
        //coloca a posição Y do prédio
        super.setPosicaoY(16);
        //Muda o nome para Prédio Central
        super.setNome("Prédio Central");
        //Coloca ele como construído (ele é adicionado assim que cria a cidade)
        super.setConstruido();
        //Adiciona a imagem do prédio central
        super.setImage(new ImageView(new Image(getClass().getResourceAsStream("/sprites/Predios/predioPrincipal.png"))));
        //Cria o stackpane do prédio central
        super.contruirStackPredio();
    }
}
