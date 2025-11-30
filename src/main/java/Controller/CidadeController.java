package Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import Model.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**Classe controller da cidade.
 */
public class CidadeController implements Initializable {
    //Prédios
    private PredioCentral predioCentral = BancoDeDados.getCidade().getPredioCentral();
    private PredioFabrica predioFabrica = BancoDeDados.getCidade().getPredioFabrica();
    private PredioBateria predioBateria = BancoDeDados.getCidade().getPredioBateria();
    //Robôs
    private RoboConstrutor roboConstrutor =  BancoDeDados.getCidade().getRoboConstrutor();
    private RoboExplorador roboExplorador = BancoDeDados.getCidade().getRoboExplorador();
    private RoboEngenheiro roboEngenheiro = BancoDeDados.getCidade().getRoboEngenheiro();
    //Linhas e colunas do gridpane
    private final int TAMANHO_CELULA = 30;
    private final int LINHAS = 960 / TAMANHO_CELULA;
    private final int COLUNAS = 1500 / TAMANHO_CELULA;
    //Imagem do bloco da cidade
    private Image blocoCidade = new Image(getClass().getResourceAsStream("/sprites/NativoCidade/blocoDaCidade.png"));
    //Estilos dos robôs
    private final String ESTILO_SELECIONADO = "-fx-effect: dropshadow(three-pass-box, yellow, 10, 0.5, 0, 0); -fx-border-color: yellow; -fx-border-width: 3;";
    private final String ESTILO_NAO_SELECIONADO = "";
    //StackPane dos robôs
    private StackPane stackRoboC = roboConstrutor.getRoboStack();
    private StackPane stackRoboEx = roboExplorador.getRoboStack();
    private StackPane stackRoboEng = roboEngenheiro.getRoboStack();
    //StackPane dos Prédios
    private StackPane stackPredioCentral = predioCentral.getStackPredio();
    private StackPane stackPredioFabrica = predioFabrica.getStackPredio();
    private StackPane stackPredioBateria = predioBateria.getStackPredio();
    //Matriz para tratar colisão
    private boolean[][] matrizCidade = new boolean[LINHAS][COLUNAS + 1];
    //variáveis booleanas para a movimentação dos robôs
    private boolean roboSelecionado1 = false;
    private boolean roboSelecionado2 = false;
    private boolean roboSelecionado3 = false;
    //Variaveis booleanas para o estilo dos prédios
    private boolean predioSelecionado = false;
    //Variavél booleana para confirmar a construção de prédioa e fabricação de robôs
    private boolean confirmacao;
    //utilização de um label para mostrar a quantidade de recursos
    Label nRecursos = new Label(String.valueOf(BancoDeDados.getCidade().getRecursos()));
    //utilização de um label para mostrar a quantidade de baterias
    Label nBaterias = new Label(String.valueOf(BancoDeDados.getCidade().getBaterias()));
    //
    StackPane stackNRecurso = new StackPane();
    StackPane stackNBateria = new StackPane();

    @FXML
    private GridPane gridCidade;

