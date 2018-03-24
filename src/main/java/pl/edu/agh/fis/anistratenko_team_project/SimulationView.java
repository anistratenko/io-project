package pl.edu.agh.fis.anistratenko_team_project;

import javafx.collections.ObservableList;

import javax.xml.soap.Node;
import java.util.ArrayList;

public interface SimulationView {
    /**
     * @return list of elements to be drawn
     */
    ArrayList<javafx.scene.Node> getNodes();

    /**
     * @return name of the simulation
     */
    String toString();

    void calculateDataToDraw();
}

