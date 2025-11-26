package Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import Model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**Classe controller da cidade.
 */
public class CidadeController implements Initializable{
    private RoboExplorador roboExplorador;
    private PredioCentral predioCentral;
    private final int TAMANHO_CELULA = 30;
    private final int LINHAS = 960 / TAMANHO_CELULA;
    private final int COLUNAS = 1500 / TAMANHO_CELULA;

    private Image blocoCidade = new Image(getClass().getResourceAsStream("/sprites/blocoDaCidade.png"));
    private final String ESTILO_SELECIONADO = "-fx-effect: dropshadow(three-pass-box, yellow, 10, 0.5, 0, 0); -fx-border-color: yellow; -fx-border-width: 3;";
    private final String ESTILO_NAO_SELECIONADO = "";
    private StackPane stackRoboEx;
    private ImageView imagePredioCentral;
    private boolean[][] matrizCidade = new boolean[LINHAS][COLUNAS]; //Matriz para evitar colisões
    boolean roboSelecionado = false;

    @FXML
    private GridPane gridCidade;

    /**Initialize do Grid que chama o configurarGrid, define a posição inicial do robô, cria um novo roboController enviando as posições iniciais e o gridCidade, e define um setOnMouseClick.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Configura o gridpane
        configurarGrid();
        //Define a posição inicial no meio
        int posicaoInicialX = COLUNAS / 2;
        int posicaoInicialY = LINHAS / 2;
        //Chama o método para carregar os status
        carregarStatus();
        //Adiciona o prédio central no gridpane
        predioCentral = new PredioCentral(posicaoInicialX,posicaoInicialY);
        imagePredioCentral = new ImageView(predioCentral.getImage());
        addPredio(predioCentral.getPosicaoY(),predioCentral.getPosicaoX(),imagePredioCentral);
        //Adiciona o robo no gridpane
        roboExplorador = new RoboExplorador(posicaoInicialX-3,posicaoInicialY);
        //Salva a stack do robo
        stackRoboEx = roboExplorador.getRoboStack();
        RoboController.addRobo(stackRoboEx,gridCidade,roboExplorador.getPosicaoY(), roboExplorador.getPosicaoX());
        //Clicar no robo para direciona-lo em um local
        // Adicione uma variável de estado na classe Cidade
        stackRoboEx.setOnMouseClicked(event -> {
            if (!roboSelecionado) {
                stackRoboEx.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                roboSelecionado = true;
            } else {
                stackRoboEx.setStyle(ESTILO_NAO_SELECIONADO);
                roboSelecionado = false;
            }
            event.consume();
        });

        gridCidade.setOnMouseClicked(mouseEvent -> {
            if (roboSelecionado) {
                RoboController.clicarDestino(mouseEvent, roboExplorador, matrizCidade);
                stackRoboEx.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);// Desseleciona após definir o destino
                RoboController.iniciarAnimacao(roboExplorador,matrizCidade,gridCidade);
                roboSelecionado = false;
            }
            // Se roboSelecionado for false, o clique no grid não faz nada.
        });

        //imagem do botão para construir
        ImageView imageBotao = new ImageView(new Image(getClass().getResourceAsStream("/sprites/botaoConstruir.png")));
        imageBotao.setFitWidth(150);
        imageBotao.setFitHeight(150);
        imageBotao.setPreserveRatio(true);
        imageBotao.setSmooth(true);

        Button botaoContruir = new Button();
        botaoContruir.setPrefSize(10, 10);
        botaoContruir.setMaxSize(150, 150);
        botaoContruir.setMinSize(10, 10);

        botaoContruir.setGraphic(imageBotao);
        gridCidade.add(botaoContruir,47,24);

    }

    /**
     * Método que configura as linhas e colunas do gridCidade.
     */
    private void configurarGrid() {
        //Objeto para definir o tamanho dos blocos de repetição
        BackgroundSize backgroundSize = new BackgroundSize(
                30, 30,
                false, false, false, false);

        // Repete os blocos para o fundo do game
        BackgroundImage backgroundTile = new BackgroundImage(
                blocoCidade,
                BackgroundRepeat.REPEAT, // Repete horizontalmente
                BackgroundRepeat.REPEAT, // Repete verticalmente
                null,                    // Sem posição
                backgroundSize           // Tamanho da repetição
        );

        //Cria o fundo e coloca no gridpane da cidade
        Background background = new Background(backgroundTile);
        gridCidade.setBackground(background);
        // Configurações originais para Gaps e Restrições de Layout


        for (int i = 0; i < LINHAS; i++) {
            gridCidade.getRowConstraints().add(new RowConstraints(TAMANHO_CELULA));
        }

        for (int i = 0; i < COLUNAS; i++) {
            gridCidade.getColumnConstraints().add(new ColumnConstraints(TAMANHO_CELULA));
        }

        for (int i = 0; i < LINHAS; i++) {
            for (int j = 49; j < COLUNAS; j++) {
                matrizCidade[i][j] = true;
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 39; j < COLUNAS; j++) {
                matrizCidade[i][j] = true;
            }
        }
    }

    public void addPredio(int posicaoY, int posicaoX, ImageView ImagePredio){

        ImagePredio.setFitWidth(250);
        ImagePredio.setFitHeight(250);
        ImagePredio.setPreserveRatio(true);
        ImagePredio.setSmooth(true);
        gridCidade.add(ImagePredio, posicaoX, posicaoY);
        for (int i=posicaoY-3; i<posicaoY+4; i++){
            for (int j=posicaoX; j<posicaoX+8; j++){
                matrizCidade[i][j] = true;
            }
        }
    }

    /**Método para carregar os status da cidade
     */
    public void carregarStatus(){

        //Exibição da barra de recursos
        ImageView image2 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/barraDeRecursos.png")));
        image2.setFitWidth(190);
        image2.setFitHeight(190);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);
        //utilização de um label para mostrar a quantidade de recursos
        Label nRecursos = new Label(String.valueOf(BancoDeDados.getCidade().getRecursos()));
        nRecursos.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black;");

        //Exibição da barra de baterias
        ImageView image3 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/barraDeBaterias.png")));
        image3.setFitWidth(120);
        image3.setFitHeight(120);
        image3.setPreserveRatio(true);
        image3.setSmooth(true);
        //utilização de um label para mostrar a quantidade de baterias
        Label nBaterias = new Label(String.valueOf(BancoDeDados.getCidade().getBaterias()));
        nBaterias.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black;");

        //imagem da arvore
        ImageView image5;
        for (int i=0; i<LINHAS; i=i+3){
            image5 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/arvore.png")));
            image5.setFitWidth(200);
            image5.setFitHeight(200);
            image5.setPreserveRatio(true);
            image5.setSmooth(true);
            gridCidade.add(image5, 47, i);
        }
        gridCidade.add(image2,39,1);
        gridCidade.add(image3,46,1);
        gridCidade.add(nRecursos,44,1);
        gridCidade.add(nBaterias,48,1);
    }
}
