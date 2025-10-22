package model;

import javafx.scene.image.Image;

/**
 * Classe modelo do robô.
 */
public class Robo {

    /**
     * Atributos da classe, posições, destino, emMovimento e sprites.
     */
    private int posicaoX, posicaoY, destinoX, destinoY;
    private boolean emMovimento;
    private Image sprite1, sprite2, spriteAtual;

    /**
     * Construtor da classe, define as posições, o destino, se o robô está em movimento, carrega os sprites e define o sprite inicial.
     * @param posicaoX
     * @param posicaoY
     */
    public Robo(int posicaoX, int posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.destinoX = posicaoX;
        this.destinoY = posicaoY;
        this.emMovimento = false;
        this.sprite1 = new Image(getClass().getResourceAsStream("/sprites/robo-verde-1.png"));
        this.sprite2 = new Image(getClass().getResourceAsStream("/sprites/robo-verde-2.png"));
        this.spriteAtual = sprite1;
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
        this.spriteAtual = sprite1;
    }

    /**
     * Método que faz o robô se mover comparando a sua posição com o seu destino. Possuí um boolean que se for igual a true, chama o método para alternar o sprite, dessa forma o sprite é alterado a cada movimento do robô. Se o robô chegar ao destino, define que o robô não está em movimento e volta para o sprite inicial.
     */
    public void mover() {
        boolean moveu = false;

        if (posicaoX < destinoX) {
            posicaoX++;
            moveu = true;
        } else if (posicaoX > destinoX) {
            posicaoX--;
            moveu = true;
        }

        if (posicaoY < destinoY) {
            posicaoY++;
            moveu = true;
        } else if (posicaoY > destinoY) {
            posicaoY--;
            moveu = true;
        }

        if (moveu) {
           alternarSprite();
        }

        if (posicaoX == destinoX && posicaoY == destinoY) {
            emMovimento = false;
            spriteAtual = sprite1;
        }
    }

    /**
     * Método que alterna os sprites.
     */
    private void alternarSprite() {
        spriteAtual = (spriteAtual == sprite1) ? sprite2 : sprite1;
    }

    /**
     * Getters.
     */
    public int getPosicaoX() { return posicaoX; }
    public int getPosicaoY() { return posicaoY; }
    public boolean isEmMovimento() { return emMovimento; }
    public Image getSpriteAtual() { return spriteAtual; }
}
