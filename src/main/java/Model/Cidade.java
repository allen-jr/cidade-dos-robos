package Model;

import java.util.ArrayList;

public class Cidade {
    private RoboExplorador roboExplorador = new RoboExplorador();
    private RoboConstrutor roboConstrutor = new RoboConstrutor(10, 14);
    private RoboEngenheiro roboEngenheiro = new RoboEngenheiro(10,15);
    private PredioCentral predioCentral = new PredioCentral();
    private PredioFabrica predioFabrica = new PredioFabrica(7,10);
    private PredioBateria predioBateria = new PredioBateria(30,7);
    private ArrayList<PredioGeral> predios = new ArrayList<>();
    private ArrayList<Robo> robos = new ArrayList<>();
    private double recursos = 0;
    private int baterias = 0;

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

    public ArrayList<PredioGeral> getPredios() {
        return this.predios;
    }

    public ArrayList<Robo> getRobos() {
        return this.robos;
    }

    public void adicionarRecursos(double recursos) {
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

    public void adicionarBaterias(int baterias) {
        this.baterias += baterias;
    }
    public void diminuirRecursos(double recursos) {
        this.recursos -= recursos;
    }
}
