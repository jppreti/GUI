package gui.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class FrmLogin extends Application {

	@Override
	 public void start(Stage janela) {
        janela.setTitle("Bem Vindo ao JavaFX!");
        
        GridPane grid = new GridPane();        	
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);     grid.setVgap(10);     
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text txtBemVindo = new Text("Bem Vindo!");
        txtBemVindo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(txtBemVindo, 0, 0, 2, 1);        
        
        Label lblNome = new Label( "Usu�rio:");        
        grid.add(lblNome, 0, 1);
        
        TextField txtNome = new TextField();
        txtNome.setPromptText("Digite aqui seu Login");
        grid.add(txtNome, 1, 1);
        
        Label lblSenha = new Label("Senha:");        
        grid.add(lblSenha, 0, 2);
        
        PasswordField txtSenha = new PasswordField();        
        grid.add(txtSenha, 1, 2);
        
        Button btn = new Button( "Entrar" );        
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);        
        hbBtn.getChildren().add(btn);        
        grid.add(hbBtn, 1, 4);
        
        final Text txtMensagem = new Text();        
        grid.add(txtMensagem, 1, 6);
        btn.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	txtMensagem.setFill(Color.FIREBRICK);
            	txtMensagem.setId( "txtMensagem" );
            	txtMensagem.setText( "Botão Entrar Pressionado!" );
            }
        });
        
        Scene scene = new Scene(grid, 300, 275);
        janela.setScene(scene);        
        janela.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
