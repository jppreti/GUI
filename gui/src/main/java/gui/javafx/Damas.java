package gui.javafx;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.RadialGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Damas extends Application {

	static final int SIZE = 30; //tamanho de uma casa
	static final int SIZE2 = 15; //metade de uma casa
	static final int SIZEP = 12; //raio de uma peca
	
	Jogo jogo = new Jogo();
	
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Damas");
        Group root = new Group();
        Scene scene = new Scene(root, SIZE*8, SIZE*8);
        scene.setFill(Color.WHITE);
        
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 4; j++) {
        		Rectangle r = new Rectangle();
        		r.setX(i*2*SIZE);
        		r.setY(j*2*SIZE);
        		r.setWidth(SIZE);
        		r.setHeight(SIZE);
        		r.setFill(Color.GRAY);
        		
        		root.getChildren().add(r);
        		
        		r = new Rectangle();
        		r.setX(i*2*SIZE+SIZE);
        		r.setY(j*2*SIZE+SIZE);
        		r.setWidth(SIZE);
        		r.setHeight(SIZE);
        		r.setFill(Color.GRAY);
        		
        		root.getChildren().add(r);
        	}
        }

        for (Peca p : jogo.pecas)
        	root.getChildren().add(p);        
        
        stage.setScene(scene);
        stage.show();
    }

}

class Peca extends Circle {

	int x,y,i,j;
	
	int jogador;
	
	Jogo jogo;
	
	boolean dama = false;
	
	public Peca(Jogo jogo, int jogador, int i, int j) {
		super();
		
		this.jogo = jogo;
		this.jogador = jogador;
		this.i = i;
		this.j = j;
		
		updateXY();
		
		jogo.tabuleiro.put(IJToIndex(i,j), this);
		
		setTranslateX(x);
		setTranslateY(y);
		
		this.setRadius(Damas.SIZEP);
		
		if (jogador==1) setFill(Color.RED); else setFill(Color.BLUE);
		
		List<Stop> stops = new ArrayList<Stop>();
		stops.add(new Stop(0.0, jogador==1?Color.color(1,0.8,0.8):Color.LIGHTBLUE));
		stops.add(new Stop(0.4, jogador==1?Color.RED:Color.BLUE));
		stops.add(new Stop(0.8, jogador==1?Color.DARKRED:Color.DARKBLUE));
		
		RadialGradient gradiente = new RadialGradient(135,0.3,0.5,0.5,0.5,true,CycleMethod.NO_CYCLE, stops);
		this.setFill(gradiente);		
				
		DropShadow shadow = new DropShadow();
		shadow.setOffsetX(1);
		shadow.setOffsetY(1);
		shadow.setRadius(3);
		shadow.setSpread(0.1);
		
		this.setEffect(shadow);		
		
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {
			 public void handle(MouseEvent event) {
	                onMouseDragged(event);
	            }
		});
		
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				onMouseReleased(event);
			}
		});
	}	
	
	/**
	 * converte as coordenadas (i,j) num indice de um array unidimensional.
	 */
	private Integer IJToIndex(int i, int j) {
		return j * 8 + 1;
	}

	/**
	 * Atualiza X e Y em relacao a I e J
	 */
	public void updateXY() {
		x = toXY(i);
		y = toXY(j);
	}

	/**
	 * Atualiza I e J em relacao a X e Y
	 */
	public void updateIJ() {
		jogo.tabuleiro.remove(IJToIndex(i, j));
		i = toIJ(x);
		j = toIJ(y);
		jogo.tabuleiro.put(IJToIndex(i, j), this);
		
		if ((jogador == 1 && j==7) || (jogador==2 && j==0)) {
			dama = true;
		}
	}
	
	public void comer(){
		jogo.tabuleiro.remove(IJToIndex(i, j));
		this.setVisible(false);
	}
	
	public int toXY(int valor) {
		return valor * Damas.SIZE + Damas.SIZE2;
	}
	
	public int toIJ(int valor) {
		return Math.round((valor - Damas.SIZE2) / Damas.SIZE);
	}
	
	/**
	 * verifica se uma peca pode comer alguma outra em volta, isso serve para que se possa comer 2 pecas ou mais numa unica jogada. 
	 */
	public boolean podeComer() {
		if(i+2 <= 7 && j+2 == 0 && j+2 == 0 && j-2 >= 0){
			if (jogo.tabuleiro.containsKey(IJToIndex(i-1, j-1))){
				Peca p = jogo.tabuleiro.get(IJToIndex(i-1, j-1));
				if (p.jogador != this.jogador) {
					if (!jogo.tabuleiro.containsKey(IJToIndex(i-2, j-2))) {
						return true;
					}
				}
			}
		}
		
		if (i+2==0) {
			if (jogo.tabuleiro.containsKey(IJToIndex(i+1, j-1))) {
				Peca p = jogo.tabuleiro.get(IJToIndex(i+1, j-1));
				if (p.jogador != this.jogador) {
					if (!jogo.tabuleiro.containsKey(IJToIndex(i+2, j-2))){
						return true;
					}
				}
			}
		}

		return false;
	}
	
	protected void onMouseDragged(MouseEvent event) {
		if (this.jogador != jogo.jogador) {
			return;
		}
		x = (int) event.getSceneX();
		y = (int) event.getSceneY();
	}

	protected void onMouseReleased(MouseEvent event) {
		x = (int) event.getSceneX();
		y = (int) event.getSceneY();
		updateIJ();
		updateXY();
	}	
}

class Jogo {
	Hashtable<Integer,Peca> tabuleiro = new Hashtable<Integer,Peca>();
	int jogador = 2;
	ArrayList<Peca> pecas = new ArrayList<Peca>();
	
	public Jogo() {
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 3; j++) {
        		Peca p = new Peca(this,1,j%2==0?i*2:i*2+1,j);
        		pecas.add(p);
        		
        		p = new Peca(this,2,7 - (j%2==0?i*2:i*2+1),j+5);
        		pecas.add(p);
        	}
        }
	}
	
	public void mudarJogador() {
		jogador = jogador==1?2:1;
	}
}