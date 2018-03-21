package pl.edu.agh.fis.anistratenko_team_project;

import pl.edu.agh.fis.anistratenko_team_project.Pendulum;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

class Point{
    Point(double x, double y)
    {
        setX(x);
        setY(y);
    }
    double x,y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

class PendulumConfig{
    public Circle circ1, circ2;
    public Line l1, l2;

    public PendulumConfig(Circle circ1, Circle circ2, Point start){
        this.circ1 = circ1;
        this.circ2 = circ2;
        setLines(start);
    }

    private void setLines(Point start){
        l1 = new Line(start.x, start.y, circ1.getCenterX(),circ1.getCenterY());
        l2 = new Line(circ1.getCenterX(),circ1.getCenterY(), circ2.getCenterX(),circ2.getCenterY());
    }
}

public class Demo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //primaryStage.setFullScreen(true);

        Circle circ1 = new Circle(300.f, 270.f, 10.f);
        Circle circ2 = new Circle(400.f, 270.f, 5.f);

        PendulumConfig config = new PendulumConfig(circ1, circ2,new Point(255,255));


		//mass dependend from radius, l1, l2 will be overwritten in setXY
        Pendulum P1 = new Pendulum(0.25, circ1.getRadius()/10., 0.25, circ2.getRadius()/10);
        //locations based on circles
        P1.setXY( (circ1.getCenterX()-250.)/250., (circ1.getCenterY()-250.)/250.,(circ2.getCenterX()-250.)/250., (circ2.getCenterY()-250.)/250.);

        runAnimation(config.circ1, config.circ2, config.l1, config.l2, P1);

        // circ1.relocate(50f, 50f);
        // root.getChildren().add(btn);

        setWindow(primaryStage, config.circ1, config.circ2, config.l1, config.l2);
    }

    private void setWindow(Stage primaryStage, Circle circ1, Circle circ2, Line l1, Line l2) {
        primaryStage.setTitle("Double");
        StackPane root = new StackPane();
        root.getChildren().add(new Pane(circ1));
        root.getChildren().add(new Pane(circ2));
        root.getChildren().add(new Pane(l1));
        root.getChildren().add(new Pane(l2));
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    private void runAnimation(Circle circ1, Circle circ2, Line l1, Line l2, Pendulum p1) {
        new AnimationTimer()
        {
        	long lastUpdate = 0;
            public void handle(long now)
            {
				if (now - lastUpdate >= 17_000_000)
				{
                    p1.simulate(1./60.);
            		circ1.setCenterX(250 + p1.x1*250);
            		circ1.setCenterY(250 - p1.y1*250);
            		circ2.setCenterX(250 + p1.x2*250);
            		circ2.setCenterY(250 - p1.y2*250);
            		l1.setEndX(250 + p1.x1*250);
            		l1.setEndY(250 - p1.y1*250);
            		l2.setStartX(250 + p1.x1*250);
            		l2.setStartY(250 - p1.y1*250);
            		l2.setEndX(250 + p1.x2*250);
            		l2.setEndY(250 - p1.y2*250);
                    lastUpdate = now ;
                }
            }
        }.start();
    }
}