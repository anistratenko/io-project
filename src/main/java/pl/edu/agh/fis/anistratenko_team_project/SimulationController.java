package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.TreeMap;
/**
 * @brief reserve space for simulation, holds all object to draw
 */
public class SimulationController {
    private SimulationView simulationView;
    private Pane simulationPane;
    private String simulationName;
    private Boolean simulationRunning = true;

    /**
     * @brief default constructor, sets width and height to 500
     */

    public SimulationController(Pane simulationPane) {
        this(500, 500, simulationPane);
    }

    /**
     * Pendulum
     *
     * @param sizeX - width of panel
     * @param sizeY - height of panel
     * @brief constructor where you can set size
     */
    public SimulationController(int sizeX, int sizeY, Pane simulationPane) {
        this.simulationPane = simulationPane;
        this.simulationPane.setPrefSize(sizeX, sizeY);
    }


    /**
     * @param elementInScene element to be drawn
     * @return true if Element was added successfully
     * @brief add element to be drawn in panel
     */
    public void addToPane(Node elementInScene) {
        simulationPane.getChildren().add(elementInScene);
    }

    /**
     * @param simulation - SimulationController to be drawn
     * @return was all elements of simulation successfully added?
     * @brief apply all necessary Nodes to this Pane
     */
    public void applySimulation(SimulationView simulation) {
        simulationView = simulation;
        for (Node i : simulation.getNodes())
            addToPane(i);

        this.simulationName = simulation.toString();
    }

    public void performSimulationStep() {
        if (simulationRunning) simulationView.performSimulationStep();
        simulationPane.getChildren().clear();
        for (Node i : simulationView.getNodes())
            addToPane(i);
    }

    public Pane getSimulationPane() {
        return simulationPane;
    }

    public void pauseSimulation()
    {
        simulationRunning = false;
    }

    public void startSimulation()
    {
        simulationRunning = true;
    }

    public void setSimulationViewParameters(TreeMap<String, Double> TM)
    {
        simulationView.setParams(TM);
    }

    /**
     * @return Name of the simulation
     */
    @Override
    public String toString() {
        return simulationName;
    }

}