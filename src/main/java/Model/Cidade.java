package Model;

import Controller.RoboController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Cidade {
    private RoboExplorador roboExplorador;
    private PredioCentral predioCentral;
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

    public void setBaterias(int baterias) {
        this.baterias += baterias;
    }
}
