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
    private final int TAMANHO_CELULA = 30;
    private final int LINHAS = 780 / TAMANHO_CELULA;
    private final int COLUNAS = 1320 / TAMANHO_CELULA;
    private RoboController roboController;
    private Image blocoCidade = new Image(getClass().getResourceAsStream("/sprites/blocoDaCidade.png"));

    @FXML
    private GridPane gridCidade;

    /**Initialize do Grid que chama o configurarGrid, define a posição inicial do robô, cria um novo roboController enviando as posições iniciais e o gridCidade, e define um setOnMouseClick.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarGrid();

        int posicaoInicialX = COLUNAS / 2;
        int posicaoInicialY = LINHAS / 2;

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

        PredioCentral predioCentral = new PredioCentral(posicaoInicialX,posicaoInicialY);
        addPredio(predioCentral.getPosicaoX(),predioCentral.getPosicaoY(),predioCentral);

        roboExplorador = new RoboExplorador(posicaoInicialX,posicaoInicialY);
        roboController = new RoboController(posicaoInicialX,posicaoInicialY,gridCidade,roboExplorador);
        gridCidade.setOnMouseClicked(this::clicarDestino);

    }

    /**
     * Método que configura as linhas e colunas do gridCidade.
     */
    private void configurarGrid() {

        BackgroundSize backgroundSize = new BackgroundSize(
                TAMANHO_CELULA, TAMANHO_CELULA,
                false, false, false, false);

        // 2. Cria o BackgroundImage com repetição de padrão (TILE)
        BackgroundImage backgroundTile = new BackgroundImage(
                blocoCidade,
                BackgroundRepeat.REPEAT, // Repete horizontalmente
                BackgroundRepeat.REPEAT, // Repete verticalmente
                null,                    // Sem posição
                backgroundSize           // Tamanho da repetição
        );

        // 3. Cria o Background e o aplica ao gridCidade
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

}
