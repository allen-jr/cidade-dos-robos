package Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import Model.*;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**Classe controller da cidade.
 */
public class CidadeController implements Initializable{
    private RoboExplorador roboExplorador;
    private PredioCentral predioCentral;
    private PredioFabrica predioFabrica = BancoDeDados.getCidade().getPredioFabrica();
    private RoboConstrutor roboConstrutor;

    private final int TAMANHO_CELULA = 30;
    private final int LINHAS = 960 / TAMANHO_CELULA;
    private final int COLUNAS = 1500 / TAMANHO_CELULA;

    private Image blocoCidade = new Image(getClass().getResourceAsStream("/sprites/NativoCidade/blocoDaCidade.png"));
    private final String ESTILO_SELECIONADO = "-fx-effect: dropshadow(three-pass-box, yellow, 10, 0.5, 0, 0); -fx-border-color: yellow; -fx-border-width: 3;";
    private final String ESTILO_NAO_SELECIONADO = "";

    private StackPane stackRoboEx;
    private StackPane stackRoboC;
    private StackPane stackPredioCentral;
    private StackPane stackPredioFabrica = predioFabrica.getStackPredio();

    private boolean[][] matrizCidade = new boolean[LINHAS][COLUNAS+1]; //Matriz para evitar colisões
    boolean roboSelecionado1 = false;
    boolean roboSelecionado2 = false;
    boolean predioSelecionado = false;
    boolean confirmacao;

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
        stackPredioCentral = predioCentral.getStackPredio();
        addPredio(predioCentral.getPosicaoY(),predioCentral.getPosicaoX(),stackPredioCentral);
        stackPredioCentral.setOnMouseClicked(mouseEvent -> {
            if (!predioSelecionado) {
                stackPredioCentral.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                predioSelecionado = true;
            } else {
                stackPredioCentral.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                predioSelecionado = false;
            }
            mouseEvent.consume();
        });
        //Adiciona o robo no gridpane
        roboExplorador = new RoboExplorador(posicaoInicialX-3,posicaoInicialY);
        roboConstrutor = new RoboConstrutor(1,1);
        //Salva a stack do robo
        stackRoboEx = roboExplorador.getRoboStack();
        stackRoboC = roboConstrutor.getRoboStack();
        roboConstrutor = new RoboConstrutor(10,23);
        RoboController.addRobo(stackRoboEx,gridCidade,roboExplorador.getPosicaoY(), roboExplorador.getPosicaoX());
        //Clicar no robo para direciona-lo em um local
        // Adicione uma variável de estado na classe Cidade
        stackRoboEx.setOnMouseClicked(event -> {
            if (!roboSelecionado1) {
                stackRoboEx.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                roboSelecionado1 = true;
            } else {
                stackRoboEx.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                roboSelecionado1 = false;
            }
            event.consume();
        });

        stackRoboC.setOnMouseClicked(event -> {
            if (!roboSelecionado2) {
                stackRoboC.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                roboSelecionado2 = true;
            } else {
                stackRoboC.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                roboSelecionado2 = false;
            }
            event.consume();
        });

        stackPredioFabrica.setOnMouseClicked(mouseEvent -> {
            if (!predioSelecionado && stackPredioFabrica != null) {
                stackPredioFabrica.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                predioSelecionado = true;
            } else {
                if (stackPredioFabrica != null){
                    stackPredioFabrica.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                    predioSelecionado = false;
                }
            }
            exibirMenuDeRobo();
            mouseEvent.consume();
        });

        gridCidade.setOnMouseClicked(mouseEvent -> {
            if (roboSelecionado1) {
                RoboController.clicarDestino(mouseEvent, roboExplorador, matrizCidade);
                stackRoboEx.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                RoboController.iniciarAnimacao(roboExplorador,matrizCidade,gridCidade);
                roboSelecionado1 = false;
            } else if (roboSelecionado2){
                RoboController.clicarDestino(mouseEvent, roboConstrutor, matrizCidade);
                stackRoboC.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                RoboController.iniciarAnimacao(roboConstrutor,matrizCidade,gridCidade);
                roboSelecionado2 = false;
            }
        });

