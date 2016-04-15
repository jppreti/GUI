/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExemploMain extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage janela) {
        janela.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        //scene.getStylesheets().add("/diretorio/arquivo.css");

        final Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Hello World");
        btn.setStyle("-fx-effect: dropshadow(one-pass-box, black, 8, 0.0, 2, 0);" +
        			 "-fx-font: bold italic 20pt \"Arial\";" +
        		     "-fx-background-color: yellow");
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent arg0) {
				System.out.println("Botão foi Pressionado!");
			}
        });
        
        btn.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent arg0) {
				btn.setText("Mouse na Área!");
			}
        });

        btn.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent arg0) {
				btn.setText("Mouse por aí!");
			}
        });
        
        root.getChildren().add(btn);
        janela.setScene(scene);
        janela.show();
    }
}
