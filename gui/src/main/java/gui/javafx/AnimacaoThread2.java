package gui.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class AnimacaoThread2 extends Application implements Runnable {

	Circle circle1 = new Circle(40);
	Circle circle2 = new Circle(40);

	Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		Pane pane = new Pane();

		circle1.setCenterX(40);
		circle1.setCenterY(40);

		circle2.setCenterX(760);
		circle2.setCenterY(40);

		pane.getChildren().add(circle1);
		pane.getChildren().add(circle2);
		
		Scene scene = new Scene(pane,800,300);
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
		boolean avanca1 = true;
		boolean avanca2 = true;

		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			double colisao = (stage.getWidth()+circle2.getTranslateX())-circle1.getTranslateX();
			System.out.println(colisao);

			//Movimento da bola1
			if (circle1.getTranslateX()>=stage.getWidth()-80 || colisao<160) avanca1=false;
			if (circle1.getTranslateX()<=0) avanca1=true;

			if (avanca1)
				circle1.setTranslateX(circle1.getTranslateX()+5);
			else
				circle1.setTranslateX(circle1.getTranslateX()-5);

			//Movimento da bola 2
			if (circle2.getTranslateX()>0) avanca2=true;
			if (circle2.getTranslateX()<stage.getWidth()*-1+80 || colisao<160) avanca2=false;

			if (avanca2)
				circle2.setTranslateX(circle2.getTranslateX()-2);
			else
				circle2.setTranslateX(circle2.getTranslateX()+2);

		}
	}
}
