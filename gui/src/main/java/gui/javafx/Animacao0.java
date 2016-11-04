package gui.javafx;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animacao0 extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
        stage.setTitle("Exemplo de Transição HLine");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 375);		
		
		final Rectangle rectPath = new Rectangle (0, 0, 40, 40);
		rectPath.setArcHeight(10); 
		rectPath.setArcWidth(10); 
		rectPath.setFill(Color.ORANGE); 

		Path path = new Path(); 
		path.getElements().add(new MoveTo(20,20)); 
		path.getElements().add(new HLineTo(400));
		path.getElements().add(new VLineTo(100));
		path.getElements().add(new HLineTo(20));
		path.getElements().add(new VLineTo(20));

		PathTransition pathTransition = new PathTransition(); 
		pathTransition.setDuration(Duration.millis(4000));
		pathTransition.setPath(path); 
		pathTransition.setNode(rectPath);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(false);

		
        root.getChildren().add(rectPath);
        
        stage.setScene(scene);
        stage.show();

        pathTransition.play();
		
	}
}