package pl.edu.agh.fis.anistratenko_team_project;

import javafx.collections.ObservableList;

import javax.xml.soap.Node;

public interface Simulation {
    /**
     * @return list of elements to be drawn
     */
    public ObservableList<javafx.scene.Node> getNodes();

    /**
     * @return name of the simulation
     */
    public String toString();
}
