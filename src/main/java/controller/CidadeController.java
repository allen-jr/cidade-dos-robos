package controller;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;

/**Classe controller da cidade.
 */
public class CidadeController implements Initializable {

    /**Atributos da classe, tamanho das células do grid, número de linhas e colunas, gridCidade e roboController.
     */
    RoboExplorador roboExplorador;
    PredioCentral predioCentral;
    private final int TAMANHO_CELULA = 30;
    private final int LINHAS = 780 / TAMANHO_CELULA;
    private final int COLUNAS = 1320 / TAMANHO_CELULA;
    private RoboController roboController;
    private Image blocoCidade = new Image(getClass().getResourceAsStream("/sprites/blocoDaCidade.png"));
    private final String ESTILO_SELECIONADO = "-fx-effect: dropshadow(three-pass-box, yellow, 10, 0.5, 0, 0); -fx-border-color: yellow; -fx-border-width: 3;";
    private final String ESTILO_NAO_SELECIONADO = "";
    private ImageView roboEx;
    private ImageView predioPrincipal;

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
        addPredio(predioCentral.getPosicaoX(),predioCentral.getPosicaoY(),predioCentral);
        //Adiciona o robo no gridpane
        roboExplorador = new RoboExplorador(posicaoInicialX,posicaoInicialY);
        roboController = new RoboController(posicaoInicialX,posicaoInicialY,gridCidade,roboExplorador);
        //Salva a imagem do robo
        roboEx = roboController.getRoboSprite();
        //Clicar no robo para direciona-lo em um local
        roboEx.setOnMouseClicked(event -> {
            if (roboEx != null) {
                roboEx.setStyle(ESTILO_SELECIONADO);
                // Se o gridCidade tiver um handler de clique, você pode querer consumi-lo
                event.consume();
            }
        });
        gridCidade.setOnMouseClicked(this::clicarDestino);

    }

    /**
     * Método que configura as linhas e colunas do gridCidade.
     */
    private void configurarGrid() {
        //Objeto para definir o tamanho dos blocos de repetição
        BackgroundSize backgroundSize = new BackgroundSize(
                TAMANHO_CELULA, TAMANHO_CELULA,
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
        gridCidade.setHgap(0.0);
        gridCidade.setVgap(0.0);

        for (int i = 0; i < LINHAS; i++) {
            gridCidade.getRowConstraints().add(new RowConstraints(TAMANHO_CELULA));
        }

        for (int i = 0; i < COLUNAS; i++) {
            gridCidade.getColumnConstraints().add(new ColumnConstraints(TAMANHO_CELULA));
        }
    }


    /**
     * Método chamado ao clicar em uma célula do gridCidade, envia as posições da célula clicada ao roboController.
     * @param event
     */
    private void clicarDestino(MouseEvent event) {
        int destinoX = (int) (event.getX() / TAMANHO_CELULA);
        int destinoY = (int) (event.getY() / TAMANHO_CELULA);

        if (roboEx != null) {
            roboEx.setStyle(ESTILO_NAO_SELECIONADO);
        }
        roboController.definirDestino(destinoX, destinoY);
    }

    public void addPredio(int posicaoX, int posicaoY, PredioGeral predio){
        ImageView imagem = new ImageView(predio.getImage());
        imagem.setFitWidth(250);
        imagem.setFitHeight(250);
        imagem.setPreserveRatio(true);
        imagem.setSmooth(true);
        gridCidade.add(imagem, posicaoX, posicaoY);
    }

    /**Método para carregar os status da cidade
     */
    public void carregarStatus(){
        //Exibição da barra de energia
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/sprites/barraDeEnergia.png")));
        image.setFitWidth(97);
        image.setFitHeight(97);
        image.setPreserveRatio(true);
        image.setSmooth(true);
        //Exibição da barra de recursos
        ImageView image2 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/barraDeRecursos.png")));
        image2.setFitWidth(190);
        image2.setFitHeight(190);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);
        //Exibição da barra de baterias
        ImageView image3 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/barraDeBaterias.png")));
        image3.setFitWidth(130);
        image3.setFitHeight(130);
        image3.setPreserveRatio(true);
        image3.setSmooth(true);

        gridCidade.add(image,40,1);
        gridCidade.add(image2,27,1);
        gridCidade.add(image3,35,1);
    }
}
