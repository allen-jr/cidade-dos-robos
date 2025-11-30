package Model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class BancoDeDados {
    private static Cidade cidade = new Cidade();

    public static Cidade getCidade() {
        return cidade;
    }
    /** Salva todo o estado da cidade no arquivo JSON */
    public static void salvar() {
        cidade.getPredios().add(cidade.getPredioBateria());
        cidade.getPredios().add(cidade.getPredioFabrica());
        cidade.getRobos().add(cidade.getRoboConstrutor());
        cidade.getRobos().add(cidade.getRoboEngenheiro());
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("cidade.json")) {
            gson.toJson(cidade, writer);
            System.out.println("Cidade salva com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Carrega o estado salvo da cidade */
    public static void carregar(){
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("cidade.json")) {
            Type tipo = new TypeToken<Cidade>() {}.getType();
            cidade = gson.fromJson(reader, tipo);
            BancoDeDados.reconstruirDados();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nenhum arquivo salvo encontrado, criando nova cidade...");
            cidade = new Cidade();
        }
    }

    public static void reconstruirDados(){
        cidade.getPredioCentral().setImage(new ImageView(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Predios/predioPrincipal.png"))));
        cidade.getPredioBateria().setImage(new ImageView(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Predios/FabricaDeBateria.png"))));
        cidade.getPredioFabrica().setImage(new ImageView(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Predios/FabricaDeRobos.png"))));
        cidade.getRoboExplorador().setSprite1(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Robos/robo-verde-1.png")));
        cidade.getRoboExplorador().setSprite2(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Robos/robo-verde-2.png")));
        cidade.getRoboEngenheiro().setSprite1(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Robos/robo-azul-1.png")));
        cidade.getRoboEngenheiro().setSprite2(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Robos/robo-azul-2.png")));
        cidade.getRoboConstrutor().setSprite1(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Robos/robo-construtor-1.png")));
        cidade.getRoboConstrutor().setSprite2(new Image(BancoDeDados.class.getResourceAsStream("/sprites/Robos/robo-construtor-2.png")));

        cidade.getRoboExplorador().getBateria().recuperarImagem();
        cidade.getRoboConstrutor().getBateria().recuperarImagem();
        cidade.getRoboEngenheiro().getBateria().recuperarImagem();

        cidade.getRoboExplorador().criarStackRobo();
        cidade.getRoboConstrutor().criarStackRobo();
        cidade.getRoboEngenheiro().criarStackRobo();
        cidade.getPredioCentral().contruirStackPredio();
        cidade.getPredioBateria().contruirStackPredio();
        cidade.getPredioFabrica().contruirStackPredio();
    }
}
