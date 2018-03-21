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

public class Demo extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Double");
        //primaryStage.setFullScreen(true);

        Circle circ1 = new Circle();
        Circle circ2 = new Circle();
        Line l1 = new Line();
        Line l2 = new Line();
        l1.setStartX(250f);
		l1.setStartY(250f);
		l1.setEndX(300f);
		l1.setEndY(300f);
        circ1.setCenterX(300.f);
        circ1.setCenterY(270.f);
        circ1.setRadius(10.f);
        circ2.setCenterX(400.f);
        circ2.setCenterY(270.f);
        circ2.setRadius(5.f);
 	

        
        StackPane root = new StackPane();
        root.getChildren().add(new Pane(circ1));
        root.getChildren().add(new Pane(circ2));
        root.getChildren().add(new Pane(l1));
		root.getChildren().add(new Pane(l2));

		//mass dependend from radius, l1, l2 will be overwritten in setXY
        Pendulum P1 = new Pendulum(0.25, circ1.getRadius()/10., 0.25, circ2.getRadius()/10);
        //locations based on circles
        P1.setXY( (circ1.getCenterX()-250.)/250., (circ1.getCenterY()-250.)/250.,(circ2.getCenterX()-250.)/250., (circ2.getCenterY()-250.)/250.);
        new AnimationTimer()
        {
        	long lastUpdate = 0;
            public void handle(long now)
            {
				if (now - lastUpdate >= 17_000_000) 
				{
                    P1.simulate(1./60.);
            		circ1.setCenterX(250 + P1.x1*250);
            		circ1.setCenterY(250 - P1.y1*250);
            		circ2.setCenterX(250 + P1.x2*250);
            		circ2.setCenterY(250 - P1.y2*250);
            		l1.setEndX(250 + P1.x1*250);
            		l1.setEndY(250 - P1.y1*250);
            		l2.setStartX(250 + P1.x1*250);
            		l2.setStartY(250 - P1.y1*250);
            		l2.setEndX(250 + P1.x2*250);
            		l2.setEndY(250 - P1.y2*250);
                    lastUpdate = now ;
                }
            }
        }.start();

        // circ1.relocate(50f, 50f);
        // root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}