        //imagem do botão para construir
        ImageView imageBotao = new ImageView(new Image(getClass().getResourceAsStream("/sprites/NativoCidade/botaoConstruir.png")));
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

        botaoContruir.setOnAction(event -> {
            exibirMenuConstruir();
        });

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

    public void addPredio(int posicaoY, int posicaoX, StackPane stackPredio){
        gridCidade.add(stackPredio, posicaoX, posicaoY);
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
        ImageView image2 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/NativoCidade/barraDeRecursos.png")));
        image2.setFitWidth(190);
        image2.setFitHeight(190);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);
        //utilização de um label para mostrar a quantidade de recursos
        Label nRecursos = new Label(String.valueOf(BancoDeDados.getCidade().getRecursos()));
        nRecursos.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black;");

        //Exibição da barra de baterias
        ImageView image3 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/NativoCidade/barraDeBaterias.png")));
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
            image5 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/NativoCidade/arvore.png")));
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

    public void exibirMenuConstruir(){
        //Cria uma stage secundária
        Stage menu = new Stage();
        menu.setTitle("MENU DE CONSTRUÇÃO");
        menu.setResizable(false);
        //Vbox para organizar os nós do stage
        VBox VBox = new VBox(20);
        VBox.setPadding(new Insets(20));
        VBox.setAlignment(Pos.TOP_CENTER);
        //Label para fornecer informações
        Label label1 = new Label("ESCOLHA UM PRÉDIO PARA CONSTRUIR:");
        label1.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold; -fx-text-fill: Black;");
        Label label2 = new Label("(CLIQUE NO PRÉDIO PARA CONSTRUIR)");
        label2.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold; -fx-text-fill: Black;");
        //Hbox para organizar os StackPane de construção
        HBox hboxMeio = new HBox(50);
        hboxMeio.setAlignment(Pos.CENTER);
        //Coloca o StackPane da fábrica de robôs
        StackPane stackPaneFabrica = criarStackPane(
                new Image(getClass().getResourceAsStream("/sprites/Predios/FabricaDeRobos.png")),
                new Image(getClass().getResourceAsStream("/sprites/Valores/valor_0.png"))
        );
        //verifica se ja colocou no mapa
        if (!predioFabrica.getConstruido()) {
            hboxMeio.getChildren().add(stackPaneFabrica);
        }
        // 4. CONTEÚDO DO FUNDO: O Botão Fechar
        Button closeButton = new Button("Fechar");
        closeButton.setOnAction(e -> menu.close());
        //Adiciona tudo ao Vbox
        VBox.getChildren().addAll(
                label1,
                label2,
                hboxMeio,
                closeButton
        );
        //Define o scene para o stage
        Scene cena = new Scene(VBox, 700, 500);
        menu.setScene(cena);
        //evento para construir a fabrica caso clique nela
        stackPaneFabrica.setOnMouseClicked( mouseEvent -> {
            if (confirmar()){
                construirPredio(hboxMeio,stackPaneFabrica,predioFabrica);
                menu.close();
            }
        });
        //sáida da stage
        menu.showAndWait();
    }

    public StackPane criarStackPane(Image imagemPredio, Image imagemValor){
        ImageView image1 = new ImageView(imagemPredio);
        image1.setFitWidth(150);
        image1.setFitHeight(150);
        image1.setPreserveRatio(true);
        image1.setSmooth(true);

        ImageView image2 = new ImageView(imagemValor);
        image2.setFitWidth(60);
        image2.setFitHeight(60);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.getChildren().addAll(image1,image2);
        StackPane.setMargin(image2, new Insets(+150,0,0,0));
        return stackPane;
    }

