package Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.awt.*;

/**Classe abstrata para os prédios derivados
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

    public void contruirStackPredio(){
        imagePredio.setFitWidth(250);
        imagePredio.setFitHeight(250);
        imagePredio.setPreserveRatio(true);
        imagePredio.setSmooth(true);

        Label labelPredio = new Label(nome);
        labelPredio.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: White;");

        StackPane.setAlignment(labelPredio, Pos.TOP_CENTER);
        StackPane.setMargin(labelPredio, new Insets(-5,0,0,0));

        if (stackPredio != null){
            stackPredio.getChildren().add(imagePredio);
            stackPredio.getChildren().add(labelPredio);
        } else {
            stackPredio = new StackPane();
            stackPredio.getChildren().add(imagePredio);
            stackPredio.getChildren().add(labelPredio);
        }

    }

    public void setPosicaoX(int posicaoX){
        this.posicaoX = posicaoX;
    }
    public void setPosicaoY(int posicaoY){
        this.posicaoY = posicaoY;
    }
    public void setLevel() {
        this.level++;
    }
    public void setImage(ImageView image){
        this.imagePredio = image;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setConstruido() {
        this.construido = true;
    }

    public int getPosicaoX() {
        return this.posicaoX;
    }
    public int getPosicaoY() {
        return this.posicaoY;
    }
    public int getLevel() {
        return level;
    }
    public ImageView getImage() {
        return this.imagePredio;
    }
    public boolean getConstruido() {
        return this.construido;
    }

    public StackPane getStackPredio() {
        return this.stackPredio;
    }
}
