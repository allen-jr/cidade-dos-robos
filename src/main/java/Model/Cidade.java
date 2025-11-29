package Model;

public class Cidade {
    private RoboExplorador roboExplorador = new RoboExplorador(22,16);
    private RoboConstrutor roboConstrutor = new RoboConstrutor(10, 14);
    private RoboEngenheiro roboEngenheiro = new RoboEngenheiro(10,15);
    private PredioCentral predioCentral = new PredioCentral(25,16);
    private PredioFabrica predioFabrica = new PredioFabrica(7,10);
    private PredioBateria predioBateria = new PredioBateria(30,7);
    private double recursos = 0.0;
    private int baterias = 0;

    public void setPredioCentral(PredioCentral predioCentral) {
        this.predioCentral = predioCentral;
    }

    public void setRoboExplorador(RoboExplorador robo) {
        this.roboExplorador = robo;
    }

    public PredioCentral getPredioCentral() {
        return this.predioCentral;
    }

    public RoboExplorador getRoboExplorador() {
        return this.roboExplorador;
    }

    public RoboConstrutor getRoboConstrutor() {
        return this.roboConstrutor;
    }

    public RoboEngenheiro getRoboEngenheiro() {
        return this.roboEngenheiro;
    }

    public void setRecursos(double recursos) {
        this.recursos += recursos;
    }

    public double getRecursos() {
        return recursos;
    }

    public int getBaterias() {
        return baterias;
    }

    public PredioFabrica getPredioFabrica() {
        return  this.predioFabrica;
    }

    public PredioBateria getPredioBateria() {
        return this.predioBateria;
    }

    public void setBaterias(int baterias) {
        this.baterias += baterias;
    }
}
