package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @brief reserve space for simulation, holds all object to draw
 */
public class SimulationPanel extends Pane{

    private Pane sceneToDraw;
    private String simulationName;

    /**
     * @brief Initialize empty panel
     * @param sizeX
     * @param sizeY
     */
    private void firstInit(int sizeX, int sizeY){
        sceneToDraw = new Pane();
        sceneToDraw.setPrefSize(sizeX,sizeY);
    }

    public SimulationPanel(){
        firstInit(500,500);
    }

    public SimulationPanel(int sizeX, int sizeY){
        firstInit(sizeX, sizeY);
    }

    public boolean addToPane(Node elementInScene){
       return sceneToDraw.getChildren().add(elementInScene);
    }

    public Pane GetPane(){
        return sceneToDraw;
    }

    public boolean applySimulation(Simulation simulation)
    {

    }
    public String ToString(){
        return simulationName;
    }

}

