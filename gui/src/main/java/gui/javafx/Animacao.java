/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

public class Animacao extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exemplo com Som e Animacao");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 375);
        
        ImageView view = new ImageView();
        Image img = new Image("http://farm1.static.flickr.com/39/121693644_75491b23b0.jpg");
        view.setImage(img);
               
        Text text = new Text();
        text.setEffect(new DropShadow());
        text.setFont(Font.font("Serif", FontWeight.BOLD,scene.getHeight()/12.5));
        text.setFill(Color.GOLDENROD);
        text.setX(10);
        text.setY(scene.getHeight());
        text.setText("De que distancia\n"+
        			"chega essa chuva\n"+
        			"de asas, tangida\n" +
        			"pela ventania?");
        
        final TranslateTransition animacao = new TranslateTransition(Duration.millis(24000),text);
        animacao.setFromY(0);
        animacao.setToY(-330);
        animacao.setInterpolator(Interpolator.EASE_OUT);

        

        Button btn = new Button();

        btn.setTranslateX(0);
        btn.setTranslateY(0);
        btn.setText("Rodar Novamente");
        btn.setVisible(true);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	animacao.playFromStart();
            }
        });
        
        //AudioClip audio = new AudioClip("http://www.fws.gov/video/sounds/40bluemartins.mp3");
        
        root.getChildren().add(view);
        root.getChildren().add(text);
        root.getChildren().add(btn);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        //audio.play();
        animacao.play();
        
    }
}
