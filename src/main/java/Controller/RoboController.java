package Controller;
import Model.BancoDeDados;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import Model.Robo;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**Classe controller dos robôs
 */
public class RoboController {
    private static long ultimoMovimento = 0, intervaloMovimento = 500_000_000;
    private static GridPane gridCidade;


    /**Método para fazer a animação do robô
     * @param roboModelo robô que terá a animação
     * @param matrizCidade matriz booleana da cidade para tratar colisões
     * @param gridCity cidade que contém o robô
     */
    public static void iniciarAnimacao(Robo roboModelo, boolean[][] matrizCidade, GridPane gridCity) {
        gridCidade = gridCity;
        StackPane stackrobo = roboModelo.getRoboStack();
        new AnimationTimer() {
            @Override
            public void handle(long agora) {
                if (stackrobo != null && roboModelo.emMovimento() && (agora - ultimoMovimento) >= intervaloMovimento) {
                    roboModelo.mover(matrizCidade);
                    GridPane.setColumnIndex(stackrobo, roboModelo.getPosicaoX());
                    GridPane.setRowIndex(stackrobo, roboModelo.getPosicaoY());

                    ultimoMovimento = agora;
                }
            }
        }.start();
    }

    /**Método que envia o destino do robô ao seu modelo.
     * @param roboModelo robô que terá seu destino alterado
     * @param destinoX novo destino X
     * @param destinoY novo destino Y
     */
    public static void definirDestino(Robo roboModelo, int destinoX, int destinoY) {
        roboModelo.destino(destinoX, destinoY);
    }

    /**Método chamado ao clicar em uma célula do gridCidade, envia as posições da célula clicada ao roboController.
     * @param event evento de clique na cidade
     * @param robo robô que terá o destino alterado
     * @param matrizCidade matriz booleana da cidade para tratar a colisão
     */
    public static void clicarDestino(MouseEvent event, Robo robo, boolean[][] matrizCidade) {
        //destinos recebidos do evento de clique da cidade
        int destinoX = (int) (event.getX() / 30);
        int destinoY = (int) (event.getY() / 30);
        //verifica se o robô não é nulo
        if (robo != null ) {
            //remove o estilo do robô
            robo.getRoboStack().setStyle("");
            //verifica se a bateria do robô tem pelo menos 1%
            if (robo.getPorcentoBateria() >= 1){
                //verifica se o local clicado não está ocupado
                if (!matrizCidade[destinoY][destinoX]){
                    definirDestino(robo,destinoX, destinoY);
                }
            } else {
                //exibe a mensagem de falta de energia caso a bateria está descarregada
                exibirMensagemBateria();
            }
        }

    }

    /**Método que adiciona o robô à cidade
     * @param robo robô que será adicionado
     * @param gridCidade cidade que terá o robô adicionado
     * @param posicaoY posição Y que adicionará o robô
     * @param posicaoX posição X que adicionará o robô
     */
    public static void addRobo(StackPane robo, GridPane gridCidade, int posicaoY, int posicaoX) {
        gridCidade.add(robo, posicaoX, posicaoY);
    }

