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
    private Image sprite1;
    private Image sprite2;
    private Image spriteAtual;

    public void setSprite1(Image sprite1) {
        this.sprite1 = sprite1;
    }

    public void setSprite2(Image sprite2) {
        this.sprite2 = sprite2;
    }

    public void setSpriteAtual(Image spriteAtual) {
        this.spriteAtual = spriteAtual;
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

    /**Getters.
     */
    public int getPosicaoX() { return posicaoX; }
    public int getPosicaoY() { return posicaoY; }
    public boolean emMovimento(){
        return this.emMovimento;
    }
    public Image getSpriteAtual() { return spriteAtual; }

    public Image getSprite1() {
        return sprite1;
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

    /**Método para executar uma ação específica do robo (o método deve ser sobrescrito nas classes derivadas)
     */
    public void acaoRobo(){
        System.out.println("sem ação");
    }
}
