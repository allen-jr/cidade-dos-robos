package model;
import java.util.ArrayList;

public class Cidade {
    private ArrayList<Robo> robos = new ArrayList<>();
    private PredioCentral predioCentral;

    public void setPredioCentral(PredioCentral predioCentral) {
        this.predioCentral = predioCentral;
    }

    public void setRobos(ArrayList<Robo> robos) {
        this.robos = robos;
    }

    public PredioCentral getPredioCentral() {
        return this.predioCentral;
    }

    public ArrayList<Robo> getRobos() {
        return this.robos;
    }
}