    public void exibirMenuDeRobo(){
        //Cria uma stage secundária
        Stage menu = new Stage();
        menu.setTitle("MENU DE FABRICAR ROBÔ");
        menu.setResizable(false);
        //Vbox para organizar os nós do stage
        VBox VBox = new VBox(20);
        VBox.setPadding(new Insets(20));
        VBox.setAlignment(Pos.TOP_CENTER);
        //Label para fornecer informações
        Label label1 = new Label("ESCOLHA UM ROBÔ PARA FABRICAR:");
        label1.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold; -fx-text-fill: Black;");
        Label label2 = new Label("(CLIQUE NO ROBÔ PARA FABRICAR)");
        label2.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold; -fx-text-fill: Black;");
        //Hbox para organizar os StackPane de construção
        HBox hboxMeio = new HBox(50);
        hboxMeio.setAlignment(Pos.CENTER);
        //Coloca o StackPane da fábrica de robôs
        StackPane stackPaneRobo = criarStackPane(
                new Image(getClass().getResourceAsStream("/sprites/Robos/robo-construtor-1.png")),
                new Image(getClass().getResourceAsStream("/sprites/Valores/valor_0.png"))
        );
        //verifica se ja colocou no mapa
        if (!roboConstrutor.isFabricado()) {
            hboxMeio.getChildren().add(stackPaneRobo);
        }
        // 4. CONTEÚDO DO FUNDO: O Botão Fechar
        Button closeButton = new Button("Fechar");
        closeButton.setOnAction(e -> menu.close());
        //Adiciona tudo ao Vbox
        VBox.getChildren().addAll(
                label1,
                label2,
                hboxMeio,
                closeButton
        );
        //Define o scene para o stage
        Scene cena = new Scene(VBox, 700, 500);
        menu.setScene(cena);
        //evento para construir a fabrica caso clique nela
        stackPaneRobo.setOnMouseClicked( mouseEvent -> {
           if (confirmar()){
               fabricarRobo(hboxMeio,roboConstrutor,stackPaneRobo);
               menu.close();
           }
        });
        //sáida da stage
        menu.showAndWait();
        stackPredioFabrica.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
    }

    public void fabricarRobo(HBox hbox, Robo robo,StackPane stackPaneRobo){
        if (BancoDeDados.getCidade().getRecursos() >= 0 ){
            gridCidade.add(stackRoboC,21,16);
            hbox.getChildren().remove(stackPaneRobo);
            roboConstrutor.setFabricado();
        } else {
            exibirMensagem();
        }
    }

    public void construirPredio(HBox hbox, StackPane stackPredio, PredioGeral predio) {
        if (BancoDeDados.getCidade().getRecursos() >= 0 ){
            addPredio(7,10,predio.getStackPredio());
            hbox.getChildren().remove(stackPredio);
            predioFabrica.setConstruido();
        } else {
            exibirMensagem();
        }
    }

    public void exibirMensagem(){
        //
        Stage alertaStage = new Stage();
        alertaStage.setTitle("Alerta de Recursos");
        //
        VBox rootVBox = new VBox(15);
        rootVBox.setPadding(new Insets(20));
        rootVBox.setAlignment(Pos.CENTER);
        // Label com a mensagem de erro
        Label mensagemLabel = new Label("Você não tem recursos suficientes");
        mensagemLabel.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold; -fx-text-fill: red;");
        //
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> alertaStage.close());
        // Adiciona os componentes ao VBox
        rootVBox.getChildren().addAll(mensagemLabel, okButton);
        //
        Scene cena = new Scene(rootVBox, 350, 150);
        alertaStage.setScene(cena);

        //
        alertaStage.showAndWait();
    }

    public boolean confirmar(){
        Stage confirmar = new Stage();
        confirmar.setTitle("Confirmar");
        confirmar.setResizable(false);
        confirmacao = false;

        VBox raizVBox = new VBox(15);
        raizVBox.setAlignment(Pos.CENTER);
        raizVBox.setPadding(new Insets(20));

        Label mensagemLabel = new Label("Você realmente deseja fazer isso?");
        mensagemLabel.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold; -fx-text-fill: black;");

        HBox escolha = new HBox(10);
        escolha.setAlignment(Pos.CENTER);

        Button sim =  new Button("Confirmar");
        sim.setOnAction(e -> {
            confirmacao = true;
            confirmar.close();
        });
         Button nao = new Button("Cancelar");
         nao.setOnAction(e -> {
             confirmacao = false;
             confirmar.close();
         });

         escolha.getChildren().addAll(sim, nao);
         raizVBox.getChildren().addAll(mensagemLabel,escolha);
         confirmar.setScene(new Scene(raizVBox, 350, 150));
         confirmar.showAndWait();
         return  confirmacao;
    }

}
