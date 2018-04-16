package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import java.util.Random;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.TreeMap;


public class GravityView implements SimulationView{
    public static final double FRAMERATE = 1 / 60.;
    private ArrayList<Node> elements = new ArrayList<>();
    private Node body;
    private Gravity gravity;
    int k = 0;


    public GravityView(int numOfBodies){
//        body = new Circle(10.0,
//                          10.0,
//                                  10);
        gravity = new Gravity(numOfBodies);
//        elements.add(body);
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
//        elements.clear();
////
//        for (int i = 0; i < gravity.getNumOfBodies(); i++){
//            elements.add(new Circle((double)gravity.getBody(i).getX(),
//                                    (double)gravity.getBody(i).getY(),
//                                            gravity.getBody(i).getR()));
////            System.out.println(((Circle)elements.get(0)).getCenterX());
//        }
//        System.out.println(elements.size());
//        System.out.println("numBod " + gravity.getNumOfBodies());
//        System.out.println(((Circle)elements.get(0)).getCenterX());
//        ((Circle)elements.get(0)).setCenterX(gravity.getBody(0).getX() + 1);
//        ((Circle)body).setCenterX(((Circle)body).getCenterX() + 1);
        for (int i = 0; i < elements.size(); i++){
//            System.out.println(gravity.getBody(i).getX());
            ((Circle)elements.get(i)).setCenterX(gravity.getBody(i).getX());
//            System.out.println(gravity.getBody(0).getX());
//            ((Circle)elements.get(i)).setCenterX((((Circle)elements.get(i)).getCenterX()+1));
//            System.out.println(((Circle) elements.get(0)).getCenterX());
            ((Circle)elements.get(i)).setCenterY(gravity.getBody(i).getY());
            ((Circle)elements.get(i)).setRadius(gravity.getBody(i).getR());
        }

    }

}
