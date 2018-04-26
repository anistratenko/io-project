package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.TreeMap;


public class GravityView implements SimulationView{
    public static final double FRAMERATE = 1 / 60.;
    private ArrayList<Node> elements = new ArrayList<>();
    private Node body;
    private Gravity gravity;
    int k = 0;
    private static int[] offset = {250, 250};

    public GravityView(int numOfBodies){
        gravity = new Gravity(numOfBodies);
        for (int i = 0; i < numOfBodies; i++){
            elements.add(new Circle((double)gravity.getBody(i).getX(),
                                    (double)gravity.getBody(i).getY(),
                                    gravity.getBody(i).getR()));
        }
    }


    @Override
    public ArrayList<Node> getNodes() {
        return elements;
    }

    @Override
    public void setParams(TreeMap<String, Double> TM) {
        gravity.setParams(10);
    }

    @Override
    public void performSimulationStep() {
        gravity.simulate(FRAMERATE);
        elements.clear();
        for (int i = 0; i < gravity.getNumOfBodies(); i++){
            elements.add(new Circle(gravity.getBody(i).getX() + offset[0],offset[1] -  gravity.getBody(i).getY(), gravity.getBody(i).getR()));
        }

//        for (int i = 0; i < elements.size(); i++){
//            ((Circle)elements.get(i)).setCenterX(gravity.getBody(i).getX());
//            ((Circle)elements.get(i)).setCenterY(gravity.getBody(i).getY());
//            ((Circle)elements.get(i)).setRadius(gravity.getBody(i).getR());
//        }

    }

    public static void setOffsetWidth(int x) {
        offset[0] = x;
    }
    public static void setOffsetHeight(int y) {
        offset[1] = y;
    }

}
