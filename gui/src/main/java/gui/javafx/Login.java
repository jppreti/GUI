package gui.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage janela) throws Exception {
		janela.setTitle("Controle de Acesso!");
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		grid.setAlignment(Pos.CENTER);
		
		Text txtBemVindo = new Text("Bem Vindo!");
		txtBemVindo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(txtBemVindo, 0, 0, 2, 1);
		
		Label lblUsuario = new Label("Usu‡rio:");
		grid.add(lblUsuario, 0, 1);
		
		TextField txtUsuario = new TextField();
		txtUsuario.setPromptText("Digite seu Login aqui");
		grid.add(txtUsuario, 1, 1);
		
		Label lblSenha = new Label("Senha:");
		grid.add(lblSenha, 0, 2);
		
		PasswordField pswSenha = new PasswordField();
		pswSenha.setPromptText("Digite sua senha aqui");
		grid.add(pswSenha, 1, 2);
		
		Button btnEntrar = new Button("Entrar");
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(btnEntrar);
		grid.add(hbox, 1, 4);
		
		Text txtMensagem = new Text();
		txtMensagem.setFill(Color.FIREBRICK);
		grid.add(txtMensagem, 1, 6);
		
		Scene scene = new Scene(grid, 300,275);
		janela.setScene(scene);
		janela.show();
	}

}
