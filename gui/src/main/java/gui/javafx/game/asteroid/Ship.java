package gui.javafx.game.asteroid;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class Ship extends BaseVectorShape {

	private Double[] shipXY = {
			-6d, 6d,
			-3d, 7d,
			 0d, 7d,
			 3d, 7d,
			 6d, 6d,
			 0d,-7d};

	public Ship(){
		Polygon p = new Polygon();
		p.getPoints().addAll(shipXY);
		setShape(p);
		setAlive(true);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)getX()-6,(int)getY()-6,12,12);
	}
}
