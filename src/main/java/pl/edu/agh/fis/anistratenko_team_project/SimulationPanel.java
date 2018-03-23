package pl.edu.agh.fis.anistratenko_team_project;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class SimulationPanel {

    private Scene sceneToDraw;


    private void firstInit(StackPane parentPanel, int sizeX, int sizeY){
        sceneToDraw = new Scene(parentPanel, sizeX,sizeY);
    }

    public SimulationPanel(StackPane parentPanel){
        firstInit(parentPanel,500,500);
    }

    public SimulationPanel(StackPane parentPanel, int sizeX, int sizeY){
        firstInit(parentPanel,sizeX,sizeY);
    }
    
    public Scene GetScene(){
        return sceneToDraw;
    }


}