    /**Método para mostrar as informações do robô
     * @param robo robô que será mostrado as informações
     */
    public static void mostrarInfoRobo(Robo robo){
        //cria um stage
        Stage stage = new Stage();
        stage.setTitle("Informações do Robo");
        //cria um Vbox para organizar os componentes
        VBox rootVBox = new VBox(15);
        rootVBox.setAlignment(Pos.TOP_LEFT);
        rootVBox.setPadding(new Insets(15));
        //label para mostrar o nome do robô
        Label nomeRobo = new Label("NOME: Robô "+robo.getNome());
        nomeRobo.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: Black;");
        //ImageView para mostrar um icone de robô e definição das propriedades
        ImageView imageRosto = new ImageView( new Image(RoboController.class.getResourceAsStream("/sprites/Robos/rostofeliz.png")));
        imageRosto.setFitWidth(50);
        imageRosto.setFitHeight(50);
        imageRosto.setPreserveRatio(true);
        imageRosto.setSmooth(true);
        //botão para executar a ação do robô
        Button botaoAcao = new Button("TRABALHAR");
        botaoAcao.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: Green;");
        //botão para mover o robô
        Button mover = new Button("MOVER");
        mover.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: Black;");
        //Hbox para organizar o stackpane 1
        HBox informacao1 = new HBox(15);
        informacao1.setAlignment(Pos.CENTER);
        //Hbox para organizar o stackpane2
        HBox informacao2 = new HBox(15);
        informacao2.setAlignment(Pos.CENTER);
        //cria o stackpane da bateria
        StackPane stackBateria = new StackPane();
        stackBateria.setAlignment(Pos.CENTER_LEFT);
        //cria o stackpane do rosto
        StackPane stackRosto = new StackPane();
        stackRosto.setAlignment(Pos.CENTER_LEFT);
        //cria o ImageView do sprite atual da bateria do robô e define as propriedades
        ImageView imagemBateria = new ImageView(robo.getImagemBateria().getImage());
        imagemBateria.setFitHeight(90);
        imagemBateria.setFitWidth(90);
        imagemBateria.setPreserveRatio(true);
        imagemBateria.setPreserveRatio(true);
        //botão para recarregar o robô
        Button recarregar = new Button("RECARREGAR");
        recarregar.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: Blue;");
        //adiciona a ImageView da bateria e o botão de recarregar no stackBateria
        stackBateria.getChildren().addAll(imagemBateria, recarregar);
        //desloca o botão de recarregar para +200 na direita
        StackPane.setMargin(recarregar, new Insets(0, 0, 0, +200));
        //desloca o botão de mover para +120 para a direita
        StackPane.setMargin(mover, new Insets(0, 0, 0, +120));
        //desloca o botão de ação para +200 para a direita
        StackPane.setMargin(botaoAcao, new Insets(0, 0, 0, +200));
        //adiciona o icone do rosto, o botão de mover e o botão de ação no stackRosto
        stackRosto.getChildren().addAll(imageRosto,mover, botaoAcao);
        //evento de clique no botão de recarregar
        recarregar.setOnAction( actionEvent -> {
            //verifica se tem baterias suficientes na cidade
            if (BancoDeDados.getCidade().getBaterias() >= 1){
                //recarrega a bateria do robô
                robo.recarregarBateria();
                //diminui a quantidade de baterias na cidade
                BancoDeDados.getCidade().diminuirBaterias(1);
                //fecha o stage
                stage.close();
            } else {
                //exibe a mensagem de falta de baterias na cidade
                mensagemSemBaterias();
            }
        });
        //evento de clique no botão de ação
        botaoAcao.setOnAction( actionEvent -> {
            //executa a ação definida pelo robô
            robo.acaoRobo();
            //fecha o stage
            stage.close();
        });
        //evento de clique no botão de mover
        mover.setOnAction( actionEvent -> {
            //fecha o stage
            stage.close();
        });
        //adiciona ao Hbox o stackBateria
        informacao1.getChildren().addAll(stackBateria);
        //adiciona ao outro Hbox o stackRosto
        informacao2.getChildren().addAll(stackRosto);
        //adiciona o nome do robô e os Hbox ao Vbox
        rootVBox.getChildren().addAll(nomeRobo, informacao1,informacao2);
        //adiciona o scene com o Vbox e as dimensões no stage
        stage.setScene(new Scene(rootVBox, 350, 150));
        //não deixa o stage se redimensionado
        stage.setResizable(false);
        //mostra o stage e espera fechar
        stage.showAndWait();
    }

    /**Método para mostrar o alerta de falta de energia para mover
     */
    public static void exibirMensagemBateria() {
        //cria um novo stage
        Stage alertaStage = new Stage();
        alertaStage.setTitle("Alerta de Bateria");
        //cria um vbox para organizar os nodes certinho
        VBox rootVBox = new VBox(15);
        rootVBox.setPadding(new Insets(20));
        rootVBox.setAlignment(Pos.CENTER);
        // Label com a mensagem de falta de bateria no robô
        Label mensagemLabel = new Label("O robo não possui energia suficiente");
        mensagemLabel.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold; -fx-text-fill: red;");
        //botão para fechar o stage
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> alertaStage.close());
        // Adiciona os componentes ao VBox
        rootVBox.getChildren().addAll(mensagemLabel, okButton);
        // crie, coloca o vbox e define as dimensões do scene
        Scene cena = new Scene(rootVBox, 350, 150);
        alertaStage.setScene(cena);
        //não deixa o stage ser colocado como full e mostra o stage
        alertaStage.setResizable(false);
        alertaStage.showAndWait();
    }

    public static void mensagemSemBaterias(){
        //cria um novo stage
        Stage alertaSemBateria = new Stage();
        alertaSemBateria.setTitle("Alerta de falta de Bateria");
        //cria um vbox para organizar os nodes certinho
        VBox rootVBox = new VBox(15);
        rootVBox.setPadding(new Insets(20));
        rootVBox.setAlignment(Pos.CENTER);
        // Labels com a mensagem de falta de bateria da cidade
        Label mensagemLabel = new Label("A cidade não possui baterias suficiente");
        mensagemLabel.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold; -fx-text-fill: red;");
        Label mensagemLabel2 = new Label("Produza na Fábrica de Baterias");
        mensagemLabel2.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold; -fx-text-fill: red;");
        //botão para fechar o stage
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> alertaSemBateria.close());
        // Adiciona os componentes ao VBox
        rootVBox.getChildren().addAll(mensagemLabel,mensagemLabel2, okButton);
        // crie, coloca o vbox e define as dimensões do scene
        Scene cena = new Scene(rootVBox, 350, 150);
        alertaSemBateria.setScene(cena);
        //não deixa o stage ser colocado como full e mostra o stage
        alertaSemBateria.setResizable(false);
        alertaSemBateria.showAndWait();
    }
}
