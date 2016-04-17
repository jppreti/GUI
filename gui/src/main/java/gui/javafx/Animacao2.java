package gui.javafx;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animacao2 extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
        stage.setTitle("Exemplo de Transicao");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 375);		
		
		final Rectangle rectPath = new Rectangle (0, 0, 40, 40);
		rectPath.setArcHeight(10); 
		rectPath.setArcWidth(10); 
		rectPath.setFill(Color.ORANGE); 

		Path path = new Path(); 
		path.getElements().add(new MoveTo(20,20)); 
		path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
		path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240)); 
		PathTransition pathTransition = new PathTransition(); 
		pathTransition.setDuration(Duration.millis(4000)); 
		pathTransition.setPath(path); 
		pathTransition.setNode(rectPath); 
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); 
		pathTransition.setCycleCount(Timeline.INDEFINITE); 
		pathTransition.setAutoReverse(true);
		
        root.getChildren().add(rectPath);
        
        stage.setScene(scene);
        stage.show();

        pathTransition.play();
		
	}
}