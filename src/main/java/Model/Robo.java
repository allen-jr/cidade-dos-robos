package Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Classe modelo do robô.
 */
public class Robo {

    /**
     * Atributos da classe, posições, destino, emMovimento e sprites.
     */
    private int posicaoX, posicaoY, destinoX, destinoY;
    private boolean emMovimento;
    private String nome;
    private transient StackPane stackRobo = new StackPane();
    private transient ImageView spriteAtual;
    private transient ImageView spriteBateria;
    private transient Image sprite1;
    private transient Image sprite2;
    private Bateria bateria = new Bateria();
    private boolean fabricado;

    public void criarStackRobo() {
        // Inicializa a ImageView do sprite
        spriteAtual = new ImageView(sprite1);
        spriteAtual.setFitWidth(30);
        spriteAtual.setFitHeight(30);

        // Inicializa a ImageView da bateria com o sprite atual da Bateria
        spriteBateria = bateria.getImageBateria();
        spriteBateria.setFitWidth(30);
        spriteBateria.setFitHeight(20);

        // Configura a posição do ícone da bateria
        StackPane.setAlignment(spriteBateria, Pos.TOP_CENTER);
        StackPane.setMargin(spriteBateria,new Insets(-20,0,0,0));

        // Cria o StackPane e adiciona o sprite e o ícone da bateria
        stackRobo = new StackPane(spriteAtual, spriteBateria);
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public void setDestinoX(int destinoX) {
        this.destinoX = destinoX;
    }

    public void setDestinoY(int destinoY) {
        this.destinoY = destinoY;
    }

    public void setSprite1(Image sprite1) {
        this.sprite1 = sprite1;
    }

    public void setSprite2(Image sprite2) {
        this.sprite2 = sprite2;
    }

    public void setFabricado() {
        this.fabricado = true;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Getters.
     */
    public int getPosicaoX() { return posicaoX; }
    public int getPosicaoY() { return posicaoY; }
    public boolean emMovimento(){
        return this.emMovimento;
    }
    public StackPane getRoboStack() {
        return this.stackRobo;
    }

    public String getNome() {
        return this.nome;
    }

    public ImageView getImagemBateria() {
        return this.bateria.getImageBateria();
    }

    public int getPorcentoBateria() {
        return this.bateria.getPorcentagem();
    }

    public Bateria getBateria() {
        if (this.bateria != null) {
            return this.bateria;
        }
        this.bateria = new Bateria();
        return this.bateria;
    }

    public boolean isFabricado() {
        return this.fabricado;
    }

    /**
     * Método que recebe o destino do robô, define que o robô está em movimento e o seu sprite inicial.
     * @param destinoX
     * @param destinoY
     */
    public void destino(int destinoX, int destinoY) {
        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.emMovimento = true;
        spriteAtual.setImage(sprite1);
    }

    /**
     * Método que faz o robô se mover comparando a sua posição com o seu destino. Possuí um boolean que se for igual a true, chama o método para alternar o sprite, dessa forma o sprite é alterado a cada movimento do robô. Se o robô chegar ao destino, define que o robô não está em movimento e volta para o sprite inicial.
     */
    public void mover(boolean[][] matrizCidade) {
        boolean moveu = false;

        if ((posicaoX < destinoX) && !matrizCidade[posicaoY][posicaoX+1]) {
            posicaoX++;
            moveu = true;
        } else if (posicaoX > destinoX && !matrizCidade[posicaoY][posicaoX-1]) {
            posicaoX--;
            moveu = true;
        }

        if (posicaoY < destinoY && !matrizCidade[posicaoY+1][posicaoX]) {
            posicaoY++;
            moveu = true;
        } else if (posicaoY > destinoY && !matrizCidade[posicaoY-1][posicaoX]) {
            posicaoY--;
            moveu = true;
        }

        if (moveu) {
           alternarSprite();
        }

        if (posicaoX == destinoX && posicaoY == destinoY) {
            emMovimento = false;
            spriteAtual.setImage(sprite1);
            this.bateria.diminuir();
            spriteBateria.setImage(bateria.getImageBateria().getImage());
        }
    }

    /**
     * Método que alterna os sprites.
     */
    private void alternarSprite() {
        Image sprite = spriteAtual.getImage();
        if (sprite == sprite1) {
            spriteAtual.setImage(sprite2);
        } else {
            spriteAtual.setImage(sprite1);
        }
    }

    /**Método para executar uma ação específica do robo (o método deve ser sobrescrito nas classes derivadas)
     */
    public void acaoRobo(){
        System.out.println("sem ação");
    }

    public void recarregarBateria(){
        this.bateria.recarregar();
        spriteBateria.setImage(bateria.getImageBateria().getImage());
    }
    public void carregarSprites(Image sprite1, Image sprite2){
        this.sprite1 = sprite1;
        this.sprite2 = sprite2;
    }
}
