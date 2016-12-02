package gui.swing.jogo.asteroid.multiplayer.desenho;
import java.awt.Color;
import java.awt.Shape;
import java.io.Serializable;


public class BaseVectorShape implements Serializable {

	private Shape shape;
	private boolean alive;
	private double x,y;
	private double velX, velY;
	private double moveAngle, faceAngle;
	private Color cor;
	
	public BaseVectorShape(){
		setShape(null);
		setAlive(false);
		setX(0);
		setY(0);
		setVelX(0);
		setVelY(0);
		setFaceAngle(0);
		setMoveAngle(0);
	}
	
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getVelX() {
		return velX;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public double getMoveAngle() {
		return moveAngle;
	}
	public void setMoveAngle(double moveAngle) {
		this.moveAngle = moveAngle;
	}
	public double getFaceAngle() {
		return faceAngle;
	}
	public void setFaceAngle(double faceAngle) {
		this.faceAngle = faceAngle;
	}
	
	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}	
	
	public void incX(double i) { this.x+=i;	}
	public void incY(double i) { this.y+=i;	}
	public void incVelX(double i) { this.velX+=i;	}
	public void incVelY(double i) { this.velY+=i;	}
	public void incMoveAngle(double i) { this.moveAngle+=i;	}
	public void incFaceAngle(double i) { this.faceAngle+=i;	}
		
}