    /**
     * Inicializa o gridpane (chama o método configurarGrid()) e coloca os nodes iniciais dele
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Configura o gridpane
        configurarGrid();
        //Chama o método para carregar os status
        carregarStatus();
        //Reconstrói os prédios e refabrica os robôs do ultimo save
        reconstruir();
        //Adiciona o prédio central no gridpane
        if (predioCentral != null) {
            stackPredioCentral = predioCentral.getStackPredio();
            addPredio(predioCentral.getPosicaoY(), predioCentral.getPosicaoX(), stackPredioCentral);
        }
        //evento de clique no predio principal
        stackPredioCentral.setOnMouseClicked(mouseEvent -> {
            if (!predioSelecionado) {
                //coloca estilo no prédio central
                stackPredioCentral.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                predioSelecionado = true;
            } else {
                //remove o estilo do prédio central
                stackPredioCentral.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                predioSelecionado = false;
            }
            mouseEvent.consume();
        });
        //Adiciona o robo explorador na cidade
        if (stackRoboEx != null) {
            RoboController.addRobo(stackRoboEx, gridCidade, roboExplorador.getPosicaoY(), roboExplorador.getPosicaoX());
        }
        //evento de clique no robo explorador
        stackRoboEx.setOnMouseClicked(event -> {
            if (!roboSelecionado1) {
                //coloca o estilo no robo explorador
                stackRoboEx.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                //método para mostrar as informações do robô
                RoboController.mostrarInfoRobo(roboExplorador);
                //variavel que ajudará no movimento do robo
                roboSelecionado1 = true;
            } else {
                //remove o estilo
                stackRoboEx.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                roboSelecionado1 = false;
            }
            event.consume();
        });
        //evento de clique no robo construtor
        stackRoboC.setOnMouseClicked(event -> {
            if (!roboSelecionado2) {
                //coloca o estilo no robo construtor
                stackRoboC.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                //método para mostrar as informações do robô
                RoboController.mostrarInfoRobo(roboConstrutor);
                //variavel que ajudará no movimento do robo
                roboSelecionado2 = true;
            } else {
                stackRoboC.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                roboSelecionado2 = false;
            }
            event.consume();
        });
        //evento de clique no robo engenheiro
        stackRoboEng.setOnMouseClicked(event -> {
            if (!roboSelecionado3) {
                //coloca o estilo no robo engenheiro
                stackRoboEng.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                //método para mostrar as informações do robô
                RoboController.mostrarInfoRobo(roboEngenheiro);
                //variavel que ajudará no movimento do robo
                roboSelecionado3 = true;
            } else {
                stackRoboEng.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                roboSelecionado3 = false;
            }
            event.consume();
        });
        //evento de clique na fabrica de robos
        stackPredioFabrica.setOnMouseClicked(mouseEvent -> {
            if (!predioSelecionado && stackPredioFabrica != null) {
                //coloca estilo de seleção
                stackPredioFabrica.getChildren().getFirst().setStyle(ESTILO_SELECIONADO);
                predioSelecionado = true;
            } else {
                if (stackPredioFabrica != null) {
                    //remove o estilo de seleção
                    stackPredioFabrica.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                    predioSelecionado = false;
                }
            }
            //mostra o menu de fabricar robo
            exibirMenuFabricar();
            mouseEvent.consume();
        });
        //evento de clique na cidade para mover o robô
        gridCidade.setOnMouseClicked(mouseEvent -> {
            //se roboSelecionado1 for true, move o robo explorador
            if (roboSelecionado1) {
                //método para definir o destino do robô
                RoboController.clicarDestino(mouseEvent, roboExplorador, matrizCidade);
                //remove o estilo
                stackRoboEx.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                //inicia a animação de mover o robô
                RoboController.iniciarAnimacao(roboExplorador, matrizCidade, gridCidade);
                roboSelecionado1 = false;
            }//se roboSelecionado2 for true, move o robo construtor
            else if (roboSelecionado2) {
                //método para definir o destino do robô
                RoboController.clicarDestino(mouseEvent, roboConstrutor, matrizCidade);
                //remove o estilo
                stackRoboC.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                //inicia a animação de mover o robô
                RoboController.iniciarAnimacao(roboConstrutor, matrizCidade, gridCidade);
                roboSelecionado2 = false;
            }//se roboSelecionado3 for true, move o robo engenheiro
            else if (roboSelecionado3) {
                //método para definir o destino do robô
                RoboController.clicarDestino(mouseEvent, roboEngenheiro, matrizCidade);
                //remove o estilo
                stackRoboEng.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
                //inicia a animação de mover o robô
                RoboController.iniciarAnimacao(roboEngenheiro, matrizCidade, gridCidade);
                roboSelecionado3 = false;
            }
        });
        //imagem do botão para construir
        ImageView imageBotao = new ImageView(new Image(getClass().getResourceAsStream("/sprites/NativoCidade/botaoConstruir.png")));
        imageBotao.setFitWidth(150);
        imageBotao.setFitHeight(150);
        imageBotao.setPreserveRatio(true);
        imageBotao.setSmooth(true);
        //Criando o botão de construir
        Button botaoContruir = new Button();
        botaoContruir.setPrefSize(10, 10);
        botaoContruir.setMaxSize(150, 150);
        botaoContruir.setMinSize(10, 10);
        //adicionando a imagem ao botão de construir
        botaoContruir.setGraphic(imageBotao);
        //colocando o botão de construir na grid da cidade
        gridCidade.add(botaoContruir, 47, 24);
        //evento de clique no botão de construir
        botaoContruir.setOnAction(event -> {
            //exibe uma stage para construir prédios
            exibirMenuConstruir();
        });
        nRecursos.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black;");
        nBaterias.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: black;");
        atualizarRecursos();
        //Imagem da barra de recursos
        ImageView image2 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/NativoCidade/barraDeRecursos.png")));
        image2.setFitWidth(190);
        image2.setFitHeight(190);
        image2.setPreserveRatio(true);
        image2.setSmooth(true);
        //Imagem da barra de baterias
        ImageView image3 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/NativoCidade/barraDeBaterias.png")));
        image3.setFitWidth(120);
        image3.setFitHeight(120);
        image3.setPreserveRatio(true);
        image3.setSmooth(true);
        //Adiciona a barra de recursos e a barra de baterias
        stackNRecurso.setAlignment(Pos.CENTER);
        stackNRecurso.getChildren().addAll(image2,nRecursos);
        gridCidade.add(stackNRecurso, 39, 1);
        stackNBateria.setAlignment(Pos.CENTER);
        stackNBateria.getChildren().addAll(image3,nBaterias);
        gridCidade.add(stackNBateria, 46, 1);
    }

    /**Método que configura as linhas e colunas do gridCidade.
     */
    private void configurarGrid() {
        //Objeto para definir o tamanho dos blocos de repetição
        BackgroundSize backgroundSize = new BackgroundSize(
                30, 30,
                false, false, false, false);

        // Repete os blocos para o fundo do gridpane
        BackgroundImage backgroundTile = new BackgroundImage(
                blocoCidade, //Imagem do bloco
                BackgroundRepeat.REPEAT, // Repete horizontalmente
                BackgroundRepeat.REPEAT, // Repete verticalmente
                null,                    // Sem posição
                backgroundSize           // Tamanho da repetição
        );

        //Cria o fundo e coloca no gridpane da cidade
        Background background = new Background(backgroundTile);
        gridCidade.setBackground(background);
        //Construção do gridpane pelas linhas
        for (int i = 0; i < LINHAS; i++) {
            gridCidade.getRowConstraints().add(new RowConstraints(TAMANHO_CELULA));
        }
        //Construção do gridpane pelas colunas
        for (int i = 0; i < COLUNAS; i++) {
            gridCidade.getColumnConstraints().add(new ColumnConstraints(TAMANHO_CELULA));
        }
        //marcando as 2 ultimas colunas do gridpane como true na matriz do gridpane (serve para evitar a movimentação nesses locais)
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 49; j < COLUNAS; j++) {
                matrizCidade[i][j] = true;
            }
        }
    }

    /**Método para adicionar um prédio na cidade
     * @param posicaoY posição Y do gridpane
     * @param posicaoX posicão X do gridpane
     * @param stackPredio StackPane do prédio a ser adicionado
     */
    public void addPredio(int posicaoY, int posicaoX, StackPane stackPredio) {
        //adiciona o prédio
        gridCidade.add(stackPredio, posicaoX, posicaoY);
        //marca na matriz a area do prédio, evitando que o robo possa se mover pra lá
        for (int i = posicaoY - 3; i < posicaoY + 4; i++) {
            for (int j = posicaoX; j < posicaoX + 8; j++) {
                matrizCidade[i][j] = true;
            }
        }
    }

    /** Atualiza na interface o número de recursos */
    public void atualizarRecursos() {
        nRecursos.setText(String.valueOf(BancoDeDados.getCidade().getRecursos()));
        nBaterias.setText(String.valueOf(BancoDeDados.getCidade().getBaterias()));
    }


    /**Método para carregar os status da cidade e as árvores da cidade
     */
    public void carregarStatus() {
        //imagem da arvore
        ImageView image5;
        //coloca as arvores em nas 2 ultimas colunas do gridpane
        for (int i = 0; i < LINHAS; i = i + 3) {
            image5 = new ImageView(new Image(getClass().getResourceAsStream("/sprites/NativoCidade/arvore.png")));
            image5.setFitWidth(200);
            image5.setFitHeight(200);
            image5.setPreserveRatio(true);
            image5.setSmooth(true);
            gridCidade.add(image5, 47, i);
        }
    }

    /**Método para abrir o menu para construir os prédios
     */
    public void exibirMenuConstruir() {
        //Cria uma stage secundária
        Stage menu = new Stage();
        menu.setTitle("MENU DE CONSTRUÇÃO");
        menu.setResizable(false);
        //Método para não deixar clicar na cidade
        forcarResposta(menu);
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
        //Coloca o StackPane da fábrica de baterias
        StackPane stackPaneFabricaBateria = criarStackPane(
                new Image(getClass().getResourceAsStream("/sprites/Predios/FabricaDeBateria.png")),
                new Image(getClass().getResourceAsStream("/sprites/Valores/valor_100.png"))
        );
        //verifica se ja colocou no mapa
        if (!predioBateria.getConstruido()) {
            hboxMeio.getChildren().add(stackPaneFabricaBateria);
        }

        //Botão para fechar o stage
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> menu.close());
        //Adiciona tudo ao Vbox
        VBox.getChildren().addAll(
                label1,
                label2,
                hboxMeio,
                closeButton
        );
        //Define o scene para o stage e adiciona o vbox
        Scene cena = new Scene(VBox, 700, 500);
        //adiciona o scene ao stage
        menu.setScene(cena);
        //evento para construir a fabrica, caso clique nela
        stackPaneFabrica.setOnMouseClicked(mouseEvent -> {
            //verifica se a cidade tem recurso suficiente para construir
            if (BancoDeDados.getCidade().getRecursos() >= 0) {
                //exibe um popup de confirmação de construção
                if (confirmar()) {
                    //adiciona o stackpane do prédio na cidade
                    addPredio(predioFabrica.getPosicaoY(), predioFabrica.getPosicaoX(), predioFabrica.getStackPredio());
                    //remove o prédio do menu de construção
                    hboxMeio.getChildren().remove(stackPaneFabrica);
                    //coloca o prédio como construído
                    predioFabrica.setConstruido();
                }
            } else {
                exibirMensagem();
            }
            menu.close();
        });
        //evento para construir a fabrica de bateria caso clique nela
        stackPaneFabricaBateria.setOnMouseClicked(mouseEvent -> {
            construirPredio(hboxMeio, stackPaneFabricaBateria, predioBateria, 100);
            menu.close();
        });
        //sáida da stage
        menu.showAndWait();
    }

    /**Método para criar stackpane
     * @param imagemPredio primiera imagem que será colocada no stackpane
     * @param imagemValor segunda imagem que será colocada no stackpane
     * @return retorna o stackpane criado
     */
    public StackPane criarStackPane(Image imagemPredio, Image imagemValor) {
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
        stackPane.getChildren().addAll(image1, image2);
        StackPane.setMargin(image2, new Insets(+150, 0, 0, 0));
        return stackPane;
    }

    /**Método para mostrar o menu de fabricar robôs
     */
    public void exibirMenuFabricar() {
        //Cria uma stage secundária
        Stage menu = new Stage();
        menu.setTitle("MENU DE FABRICAR ROBÔ");
        menu.setResizable(false);
        //Método para não deixar clicar na cidade
        forcarResposta(menu);
        //Vbox para organizar os nós do stage
        VBox VBox = new VBox(20);
        VBox.setPadding(new Insets(20));
        VBox.setAlignment(Pos.TOP_CENTER);
        //Label para fornecer informações
        Label label1 = new Label("ESCOLHA UM ROBÔ PARA FABRICAR:");
        label1.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold; -fx-text-fill: Black;");
        Label label2 = new Label("(CLIQUE NO ROBÔ PARA FABRICAR)");
        label2.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold; -fx-text-fill: Black;");
        //Hbox para organizar os StackPane dos robôs a serem fabricados
        HBox hboxMeio = new HBox(50);
        hboxMeio.setAlignment(Pos.CENTER);
        //Cria o StackPane do robo Construtor na fábrica de robôs
        StackPane stackPaneRobo = criarStackPane(
                new Image(getClass().getResourceAsStream("/sprites/Robos/robo-construtor-1.png")),
                new Image(getClass().getResourceAsStream("/sprites/Valores/valor_0.png"))
        );
        //verifica se ja colocou no mapa
        if (!roboConstrutor.isFabricado()) {
            //coloca o stackpane do robo construtor no menu de fabricação
            hboxMeio.getChildren().add(stackPaneRobo);
        }
        //Cria o stackpane do robo engenheiro na fabrica de robos
        StackPane stackPaneRobo2 = criarStackPane(
                new Image(getClass().getResourceAsStream("/sprites/Robos/robo-azul-1.png")),
                new Image(getClass().getResourceAsStream("/sprites/Valores/valor_0.png"))
        );
        //verifica se ja colocou no mapa
        if (!roboEngenheiro.isFabricado()){
            //coloca o stackpane do robo engenheiro no menu de fabricação
            hboxMeio.getChildren().add(stackPaneRobo2);
        }
        //Botao para fechar o stage
        Button closeButton = new Button("OK");
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
        //coloca o scene no stage
        menu.setScene(cena);
        //evento para fabricar o robo construtor caso clique nele
        stackPaneRobo.setOnMouseClicked(mouseEvent -> {
            fabricarRobo(hboxMeio, roboConstrutor, stackPaneRobo, 0);
            menu.close();
        });
        //evento para fabricar o robo engenheiro caso clique nele
        stackPaneRobo2.setOnMouseClicked(mouseEvent -> {
            fabricarRobo(hboxMeio, roboEngenheiro, stackPaneRobo2, 0);
            menu.close();
        });
        //sáida da stage
        menu.showAndWait();
        //remove o estilo do prédio de fabricar robôs
        stackPredioFabrica.getChildren().getFirst().setStyle(ESTILO_NAO_SELECIONADO);
    }

    /**Método para fabricar o robô e colocar na cidade
     * @param hbox Hbox utilizada no método exibirMenufabricar()
     * @param robo Objeto do tipo robô a ser fabricado
     * @param stackPaneRobo stackpane do robô a ser fabricado
     * @param valor valor do robô para fabricar
     */
    public void fabricarRobo(HBox hbox, Robo robo, StackPane stackPaneRobo, int valor) {
        //verifica se a cidade tem recurso suficiente para fabricar
        if (BancoDeDados.getCidade().getRecursos() >= valor) {
            //exibe um popup de confirmação de fabricação
            if (confirmar()) {
                //adiciona o stackpane do robô na cidade
                RoboController.addRobo(robo.getRoboStack(), gridCidade, robo.getPosicaoY(), robo.getPosicaoX());
                //remove o robô do menu de fabricação
                hbox.getChildren().remove(stackPaneRobo);
                //coloca o robô como fabricado
                robo.setFabricado();
                //Diminui o valor do recurso
                BancoDeDados.getCidade().diminuirRecursos(valor);
            }
        } else {
            //exibe um popup indicando a falta de recurso
            exibirMensagem();
        }
    }

    /**Método para construir um prédio na cidade
     * @param hbox Hbox utilizada no método exibirMenuConstruir()
     * @param stackPredio stackpane do prédio a ser construído
     * @param predio objeto do prédio a ser construído
     * @param valor valor para construir o prédio
     */
    public void construirPredio(HBox hbox, StackPane stackPredio, PredioGeral predio, int valor) {
        //verifica se o robô construtor está fabricado
        if (roboConstrutor.isFabricado()) {
            //verifica se a cidade tem recurso suficiente para construir
            if (BancoDeDados.getCidade().getRecursos() >= valor) {
                //exibe um popup de confirmação de construção
                if (confirmar()) {
                    //adiciona o stackpane do prédio na cidade
                    addPredio(predio.getPosicaoY(), predio.getPosicaoX(), predio.getStackPredio());
                    //remove o prédio do menu de construção
                    hbox.getChildren().remove(stackPredio);
                    //coloca o prédio como construído
                    predio.setConstruido();
                    //Diminui o valor do recurso
                    BancoDeDados.getCidade().diminuirRecursos(valor);
                }
            } else {
                exibirMensagem();
            }
        } else {
            erroConstrutor();
        }
    }

    /**Método para exibir uma mensagem indicando a falta de recurso
     */
    public void exibirMensagem() {
        //cria um stage
        Stage alertaStage = new Stage();
        alertaStage.setTitle("Alerta de Recursos");
        //Método para não deixar clicar na cidade
        forcarResposta(alertaStage);
        //Vbox para organizar as informações
        VBox rootVBox = new VBox(15);
        rootVBox.setPadding(new Insets(20));
        rootVBox.setAlignment(Pos.CENTER);
        // Label com a mensagem de erro
        Label mensagemLabel = new Label("Você não tem recursos suficientes");
        mensagemLabel.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold; -fx-text-fill: red;");
        //botão para fechar o stage
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> alertaStage.close());
        // Adiciona os componentes ao VBox
        rootVBox.getChildren().addAll(mensagemLabel, okButton);
        //define o scene e coloca o Vbox
        Scene cena = new Scene(rootVBox, 350, 150);
        //adiciona o scene ao stage
        alertaStage.setScene(cena);
        //exibe o stage ao jogador
        alertaStage.showAndWait();
    }

    public void erroConstrutor() {
        //cria um stage
        Stage alertaStage = new Stage();
        alertaStage.setTitle("Falta de construtor");
        //Método para não deixar clicar na cidade
        forcarResposta(alertaStage);
        //Vbox para organizar as informações
        VBox rootVBox = new VBox(15);
        rootVBox.setPadding(new Insets(20));
        rootVBox.setAlignment(Pos.CENTER);
        // Label com a mensagem de erro
        Label mensagemLabel = new Label("Você ainda não tem um robô construtor");
        mensagemLabel.setStyle("-fx-font-size: 8pt; -fx-font-weight: bold; -fx-text-fill: red;");
        Label mensagemLabel2 = new Label("Fabrique ele na fábrica de robôs");
        mensagemLabel2.setStyle("-fx-font-size: 8pt; -fx-font-weight: bold; -fx-text-fill: red;");
        Label mensagemLabel3 = new Label("Você ainda não tem a fábrica de robôs, construa ela");
        mensagemLabel3.setStyle("-fx-font-size: 8pt; -fx-font-weight: bold; -fx-text-fill: red;");
        //botão para fechar o stage
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> alertaStage.close());
        // Adiciona os componentes ao VBox
        rootVBox.getChildren().addAll(mensagemLabel,mensagemLabel2);
        if (!predioFabrica.getConstruido()){
            rootVBox.getChildren().add(mensagemLabel3);
        }
        rootVBox.getChildren().add(okButton);
        //define o scene e coloca o Vbox
        Scene cena = new Scene(rootVBox, 350, 150);
        //adiciona o scene ao stage
        alertaStage.setScene(cena);
        //exibe o stage ao jogador
        alertaStage.showAndWait();
    }

    /**Método para exibir um popup de confirmação
     * @return retorna a resposta em booleano, true(sim) false(não)
     */
    public boolean confirmar() {
        //cria um stage
        Stage confirmar = new Stage();
        confirmar.setTitle("Confirmar");
        confirmar.setResizable(false);
        //Método para não deixar clicar na cidade
        forcarResposta(confirmar);
        //variável booleana para a confirmação
        confirmacao = false;
        //Vbox para organizar as informações
        VBox raizVBox = new VBox(15);
        raizVBox.setAlignment(Pos.CENTER);
        raizVBox.setPadding(new Insets(20));
        //Label para exibir a mensagem de confirmação
        Label mensagemLabel = new Label("Você realmente deseja fazer isso?");
        mensagemLabel.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold; -fx-text-fill: black;");
        //Hbox para organizar os botões das escolhas
        HBox escolha = new HBox(10);
        escolha.setAlignment(Pos.CENTER);
        //botão para escolher sim
        Button sim = new Button("Confirmar");
        sim.setOnAction(e -> {
            confirmacao = true;
            confirmar.close();
        });
        //botão para escolher não
        Button nao = new Button("Cancelar");
        nao.setOnAction(e -> {
            confirmacao = false;
            confirmar.close();
        });
        //adiciona os botões no Hbox
        escolha.getChildren().addAll(sim, nao);
        //coloca o Hbox e a mensagem ao Vbox
        raizVBox.getChildren().addAll(mensagemLabel, escolha);
        //define a scene e coloca a Vbox
        confirmar.setScene(new Scene(raizVBox, 350, 150));
        //exibe o stage ao jogador
        confirmar.showAndWait();
        //retorna o resultado
        return confirmacao;
    }

    /**Método para não deixar clicar na cidade
     * @param menu stage que o jogador tera a obrigação de responder
     */
    public void forcarResposta(Stage menu){
        // Obtém a referência do Stage principal
        Stage stagePrincipal = (Stage) gridCidade.getScene().getWindow();
        // Define o Stage principal como Owner (dono)
        menu.initOwner(stagePrincipal);
        // Define a modalidade como WINDOW_MODAL, serve para evitar que receba cliques na cidade
        menu.initModality(Modality.WINDOW_MODAL);
    }

    /**Método para recuperar o ùltimo save, reconstrói os prédios e refabrica os robôs
     */
    public void reconstruir(){
        if (predioFabrica.getConstruido()){
            addPredio(predioFabrica.getPosicaoY(), predioFabrica.getPosicaoX(),predioFabrica.getStackPredio());
        }
        if (predioBateria.getConstruido()){
            addPredio(predioBateria.getPosicaoY(), predioBateria.getPosicaoX(),predioBateria.getStackPredio());
        }
        if (roboConstrutor.isFabricado()){
            RoboController.addRobo(roboConstrutor.getRoboStack(),gridCidade,roboConstrutor.getPosicaoY(),roboConstrutor.getPosicaoX());
        }
        if (roboEngenheiro.isFabricado()){
            RoboController.addRobo(roboEngenheiro.getRoboStack(),gridCidade,roboEngenheiro.getPosicaoY(),roboEngenheiro.getPosicaoX());
        }
    }
}