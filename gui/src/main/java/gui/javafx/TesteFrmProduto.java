/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TesteFrmProduto extends Application {

    public static void main(String[] args) {
        Application.launch(TesteFrmProduto.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        BorderPane frmProduto = null;
        
        try{
            frmProduto = FXMLLoader.load(TesteFrmProduto.class.getResource("FrmProduto.fxml"));
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        primaryStage.setTitle("Produto");
        primaryStage.setWidth(640);
        primaryStage.setHeight(440);
        
        Scene scene = new Scene(frmProduto);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
