package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SimulationControllerTest {

    @Test
    public void addToPane() {
        Node element = new Circle();
        SimulationController simulation = new SimulationController(new Pane());
        simulation.addToPane(element);
        List<Node> elements = simulation.getSimulationPane().getChildren();
        
        assertEquals("size of elements is 1",1, elements.size());
        assertTrue("Elements are the same", elements.get(0).equals(element));
    }

    @Test @Ignore
    public void applySimulation() {
        assertEquals(true, true);
    }

    @Test @Ignore
    public void performSimulationStep() {
        assertEquals(true, true);
    }

    @Test @Ignore
    public void getSimulationPane() {
        assertEquals(true, true);
    }

    @Test @Ignore
    public void to_String() {
        assertEquals(true, true);
    }
}