package pl.edu.agh.fis.anistratenko_team_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.scene.control.TextField;

public class Main extends Application {
    private static SimulationController simulationController;

    @FXML private TextField setGInputText;
    @FXML private TextField setL1InputText;
    @FXML private TextField setL2InputText;
    @FXML private TextField setFiInputText;
    @FXML private TextField setThetaInputText;

    @FXML protected void pauseButtonAction(ActionEvent event) {
        simulationController.pauseSimulation();
    }

    @FXML protected void startButtonAction(ActionEvent event) {
        simulationController.startSimulation();
    }

    @FXML protected void resetButtonAction(ActionEvent event) {
        TreeMap<String,Double> TM = new TreeMap<>();
        TM.put("L1", Double.parseDouble(setL1InputText.getText()));
        TM.put("L2", Double.parseDouble(setL2InputText.getText()));
        TM.put("Phi", Double.parseDouble(setFiInputText.getText()));
        TM.put("Theta", Double.parseDouble(setThetaInputText.getText()));
        TM.put("G", Double.parseDouble(setGInputText.getText()));
        simulationController.setSimulationViewParameters(TM);
    }

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
        Locale currLocale = new Locale("pl");
        fxmlLoader.setResources(ResourceBundle.getBundle("Locale", currLocale));

        Parent root = fxmlLoader.load();
        simulationController = new SimulationController((Pane) fxmlLoader.getNamespace().get("simulationPane"));
        simulationController.applySimulation(new PendulumView(0.25, 1, 0.25, 1));

        primaryStage.setTitle(fxmlLoader.getResources().getString("window_title"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(550);
        primaryStage.setMinWidth(650);
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> PendulumView.setOffsetWidth(newVal.intValue() / 3));
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> PendulumView.setOffsetHeight(newVal.intValue() / 2));

        primaryStage.show();

        startSimulationThread();
        System.out.println(simulationController);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
