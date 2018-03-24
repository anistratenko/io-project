package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;

import java.util.ArrayList;

public interface SimulationView {
    /**
     * @return list of elements to be drawn
     */
    ArrayList<Node> getNodes();

    /**
     * @return name of the simulation
     */
    String toString();

    void calculateDataToDraw();

    ArrayList<Object> simulationSettings();
}

