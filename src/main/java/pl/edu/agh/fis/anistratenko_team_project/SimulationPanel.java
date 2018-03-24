package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @brief reserve space for simulation, holds all object to draw
 */
public class SimulationPanel extends Pane{
    private SimulationView simulationView;
    private String simulationName;

    /**
     * @brief Initialize empty panel
     * @param sizeX - width of panel
     * @param sizeY - height of panel
     */
    private void firstInit(int sizeX, int sizeY){
        setPrefSize(sizeX,sizeY);
    }

    /**
     * @brief default constructor, sets width and height to 500
     */

    public SimulationPanel(){
        firstInit(500,500);
    }

    /**Pendulum
     * @brief constructor where you can set size
     * @param sizeX - width of panel
     * @param sizeY - height of panel
     */
    public SimulationPanel(int sizeX, int sizeY){
        firstInit(sizeX, sizeY);
    }


    /**
     * @brief add element to be drawn in panel
     * @param elementInScene element to be drawn
     * @return true if Element was added successfully
     */
    public void addToPane(Node elementInScene){
       getChildren().add(elementInScene);
    }

    /**
     * @brief apply all necessary Nodes to this Pane
     * @param simulation - Simulation to be drawn
     * @return was all elements of simulation successfully added?
     */
    public void applySimulation(SimulationView simulation)
    {
        simulationView = simulation;
        for(Node i : simulation.getNodes())
            addToPane(i);

        this.simulationName = simulation.toString();
    }
    public void simulationStep()
    {
        simulationView.calculateDataToDraw();
    }

    /**
     * @return Name of the simulation
     */
    @Override
    public String toString(){
        return simulationName;
    }

}

