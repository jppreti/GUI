/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.javafx;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TesteFXML extends Application {

    public static void main(String[] args) {
        Application.launch(TesteFXML.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        AnchorPane exemplo = null;
        
        try{
            exemplo = FXMLLoader.load(TesteFXML.class.getResource("ExemploFXML.fxml"));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        primaryStage.setTitle("Test FXML");
        primaryStage.setWidth(320);
        primaryStage.setHeight(200);
        
        Scene scene = new Scene(exemplo);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
