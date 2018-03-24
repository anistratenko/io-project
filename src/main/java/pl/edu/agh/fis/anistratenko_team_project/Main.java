package pl.edu.agh.fis.anistratenko_team_project;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Button btn = new Button();
        /*
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);


        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        */
        //prepare simulation
        SimulationPanel simulationWindow = new SimulationPanel();
        PendulumView pendulum = new PendulumView(0.25,1,1.f,0.5);
        simulationWindow.applySimulation(pendulum);

        //prepare window
        StackPane mainWindow = new StackPane();

        //add element of simulation to window
        mainWindow.getChildren().add(simulationWindow);

        Scene scene = new Scene(mainWindow);

        primaryStage.setTitle(simulationWindow.toString());
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animation = new AnimationTimer(){
            long lastUpdate = 0;
            public void handle(long now){
                if (now - lastUpdate >= 17_000_000)
                pendulum.calculateDataToDraw();
                lastUpdate=now;

            }
        };
        animation.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
