package gui.javafx.game.asteroid;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by joaopaulodelgadopreti on 04/11/16.
 */
public class Main2 extends Application {

    Ship ship = new Ship();
    Ship ship2 = new Ship();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Asteroid");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 375);

        Pane tela = new Pane();

        tela.getChildren().add(ship.getShape());
        tela.getChildren().add(ship2.getShape());

        root.getChildren().add(tela);

        ship.getShape().setTranslateX(20);
        ship.getShape().setTranslateY(20);

        ship2.getShape().setTranslateX(100);
        ship2.getShape().setTranslateY(100);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50);
                        update(scene);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();

        scene.setOnKeyPressed(e->{
            if (e.getCode()== KeyCode.UP) ship.setFaceAngle(0);
            if (e.getCode()== KeyCode.LEFT) ship.setFaceAngle(270);
            if (e.getCode()== KeyCode.RIGHT) ship.setFaceAngle(90);
            if (e.getCode()== KeyCode.DOWN) ship.setFaceAngle(180);

            ship.getShape().setRotate(ship.getFaceAngle());

            if (ship.getShape().getBoundsInParent().intersects(ship2.getShape().getBoundsInParent())) {
                if (ship.getShape().getBoundsInParent().intersects(ship2.getShape().getBoundsInParent())) {
                    ship2.setAlive(false);
                    ship2.getShape().setTranslateZ(-1); //objetos com 0 (default) colidem
                    ship2.getShape().setFill(Color.RED);
                    FadeTransition ft = new FadeTransition(Duration.millis(2000));
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setCycleCount(1);
                    ft.setAutoReverse(false);
                    ft.setNode(ship2.getShape());
                    ft.play();
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public void update(Scene scene) {
        if (ship.getShape().getTranslateX()<=-10) ship.getShape().setTranslateX(scene.getWidth());
        if (ship.getShape().getTranslateX()>=scene.getWidth()+10) ship.getShape().setTranslateX(0);
        if (ship.getShape().getTranslateY()<=-10) ship.getShape().setTranslateY(scene.getHeight());
        if (ship.getShape().getTranslateY()>=scene.getHeight()+10) ship.getShape().setTranslateY(0);

        ship.getShape().setRotate(ship.getFaceAngle());

        if (ship.getFaceAngle()==0)  ship.getShape().setTranslateY(ship.getShape().getTranslateY()-2);
        if (ship.getFaceAngle()==90) ship.getShape().setTranslateX(ship.getShape().getTranslateX()+2);
        if (ship.getFaceAngle()==180) ship.getShape().setTranslateY(ship.getShape().getTranslateY()+2);
        if (ship.getFaceAngle()==270) ship.getShape().setTranslateX(ship.getShape().getTranslateX()-2);

    }
}
