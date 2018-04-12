package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.TreeMap;

public interface SimulationView {
    /**
     * @return list of elements to be drawn
     */
    ArrayList<Node> getNodes();

    void setParams(TreeMap<String, Double> TM);

    /**
     * @return name of the simulation
     */
    String toString();

    void performSimulationStep();
}

