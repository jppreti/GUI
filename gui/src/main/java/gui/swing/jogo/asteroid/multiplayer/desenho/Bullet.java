package gui.swing.jogo.asteroid.multiplayer.desenho;
import java.awt.Color;
import java.awt.Rectangle;


public class Bullet extends BaseVectorShape {
	
	public Bullet(Color cor){
		setShape(new Rectangle(0,0,1,1));
		setAlive(true);
		setCor(cor);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)getX(),(int)getY(),1,1);
	}

}
