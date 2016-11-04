package gui.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OlaMundo extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage janela) throws Exception {
		janela.setTitle("Olá Mundo!");
		Pane pane = new Pane();
		Button btnEntrar = new Button("Olá Mundo");
		btnEntrar.setOnAction(new OlaMundoHandler());
		OlaMundoMouseHandler olaMouseHnd = new OlaMundoMouseHandler();
		//btnEntrar.setOnMouseEntered(olaMouseHnd);
		btnEntrar.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("Mouse ENTROU na área do botão");
			}
		});

		//btnEntrar.setOnMouseExited(olaMouseHnd);
		btnEntrar.setOnMouseExited(e->{
			System.out.println("Mouse SAIU da área do botão");
		});
		//btnEntrar.setOnMouseClicked(olaMouseHnd);
		btnEntrar.setOnMouseClicked(e->{
			btnEntrar_onMouseClicked(e);
		});

		pane.getChildren().addAll(btnEntrar);
		Scene scene = new Scene(pane, 300,275);
		janela.setScene(scene);
		janela.show();
	}

	private void btnEntrar_onMouseClicked(MouseEvent e) {
		System.out.println("Duplo clique no botão Olá Mundo!!!");
	}
}

class OlaMundoHandler implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		System.out.println("Ola Mundo foi Pressionado!!!" + event.toString());
	}
}

class OlaMundoMouseHandler implements EventHandler<MouseEvent> {

	@Override
	public void handle(MouseEvent event) {

		if (event.getClickCount()==2) {
			System.out.println("Duplo clique no botão Olá Mundo!!!");
		}
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			System.out.println("Mouse ENTROU na área do Botão Olá Mundo");
		}
		if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			System.out.println("Mouse SAIU da área do Botão Olá Mundo");
		}
	}
}