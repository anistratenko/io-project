package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * @brief reserve space for simulation, holds all object to draw
 */
public class SimulationPane extends Pane {
    private SimulationView simulationView;
    private String simulationName;

    /**
     * @param sizeX - width of panel
     * @param sizeY - height of panel
     * @brief Initialize empty panel
     */
    private void firstInit(int sizeX, int sizeY) {
        setPrefSize(sizeX, sizeY);
    }

    /**
     * @brief default constructor, sets width and height to 500
     */

    public SimulationPane() {
        firstInit(500, 500);
    }

    /**
     * Pendulum
     *
     * @param sizeX - width of panel
     * @param sizeY - height of panel
     * @brief constructor where you can set size
     */
    public SimulationPane(int sizeX, int sizeY) {
        firstInit(sizeX, sizeY);
    }


    /**
     * @param elementInScene element to be drawn
     * @return true if Element was added successfully
     * @brief add element to be drawn in panel
     */
    public void addToPane(Node elementInScene) {
        getChildren().add(elementInScene);
    }

    /**
     * @param simulation - Simulation to be drawn
     * @return was all elements of simulation successfully added?
     * @brief apply all necessary Nodes to this Pane
     */
    public void applySimulation(SimulationView simulation) {
        simulationView = simulation;
        for (Node i : simulation.getNodes())
            addToPane(i);

        this.simulationName = simulation.toString();
    }

    public void simulationStep() {
        simulationView.calculateDataToDraw();
    }

    /**
     * @return Name of the simulation
     */
    @Override
    public String toString() {
        return simulationName;
    }

}

