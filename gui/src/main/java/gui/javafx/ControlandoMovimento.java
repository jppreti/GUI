package gui.javafx;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControlandoMovimento extends Application {

	@Override
	public void start(Stage stage) throws Exception {
        stage.setTitle("Exemplo de Controle de Objeto por meio do teclado");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 375);
        
		final Rectangle rect = new Rectangle (0, 0, 40, 40);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evt) {
				if (evt.getCode() == KeyCode.RIGHT) rect.setTranslateX(rect.getTranslateX()+5);
				if (evt.getCode() == KeyCode.LEFT) rect.setTranslateX(rect.getTranslateX()-5);
				if (evt.getCode() == KeyCode.UP) rect.setTranslateY(rect.getTranslateY()-5);
				if (evt.getCode() == KeyCode.DOWN) rect.setTranslateY(rect.getTranslateY()+5);
			}
		});
				        
        root.getChildren().add(rect);
        
        stage.setScene(scene);
        stage.show();
		
	}

    public static void main(String[] args) {
        Application.launch(args);
    }

}
