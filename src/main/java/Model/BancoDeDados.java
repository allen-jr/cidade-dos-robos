package Model;

public class BancoDeDados {
    private static Cidade cidade = new Cidade();

    public static Cidade getCidade() {
        return cidade;
    }

    public static void setCidade(Cidade city) {
        cidade = city;
    }
}
