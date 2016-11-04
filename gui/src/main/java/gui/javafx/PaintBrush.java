package gui.javafx;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import javafx.stage.Stage;

public class PaintBrush extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Desenhando linhas com o Mouse");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 375);

        Pane tela = new Pane();

        scene.setOnMouseDragged(e -> {
                tela.getChildren().add(new Circle(e.getX(), e.getY(), 2));
        });

        root.getChildren().add(tela);

        stage.setScene(scene);
        stage.show();

    }
}

