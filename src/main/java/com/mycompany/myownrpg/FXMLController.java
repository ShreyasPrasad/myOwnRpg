package com.mycompany.myownrpg;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleFileRequest(ActionEvent event) {
     FileChooser chooser = new FileChooser();
     chooser.setTitle("Select Configuration File");
     File file = chooser.showOpenDialog(new Stage());
     if (file!=null){
         //verify if file is correct 
         System.out.println(""+file.getName().substring(0,6));
         if (file.getName().substring(0,6).equals("config")){
              initGame(file);
              switchScreens(event);
         }
        
     }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void switchScreens(ActionEvent event) {
        
         try{
             Parent root=FXMLLoader.load(getClass().getResource("/fxml/template.fxml"));
             Scene newScene=new Scene(root);
             
             Stage currentStage=(Stage)((Node)event.getSource()).getScene().getWindow();
             currentStage.hide();
             currentStage.setScene(newScene);
             currentStage.show();
         }
         catch(Exception e){
             e.printStackTrace();
         }
    }

    private void initGame(File file) {
          //call config
        configLoader cfg= new configLoader(file);
    }
}
