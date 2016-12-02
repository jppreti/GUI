package gui.swing.jogo.asteroid.multiplayer.desenho;
import java.awt.Polygon;
import java.awt.Rectangle;


public class Ship extends BaseVectorShape {

	private int[] shipX = {-6,-3,0,3,6, 0};
	private int[] shipY = { 6, 7,7,7,6,-7};
	
	public Ship(){
		setShape(new Polygon(shipX, shipY,shipX.length));
		setAlive(true);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)getX()-6,(int)getY()-6,12,12);
	}
}
