package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import model.Cidade;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe controller da cidade.
 */
public class CidadeController implements Initializable {

    /**
     * Atributos da classe, tamanho das células do grid, número de linhas e colunas, gridCidade e roboController.
     */
    private final int TAMANHO_CELULA = 29;
    private final int LINHAS = 700 / TAMANHO_CELULA;
    private final int COLUNAS = 1280 / TAMANHO_CELULA;

    @FXML
    private GridPane gridCidade;

    private RoboController roboController;

    /**
     * Initialize do Grid que chama o configurarGrid, define a posição inicial do robô, cria um novo roboController enviando as posições iniciais e o gridCidade, e define um setOnMouseClick.
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

        roboController = new RoboController(posicaoInicialX, posicaoInicialY, gridCidade);

        gridCidade.setOnMouseClicked(this::clicarDestino);

    }

    /**
     * Método que configura as linhas e colunas do gridCidade.
     */
    private void configurarGrid() {
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
}
