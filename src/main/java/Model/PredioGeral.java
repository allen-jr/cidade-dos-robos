package Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;

/**Classe modelo para os prédios derivados
 * @author Tairone Lima
 */
public class PredioGeral {
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private int level = 1;
    private transient ImageView imagePredio;
    private transient StackPane stackPredio = new StackPane();
    private boolean construido;

    /**Método para construir o stackpane do prédio
     */
    public void contruirStackPredio(){
        //define as propriedades da imagem do prédio
        imagePredio.setFitWidth(250);
        imagePredio.setFitHeight(250);
        imagePredio.setPreserveRatio(true);
        imagePredio.setSmooth(true);
        //Label para colocar o nome do prédio no stackpane
        Label labelPredio = new Label(nome);
        labelPredio.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: White;");
        //define o alinhamento do stackpane em relação ao nome
        StackPane.setAlignment(labelPredio, Pos.TOP_CENTER);
        //define a distância do nome em relação ao alinhamento
        StackPane.setMargin(labelPredio, new Insets(-5,0,0,0));
        //verifica se o stackpane é diferente de null
        if (stackPredio != null){
            //adiciona a imagem e o nome no stackpane
            stackPredio.getChildren().add(imagePredio);
            stackPredio.getChildren().add(labelPredio);
        } else {
            //cria o stackpane
            stackPredio = new StackPane();
            //adiciona a imagem e o nome no stackpane
            stackPredio.getChildren().add(imagePredio);
            stackPredio.getChildren().add(labelPredio);
        }

    }

    /**Método para alterar a posição X do prédio
     * @param posicaoX nova posição X
     */
    public void setPosicaoX(int posicaoX){
        this.posicaoX = posicaoX;
    }

    /**Método para alterar a posição Y do prédio
     * @param posicaoY nova posição Y
     */
    public void setPosicaoY(int posicaoY){
        this.posicaoY = posicaoY;
    }

    /**Método para adicionar mais 1 level no prédio
     */
    public void addLevel() {
        this.level++;
    }

    /**Método para mudar a imagem do prédio
     * @param image nova imagem
     */
    public void setImage(ImageView image){
        this.imagePredio = image;
    }

    /**Método para mudar o nome do prédio
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Método para definir o prédio como construído
     */
    public void setConstruido() {
        this.construido = true;
    }

    /**Método para retornar a posição X do prédio
     * @return retorna a posição X do prédio
     */
    public int getPosicaoX() {
        return this.posicaoX;
    }

    /**Método para retornar a posição Y do prédio
     * @return retorna a posição Y do prédio
     */
    public int getPosicaoY() {
        return this.posicaoY;
    }

    /**Método para retornar o level do prédio
     * @return retorna o level atual do prédio
     */
    public int getLevel() {
        return level;
    }

    /**Método para retornar a ImageView do prédio
     * @return retorna a ImageView do prédio
     */
    public ImageView getImage() {
        return this.imagePredio;
    }

    /**Método para retorna se o prédio foi construído
     * @return true (construído) false (não construído)
     */
    public boolean getConstruido() {
        return this.construido;
    }

    /**Método para retornar o stackpane do prédio
     * @return retorna o stackpane do prédio
     */
    public StackPane getStackPredio() {
        return this.stackPredio;
    }
}
