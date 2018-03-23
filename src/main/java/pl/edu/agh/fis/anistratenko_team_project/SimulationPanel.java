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
     * @param sizeX - width of panel
     * @param sizeY - height of panel
     */
    private void firstInit(int sizeX, int sizeY){
        sceneToDraw = new Pane();
        sceneToDraw.setPrefSize(sizeX,sizeY);
    }

    /**
     * @brief default constructor, sets width and height to 500
     */

    public SimulationPanel(){
        firstInit(500,500);
    }

    /**
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
    public boolean addToPane(Node elementInScene){
       return sceneToDraw.getChildren().add(elementInScene);
    }

    /**
     * @brief get hole pane to draw it
     * @return scene to be drawn
     */
    public Pane GetPane(){
        return sceneToDraw;
    }

    /**
     * @brief apply all necessary Nodes to this Pane
     * @param simulation - Simulation to be drawn
     * @return was all elements of simulation successfully added?
     */
    public boolean applySimulation(Simulation simulation)
    {
        boolean retVal = true;

        for(Node i : simulation.getNodes())
            retVal &= addToPane(i);

        this.simulationName = simulation.toString();

        return retVal;
    }

    /**
     * @return Name of the simulation
     */
    public String ToString(){
        return simulationName;
    }

}

