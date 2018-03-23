package pl.edu.agh.fis.anistratenko_team_project;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Pendulum implements Simulation{

    ObservableList<Node> elements;

    public Pendulum()
    {
        runPendulum();
    }

    private void runPendulum()
    {

    }

    
    public ObservableList<Node> getNodes(){
        return elements;
    }

}
