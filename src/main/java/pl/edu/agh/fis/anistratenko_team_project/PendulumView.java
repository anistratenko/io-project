package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class PendulumView implements SimulationView {

    ArrayList<Node> elements = new ArrayList<>();
    Circle c1, c2;
    Line l1, l2;
    Pendulum pendulum;
    int[] hook = {250, 250};

    /**
     * Constructor for single pendulum
     *
     * @param length1 - length of the arm
     * @param mass1   - mass of the pendulum
     */

    public PendulumView(double length1, double mass1) {
        pendulum = new Pendulum(length1, mass1);
        c1 = new Circle(300.f, 270.f, 10.f);
        c2 = c1;
        elements.add(c1);
        l1 = new Line(250, 250, c1.getCenterX(), c1.getCenterY());
        l2 = l1;
        elements.add(l1);

    }

    /**
     * Double pendulum
     *
     * @param length1 - length of first pendulum
     * @param mass1   - mass of first pendulum
     * @param length2 - length of second pendulum ( which is attached to the previous one )
     * @param mass2   - mass of second pendulum
     */
    public PendulumView(double length1, double mass1, double length2, double mass2) {
        pendulum = new Pendulum(length1, mass1, length2, mass2);
        c1 = new Circle(300.f, 270.f, 10.f);
        c2 = new Circle(400.f, 270.f, 5.f);
        elements.add(c1);
        elements.add(c2);
        pendulum.setXY((c1.getCenterX() - 250.) / 250., (c1.getCenterY() - 250.) / 250., (c2.getCenterX() - 250.) / 250., (c2.getCenterY() - 250.) / 250.);
        l1 = new Line(hook[0], hook[1], c1.getCenterX(), c1.getCenterY());
        l2 = new Line(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());
        elements.add(l1);
        elements.add(l2);
    }

    /**
     * Set where the first pendulum is attached
     *
     * @param x - horizontal distance from left edge of window
     * @param y - vertical distance from top of widow
     */
    public void setHook(int x, int y) {
        hook[0] = x;
        hook[1] = y;
    }

    /**
     * Run simulation and move positions of pendulums
     */
    public void calculateDataToDraw() {

        pendulum.simulate(1.f / 60.f);
        c1.setCenterX(hook[0] + pendulum.x1 * 250);
        c1.setCenterY(hook[1] - pendulum.y1 * 250);
        c2.setCenterX(hook[0] + pendulum.x2 * 250);
        c2.setCenterY(hook[1] - pendulum.y2 * 250);
        l1.setStartX(hook[0]);
        l1.setStartY(hook[1]);
        l1.setEndX(hook[0] + pendulum.x1 * 250);
        l1.setEndY(hook[1] - pendulum.y1 * 250);
        l2.setStartX(hook[0] + pendulum.x1 * 250);
        l2.setStartY(hook[1] - pendulum.y1 * 250);
        l2.setEndX(hook[0] + pendulum.x2 * 250);
        l2.setEndY(hook[1] - pendulum.y2 * 250);
    }

    /**
     * @return elements to be drawn
     */
    public ArrayList<Node> getNodes() {
        return elements;
    }

    public String toString(){return "Pendulum";}

}
