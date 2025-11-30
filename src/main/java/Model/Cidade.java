package Model;

/**Classe da cidade utilizada no game
 */
public class Cidade {
    private RoboExplorador roboExplorador = new RoboExplorador();
    private RoboConstrutor roboConstrutor = new RoboConstrutor(10, 14);
    private RoboEngenheiro roboEngenheiro = new RoboEngenheiro(10,15);
    private PredioCentral predioCentral = new PredioCentral();
    private FabricaRobo fabricaRobo = new FabricaRobo(7,10);
    private FabricaBateria fabricaBateria = new FabricaBateria(30,7);
    private double recursos = 100;
    private int baterias = 10;

    /**Método para retornar o prédio central da cidade
     * @return retorna o prédio central da cidade
     */
    public PredioCentral getPredioCentral() {
        return this.predioCentral;
    }

    /**Método para retornar o robô explorador da cidade
     * @return retorna o robô explorador da cidade
     */
    public RoboExplorador getRoboExplorador() {
        return this.roboExplorador;
    }

    /**Método para retornar o robô construtor da cidade
     * @return retorna o robô construtor da cidade
     */
    public RoboConstrutor getRoboConstrutor() {
        return this.roboConstrutor;
    }

    /**Método para retornar o robô engenheiro da cidade
     * @return retorna o robô engenheiro da cidade
     */
    public RoboEngenheiro getRoboEngenheiro() {
        return this.roboEngenheiro;
    }

    /**Método para adicionar recurso na cidade
     * @param recursos quantidade de recurso para adicionar
     */
    public void adicionarRecursos(double recursos) {
        this.recursos += recursos;
    }

    /**Método para retornar a quantidade de recursos da cidade
     * @return retorna a quantidade de recursos da cidade
     */
    public double getRecursos() {
        return recursos;
    }

    /**Método para retornar a quantidade de baterias da cidade
     * @return retorna a quantidade de baterias da cidade
     */
    public int getBaterias() {
        return baterias;
    }

    /**Método para retornar o prédio de fábrica de robôs da cidade
     * @return retorna a fábrica de robôs da cidade
     */
    public FabricaRobo getPredioFabrica() {
        return  this.fabricaRobo;
    }

    /**Método para retornar o prédio que produz baterias dos robôs da cidade
     * @return retorna a fábrica de baterias da cidade
     */
    public FabricaBateria getPredioBateria() {
        return this.fabricaBateria;
    }

    /**Método para adicionar as baterias produzidas na cidade
     * @param baterias quantidade de baterias para adicionar na cidade
     */
    public void adicionarBaterias(int baterias) {
        this.baterias += baterias;
    }

    /**Método para diminuir a quantidade de recursos na cidade
     * @param recursos quantidade para diminuir os recursos
     */
    public void diminuirRecursos(double recursos) {
        this.recursos -= recursos;
    }

    /**Método para diminuir a quantidade de baterias na cidade
     * @param baterias quantidade de baterias a ser removida
     */
    public void diminuirBaterias(int baterias) {
        if (this.baterias >= 1){
            this.baterias -= baterias;
        }
    }
}
