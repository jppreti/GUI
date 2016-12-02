package gui.javafx;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class PaintBrush extends Application {

    Polyline poly = new Polyline();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Desenhando linhas com o Mouse");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 375);

        Pane tela = new Pane();

        tela.getChildren().add(poly);

        scene.setOnMouseDragged(e -> {
            //tela.getChildren().add(new Circle(e.getX(),e.getY(),4,new Color(Math.random(),Math.random(),Math.random(),1)));
            poly.getPoints().addAll(new Double[]{e.getX(), e.getY()});
        });

        scene.setOnMouseReleased(e->{
            poly = new Polyline();
            DropShadow ds = new DropShadow();
            ds.setOffsetY(3.0);
            ds.setOffsetX(3.0);
            ds.setColor(Color.BLUE);
            poly.setEffect(ds);
            poly.setStrokeWidth(Math.random()*10);
            tela.getChildren().add(poly);
        });
        root.getChildren().add(tela);

        stage.setScene(scene);
        stage.show();

    }
}

