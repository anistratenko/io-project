package pl.edu.agh.fis.anistratenko_team_project;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private SimulationController simulationController;

    public void startSimulationThread() {
        new AnimationTimer() {
            long lastUpdate = 0;

            public void handle(long now) {
                if (now - lastUpdate >= 16_666_666) {
                    simulationController.performSimulationStep();
                    lastUpdate = now;
                }
            }
        }.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui.fxml"));
        Parent root = fxmlLoader.load();
        simulationController = new SimulationController((Pane) fxmlLoader.getNamespace().get("simulationPane"));
        simulationController.applySimulation(new PendulumView(0.25, 1, 1.f, 0.5));

        primaryStage.setTitle(simulationController.toString());
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(650);
        primaryStage.show();

        startSimulationThread();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
