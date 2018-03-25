package pl.edu.agh.fis.anistratenko_team_project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @brief reserve space for simulation, holds all object to draw
 */
public class SimulationController {
    private SimulationView simulationView;
    private Pane simulationPane;
    private String simulationName;

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
        simulationView.performSimulationStep();
    }

    public Pane getSimulationPane() {
        return simulationPane;
    }

    /**
     * @return Name of the simulation
     */
    @Override
    public String toString() {
        return simulationName;
    }

    }