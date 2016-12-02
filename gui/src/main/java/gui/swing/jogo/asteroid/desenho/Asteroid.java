package gui.swing.jogo.asteroid.desenho;
import java.awt.Polygon;
import java.awt.Rectangle;


public class Asteroid extends BaseVectorShape{
	
	private int[] astX = {-20,-13, 0,20,22, 20, 12,  2,-10,-22,-16};
	private int[] astY = { 20, 23,17,20,16,-20,-22,-14,-17,-20, -5};
	
	protected double rotationVelocity;
	public double getRotationVelocity() { return rotationVelocity; }
	public void setRotationVelocity(double rotationVelocity) { this.rotationVelocity = rotationVelocity; }

	public Asteroid(){
		setShape(new Polygon(astX,astY,astX.length));
		setAlive(true);
		setRotationVelocity(0);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)getX()-20,(int)getY()-20,40,40);
	}
}
