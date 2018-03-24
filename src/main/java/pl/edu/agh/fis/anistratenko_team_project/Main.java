package pl.edu.agh.fis.anistratenko_team_project;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private StackPane stackPane;
    private SimulationPane simulationPane;

    public void startSimulationThread(){
        new AnimationTimer() {
            long lastUpdate = 0;

            public void handle(long now) {
                if (now - lastUpdate >= 17_000_000) {
                    simulationPane.performSimulationStep();
                    lastUpdate = now;
                }
            }
        }.start();
    }

    @Override
    public void start(Stage primaryStage) {
        simulationPane = new SimulationPane();
        stackPane = new StackPane();

        simulationPane.applySimulation(new PendulumView(0.25, 1, 1.f, 0.5));
        stackPane.getChildren().add(simulationPane);

        primaryStage.setTitle(simulationPane.toString());
        primaryStage.setScene(new Scene(stackPane));
        primaryStage.show();

        startSimulationThread();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
