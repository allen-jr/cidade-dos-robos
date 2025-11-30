package Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**Classe modelo para os robôs derivados
 */
public class Robo {
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

    /**Método para criar o stackpane do robô
     */
    public void criarStackRobo() {
        //cria a imagemView do sprite atual do robô e define as propriedades
        spriteAtual = new ImageView(sprite1);
        spriteAtual.setFitWidth(30);
        spriteAtual.setFitHeight(30);
        spriteAtual.setPreserveRatio(true);
        spriteAtual.setSmooth(true);
        //pega a instância do sprite atual da bateria e define as propriedades
        spriteBateria = bateria.getImageBateria();
        spriteBateria.setFitWidth(30);
        spriteBateria.setFitHeight(30);
        spriteBateria.setPreserveRatio(true);
        spriteBateria.setSmooth(true);
        //define o alinhamento do sprite da bateria em relação ao stackpane
        StackPane.setAlignment(spriteBateria, Pos.TOP_CENTER);
        //define a distância do sprite da bateria em relação ao stackpane
        StackPane.setMargin(spriteBateria,new Insets(-10,0,0,0));
        //Cria o StackPane e adiciona o sprite do robô e o sprite da bateria
        stackRobo = new StackPane(spriteAtual, spriteBateria);
    }

    /**Método para mudar a posição X do robô
     * @param posicaoX nova posição X
     */
    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    /**Método para mudar a posição Y do robô
     * @param posicaoY nova posição Y
     */
    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    /**Método para mudar a posição X do destino do robô
     * @param destinoX novo Destino X
     */
    public void setDestinoX(int destinoX) {
        this.destinoX = destinoX;
    }

    /**Método para mudar a posição Y do destino do robô
     * @param destinoY novo Destino Y
     */
    public void setDestinoY(int destinoY) {
        this.destinoY = destinoY;
    }

    /**Método para mudar o sprite 1 do robô
     * @param sprite1 novo sprite
     */
    public void setSprite1(Image sprite1) {
        this.sprite1 = sprite1;
    }

    /**Método para mudar o sprite 2 do robô
     * @param sprite2 novo sprite
     */
    public void setSprite2(Image sprite2) {
        this.sprite2 = sprite2;
    }

    /**Método para definir o robô como fabricado
     */
    public void setFabricado() {
        this.fabricado = true;
    }

    /**Método para mudar o nome do robô
     * @param nome novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**Método para retornar a posição X do robô
     * @return retorna a posição X
     */
    public int getPosicaoX() { return posicaoX; }

    /**Método para retornar a posição Y do robô
     * @return retorna a posição Y
     */
    public int getPosicaoY() { return posicaoY; }

    /**Método para retorna se o robô está em movimento
     * @return retorna true (robô em moviemento) false (robô não está em movimento)
     */
    public boolean emMovimento(){
        return this.emMovimento;
    }

    /**Método para retornar o stackpane do robô
     * @return retorna o stackpane do robô
     */
    public StackPane getRoboStack() {
        return this.stackRobo;
    }

    /**Método para retornar o nome do robô
     * @return retorna o nome do robô
     */
    public String getNome() {
        return this.nome;
    }

    /**Método para retorna o ImageView da bateria
     * @return retorna o ImageView atual da bateria
     */
    public ImageView getImagemBateria() {
        return this.bateria.getImageBateria();
    }

    /**Método para retornar o porcento atual da bateria do robô
     * @return retorna o porcento da bateria do robô
     */
    public int getPorcentoBateria() {
        return this.bateria.getPorcentagem();
    }

    /**Método para retornar a bateria do robõ
     * @return retorna a bateria do robô
     */
    public Bateria getBateria() {
        if (this.bateria != null) {
            return this.bateria;
        }
        this.bateria = new Bateria();
        return this.bateria;
    }

    /**Método para retornar se o robô ja foi fabricado
     * @return true (fabricado) false (não fabricado)
     */
    public boolean isFabricado() {
        return this.fabricado;
    }

    /**Método que recebe o destino do robô, define que o robô está em movimento e o seu sprite inicial.
     * @param destinoX destino X do robô
     * @param destinoY destino Y do Robô
     */
    public void destino(int destinoX, int destinoY) {
        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.emMovimento = true;
        spriteAtual.setImage(sprite1);
    }

    /**Método que faz o robô se mover comparando a sua posição com o seu destino. Possuí um boolean que se for igual a true, chama o método para alternar o sprite, dessa forma o sprite é alterado a cada movimento do robô. Se o robô chegar ao destino, define que o robô não está em movimento e volta para o sprite inicial.
     * @param matrizCidade matriz booleana com as linhas e colunas da cidade, serve para tratar colisões
     */
    public void mover(boolean[][] matrizCidade) {
        boolean moveu = false;
        //move o robô para a direita
        if ((posicaoX < destinoX) && !matrizCidade[posicaoY][posicaoX+1]) {
            posicaoX++;
            moveu = true;
        }// move o robô para a esquerda
        else if (posicaoX > destinoX && !matrizCidade[posicaoY][posicaoX-1]) {
            posicaoX--;
            moveu = true;
        }
        //move o robô para cima
        if (posicaoY < destinoY && !matrizCidade[posicaoY+1][posicaoX]) {
            posicaoY++;
            moveu = true;
        }//move o robô para baixo
        else if (posicaoY > destinoY && !matrizCidade[posicaoY-1][posicaoX]) {
            posicaoY--;
            moveu = true;
        }
        //alterna o sprite se o robô se moveu
        if (moveu) {
           alternarSprite();
        }
        //sprite do robô volta a ser o inicial, diminui a bateria do robô e o boolean em movimento passa a ser false
        if (posicaoX == destinoX && posicaoY == destinoY) {
            emMovimento = false;
            spriteAtual.setImage(sprite1);
            this.bateria.diminuir();
            spriteBateria.setImage(bateria.getImageBateria().getImage());
        }
    }

    /**Método que alterna os sprites.
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

    /**Método para recarregar a bateria do robô
     */
    public void recarregarBateria(){
        this.bateria.recarregar();
        spriteBateria.setImage(bateria.getImageBateria().getImage());
    }
}
