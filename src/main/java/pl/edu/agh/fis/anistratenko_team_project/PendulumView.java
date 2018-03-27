package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class PendulumView implements SimulationView {
    public static final double FRAMETIME = 1 / 60.;
    private ArrayList<Node> elements = new ArrayList<>();
    private Circle firstBob, secondBob;
    private Line firstCord, secondCord;
    private Pendulum pendulum;
    private static int[] offset = {250, 250};

    /**
     * Constructor for single pendulum
     *
     * @param length1 - length of the arm
     * @param mass1   - mass of the pendulum
     */

    public PendulumView(double length1, double mass1) {
        pendulum = new Pendulum(length1, mass1);
        firstBob = new Circle(300., 270., 10.);
        secondBob = firstBob;
        elements.add(firstBob);
        firstCord = new Line(250, 250, firstBob.getCenterX(), firstBob.getCenterY());
        secondCord = new Line (250, 250, 250, 250);
        elements.add(firstCord);

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
        firstBob = new Circle(300., 270., 10.);
        secondBob = new Circle(400., 270., 5.);
        elements.add(firstBob);
        elements.add(secondBob);
        pendulum.setXY((firstBob.getCenterX() - 250.) / 250.,
                (firstBob.getCenterY() - 250.) / 250.,
                (secondBob.getCenterX() - 250.) / 250.,
                (secondBob.getCenterY() - 250.) / 250.);
        firstCord = new Line(offset[0], offset[1], firstBob.getCenterX(), firstBob.getCenterY());
        secondCord = new Line(firstBob.getCenterX(), firstBob.getCenterY(), secondBob.getCenterX(), secondBob.getCenterY());
        elements.add(firstCord);
        elements.add(secondCord);
    }

    /**
     * Run simulation and move positions of pendulums
     */
    public void performSimulationStep() {
        pendulum.simulate(FRAMETIME);
        firstBob.setCenterX(offset[0] + pendulum.x1 * 250);
        firstBob.setCenterY(offset[1] - pendulum.y1 * 250);
        secondBob.setCenterX(offset[0] + pendulum.x2 * 250);
        secondBob.setCenterY(offset[1] - pendulum.y2 * 250);
        firstCord.setStartX(offset[0]);
        firstCord.setStartY(offset[1]);
        firstCord.setEndX(offset[0] + pendulum.x1 * 250);
        firstCord.setEndY(offset[1] - pendulum.y1 * 250);
        secondCord.setStartX(offset[0] + pendulum.x1 * 250);
        secondCord.setStartY(offset[1] - pendulum.y1 * 250);
        secondCord.setEndX(offset[0] + pendulum.x2 * 250);
        secondCord.setEndY(offset[1] - pendulum.y2 * 250);
    }

    public Circle getFirstBob() {
        return firstBob;
    }

    public Circle getSecondBob() {
        return secondBob;
    }

    public Line getFirstCord() {
        return firstCord;
    }

    public Line getSecondCord() {
        return secondCord;
    }

    public Pendulum getPendulum() {
        return pendulum;
    }

    /**
     * @return elements to be drawn
     */
    @Override
    public ArrayList<Node> getNodes() {
        return elements;
    }

    /**
     * Set where the first pendulum is attached
     *
     * @param x - horizontal distance from left edge of window
     * @param y - vertical distance from top of widow
     */
    public void setOffset(int x, int y) {
        offset[0] = x;
        offset[1] = y;
    }
    public static void setOffsetWidth(int x) {
        offset[0] = x;
    }
    public static void setOffsetHeight(int y) {
        offset[1] = y;
    }

    @Override
    public String toString() {
        return "Pendulum";
    }
}
