package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class SimulationPanel {

    private Pane sceneToDraw;


    private void firstInit(int sizeX, int sizeY){
        sceneToDraw = new Scene(sizeX,sizeY);
    }

    public SimulationPanel(){
        firstInit(500,500);
    }

    public SimulationPanel(int sizeX, int sizeY){
        firstInit(sizeX, sizeY);
    }

    public addToPane(Node elementInScene){
        sceneToDraw.getChildren().add(elementInScene);
    }
    
    public Pane GetPane(){
        return sceneToDraw;
    }


}
