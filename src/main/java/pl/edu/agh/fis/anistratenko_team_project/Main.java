package pl.edu.agh.fis.anistratenko_team_project;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //prepare simulation
        SimulationPane simulationPane = new SimulationPane();
        PendulumView pendulumView = new PendulumView(0.25, 1, 1.f, 0.5);
        pendulumView.setHook(200, 10);
        simulationPane.applySimulation(pendulumView);

        //prepare window
        StackPane mainWindow = new StackPane();

        //add element of simulation to window
        mainWindow.getChildren().add(simulationPane);

        Scene scene = new Scene(mainWindow);

        primaryStage.setTitle(simulationPane.toString());
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animation = new AnimationTimer() {
            long lastUpdate = 0;

            public void handle(long now) {
                if (now - lastUpdate >= 16_666_666) {
                    simulationPane.simulationStep();
                    lastUpdate = now;
                }
            }
        };
        animation.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
