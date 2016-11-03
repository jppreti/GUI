package gui.javafx;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;

@SuppressWarnings("restriction")
public class AnimacaoThread extends Application implements Runnable {

	Circle circle = new Circle(40);
	Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		Pane pane = new Pane();
		
		circle.setCenterX(40);
		circle.setCenterY(40);

		pane.getChildren().add(circle);
		
		Scene scene = new Scene(pane,800,600);
		stage.setScene(scene);
		stage.show();
		
		Thread t = new Thread(this);
		t.setDaemon(true);
		t.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	
	}

	@Override
	public void run() {
		boolean avanca = true;
		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (circle.getTranslateX()>=stage.getWidth()-80) avanca=false;
			if (circle.getTranslateX()<=0) avanca=true;

			if (avanca)
				circle.setTranslateX(circle.getTranslateX()+5);
			else
				circle.setTranslateX(circle.getTranslateX()-5);
		}
	}
}
