/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.javafx;

import gui.javafx.Formulario.Produto;
import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Formulario extends Application {

	Label lblOpcao, lblPalavraChave;
	TextField txtPalavraChave;
	ChoiceBox<String> chbOpcao;
	Button btnPesquisar;
	TableView<Produto> tbl = null;
	HBox pnlHorizontal;
	VBox pnlVertical;
	FadeTransition animacaoFade;
	ScaleTransition animacaoScale;
	
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Formulario");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 400);

        lblPalavraChave = new Label("Palavra-chave:");
        txtPalavraChave = new TextField();
        lblOpcao= new Label("Opcao:");
        chbOpcao = new ChoiceBox<String>();
        chbOpcao.getItems().addAll("Nome", "Tipo");
        chbOpcao.getSelectionModel().selectFirst();
        btnPesquisar = new Button("Pesquisar");
        
        pnlHorizontal = new HBox(5); //Espaco de 5 px
        pnlHorizontal.getChildren().addAll(lblPalavraChave, txtPalavraChave, lblOpcao, chbOpcao, btnPesquisar);
        pnlHorizontal.setPadding(new Insets(5));
        
        final ObservableList<Produto> produtos = FXCollections.observableArrayList(
        		new Produto("Vinho","Bebida",40),
        		new Produto("Sabao em Po","Limpeza",8),
        		new Produto("Detergente","Limpeza",5),
        		new Produto("Refrigerante","Bebida",3)
        );
        
        TableColumn tbcNome = TableColumnBuilder.create().text("Nome").cellValueFactory(new PropertyValueFactory("nome")).build();
        TableColumn tbcTipo = TableColumnBuilder.create().text("Tipo").cellValueFactory(new PropertyValueFactory("tipo")).build();
        TableColumn tbcValor = TableColumnBuilder.create().text("Valor").cellValueFactory(new PropertyValueFactory("valor")).build();
        
        tbl = new TableView<Produto>();
        tbl.setPrefHeight(300);
        tbl.setItems(produtos);
        tbl.getColumns().addAll(tbcNome, tbcTipo, tbcValor);
        
        //final TranslateTransition animacao = TranslateTransitionBuilder.create().duration(new Duration(24000)).node(text).fromY(0).toY(-330).interpolator(Interpolator.EASE_OUT).build();
        animacaoFade = FadeTransitionBuilder.create().duration(new Duration(1000)).node(tbl).fromValue(1).toValue(0).autoReverse(true).cycleCount(2).build();
        animacaoScale = ScaleTransitionBuilder.create().duration(new Duration(1000)).node(tbl).toX(0).toY(0).autoReverse(true).cycleCount(2).build();
        
        btnPesquisar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	btnPesquisar_onAction(event);
            }
        });
        
        pnlVertical = new VBox(5);
        pnlVertical.setPadding(new Insets(5));
        pnlVertical.getChildren().addAll(pnlHorizontal,tbl);
        
        root.getChildren().add(pnlVertical);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    protected void btnPesquisar_onAction(ActionEvent event) {
    	animacaoFade.playFromStart();
    	animacaoScale.playFromStart();
    	System.out.println(tbl.getSelectionModel().getSelectedItem());		
	}

	public static class Produto {
    	private StringProperty nome;
    	private StringProperty tipo;
    	private FloatProperty valor;
    	
    	public Produto(String nome, String tipo, float valor) {
    		this.nome = new SimpleStringProperty(nome);
    		this.tipo = new SimpleStringProperty(tipo);
    		this.valor = new SimpleFloatProperty(valor);
    	}

    	public StringProperty nomeProperty() {	return nome;	}
    	public StringProperty tipoProperty() {	return tipo;	}
    	public FloatProperty valorProperty() {	return valor;	}
    	
    	public String toString() { return "Produto: " + nome.getValue(); }
    }
}
