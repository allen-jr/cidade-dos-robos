package Model;

public class Cidade {
    private RoboExplorador roboExplorador;
    private PredioCentral predioCentral;
    private PredioFabrica predioFabrica = new PredioFabrica(7,10);
    private double recursos = 0.0;
    private int baterias;

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

    public void setBaterias(int baterias) {
        this.baterias += baterias;
    }
}
