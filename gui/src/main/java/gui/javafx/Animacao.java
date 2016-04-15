/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.javafx;

import java.net.URL;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContentBuilder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayerBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animacao extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exemplo com Som e Animação");
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
        text.setText("De que distância\n"+
        			"chega essa chuva\n"+
        			"de asas, tangida\n" +
        			"pela ventania?");
        
        final TranslateTransition animacao = TranslateTransitionBuilder.create().duration(new Duration(24000)).node(text).fromY(0).toY(-330).interpolator(Interpolator.EASE_OUT).build();

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
        
        MediaPlayerBuilder.create().autoPlay(true).media(new Media("http://video.fws.gov/sounds/35indigobunting.mp3")).build();
        
        root.getChildren().add(view);
        root.getChildren().add(text);
        root.getChildren().add(btn);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        animacao.play();

    }
}
