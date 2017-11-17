package com.mycompany.myownrpg;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class myownrpgmain extends Application {
    private static Stage primaryStage; 
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        primaryStage.setTitle("MYOwnRPG");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Stage getStage(){
        return myownrpgmain.primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
