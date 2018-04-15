package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void applySimulation() {
        class SimulationControllerStub extends SimulationController{
            SimulationControllerStub(){
                super(new Pane());
            }
            public int addToPaneCallCounter = 0;
            @Override
            public void addToPane(Node elementInScene) {
                addToPaneCallCounter++;
            }

        }

        class Simulation implements SimulationView{
            @Override
            public ArrayList<Node> getNodes(){
                ArrayList<Node> elements = new ArrayList<>();
                elements.add(new Circle());
                elements.add(new Rectangle());
                return elements;
            }

            /**
             * @return name of the simulation
             */
            @Override
            public String toString(){return "test";}
            @Override
            public void performSimulationStep(){}
        }
        SimulationControllerStub simulation = new SimulationControllerStub();
        simulation.applySimulation(new Simulation());


        assertEquals("add to Pane calls", 2,simulation.addToPaneCallCounter);



    }

    @Test
    public void performSimulationStep() {

        class Simulation implements SimulationView{
            @Override
            public ArrayList<Node> getNodes(){
                return new ArrayList<>();
            }
            @Override
            public String toString(){return "test";}
            public int performSimulationStepCallCounter = 0;
            @Override
            public void performSimulationStep(){
                performSimulationStepCallCounter++;
            }
        }

        SimulationController controller = new SimulationController(new Pane());
        Simulation sim = new Simulation();
        controller.applySimulation(sim);
        controller.performSimulationStep();

        assertEquals("simulation Step calls", 1,sim.performSimulationStepCallCounter);
    }


    @Test
    public void to_String(){
        SimulationController simulation = new SimulationController(new Pane());
        simulation.applySimulation(new SimulationView() {
            @Override
            public ArrayList<Node> getNodes() {
                return new ArrayList<Node>();
            }

            @Override
            public void performSimulationStep() {}

            @Override
            public String toString() {
                return "test";
            }
        });
        assertTrue("name is correct", simulation.toString().equals("test"));
    }
}