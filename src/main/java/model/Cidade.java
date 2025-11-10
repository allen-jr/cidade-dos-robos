package model;
import java.util.ArrayList;

public class Cidade {
    private RoboExplorador roboExplorador;
    private PredioCentral predioCentral;

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
}
