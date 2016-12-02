package gui.swing.jogo.asteroid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.swing.jogo.asteroid.desenho.Asteroid;
import gui.swing.jogo.asteroid.desenho.Bullet;
import gui.swing.jogo.asteroid.desenho.Ship;

@SuppressWarnings("serial")
public class Asteroids extends JPanel implements Runnable, KeyListener {

	final int ASTEROIDS = 20;
	final int BULLETS = 10;
	
	Thread gameLoop;
	BufferedImage backBuffer;
	Graphics2D g2d;
	boolean showBounds = false;

	Asteroid asteroids[] = new Asteroid[ASTEROIDS];

	Bullet bullet[] = new Bullet[BULLETS];
	int currentBullet=0;
	
	Ship ship = new Ship();
	
	AffineTransform identity = new AffineTransform();
	
	Random random = new Random();

	public Asteroids() {
		super();
		setFocusable(true); //Isso � para o teclado funcione, sen�o o foco n�o vai para o painel
		backBuffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();
		
		//centraliza a nave
		ship.setX(320);
		ship.setY(240);
		
		for (int i=0;i<BULLETS;i++) {
			bullet[i] = new Bullet();
		}
		
		for (int i=0;i<ASTEROIDS;i++) {
			asteroids[i] = new Asteroid();
			asteroids[i].setRotationVelocity(random.nextInt(3)+1);
			asteroids[i].setX(random.nextInt(600)+20);
			asteroids[i].setY(random.nextInt(440)+20);
			asteroids[i].setMoveAngle(random.nextInt(360));
			double angle = asteroids[i].getMoveAngle();													//era double angle = asteroids[i].getMoveAngle()-90;
			asteroids[i].setVelX(calcAngleMoveX(angle));
			asteroids[i].setVelY(calcAngleMoveY(angle));
		}
		addKeyListener(this);
	}
	
	public void drawText(){
		g2d.setTransform(identity);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Nave: " + Math.round(ship.getX())+","+Math.round(ship.getY()), 5, 10);
		g2d.drawString("�ngulo de Movimento: "+Math.round(ship.getMoveAngle())+90, 5, 25);
		g2d.drawString("�ngulo da Face: "+Math.round(ship.getFaceAngle()),5,40);		
	}
	
	public void drawShip(){
		g2d.setTransform(identity);
		g2d.translate(ship.getX(), ship.getY());
		g2d.rotate(Math. toRadians(ship.getFaceAngle()));
		g2d.setColor(Color.ORANGE);
		g2d.fill(ship.getShape());
	}

	public void drawBullets(){
		for (int i=0;i<BULLETS;i++){
			if (bullet[i].isAlive()) {
				g2d.setTransform(identity);
				g2d.translate(bullet[i].getX(), bullet[i].getY());
				g2d.setColor(Color.MAGENTA);
				g2d.draw(bullet[i].getShape());
			}
		}
	}

	public void drawAsteroids(){
		for (int i=0;i<ASTEROIDS;i++){
			if (asteroids[i].isAlive()) {
				g2d.setTransform(identity);
				g2d.translate(asteroids[i].getX(), asteroids[i].getY());
				g2d.rotate(Math.toRadians(asteroids[i].getMoveAngle()));
				g2d.setColor(Color.DARK_GRAY);
				g2d.draw(asteroids[i].getShape());
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		g2d.setTransform(identity);
		
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, 640, 480);
		
		drawText();
		drawShip();
		drawBullets();
		drawAsteroids();
		
		g.drawImage(backBuffer, 0, 0, this);
	}	
	
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		while (t == gameLoop){
			try {
				gameUpdate();
				Thread.sleep(20);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			repaint();
		}
	}
	
	public void gameUpdate(){
		updateShip();
		updateBullets();
		updateAsteroids();
		checkCollisions();
	}
	
	public void updateShip() {
		ship.incX(ship.getVelX());
		
		if (ship.getX()<-10){
			ship.setX(getSize().width+10);
		} else if (ship.getX()>getSize().width+10){
			ship.setX(-10);
		}
		
		ship.incY(ship.getVelY());

		if (ship.getY()<-10){
			ship.setY(getSize().height+10);
		} else if (ship.getY()>getSize().height+10){
			ship.setY(-10);
		}
	}
	
	public void updateBullets(){
		for (int i=0;i<BULLETS;i++) {
			if (bullet[i].isAlive()){
				bullet[i].incX(bullet[i].getVelX());
				if (bullet[i].getX()<0 || bullet[i].getX()>getSize().width){
					bullet[i].setAlive(false);
				}
				
				bullet[i].incY(bullet[i].getVelY());
				if (bullet[i].getY()<0 || bullet[i].getY()>getSize().height){
					bullet[i].setAlive(false);
				}
			}
		}
	}
	
	public void updateAsteroids() {
		for (int i=0;i<ASTEROIDS;i++) {
			if (asteroids[i].isAlive()){
				asteroids[i].incX(asteroids[i].getVelX());
				
				if (asteroids[i].getX()<-20) {
					asteroids[i].setX(getSize().width+20);
				} else if (asteroids[i].getX() > getSize().width+20) {
					asteroids[i].setX(-20);
				}
				
				asteroids[i].incY(asteroids[i].getVelY());
				
				if (asteroids[i].getY()<-20) {
					asteroids[i].setY(getSize().height+20);
				} else if (asteroids[i].getY() > getSize().height+20) {
					asteroids[i].setY(-20);
				}				
				
				asteroids[i].incMoveAngle(asteroids[i].getRotationVelocity());
				
				if (asteroids[i].getMoveAngle() < 0){
					asteroids[i].setMoveAngle(360-asteroids[i].getRotationVelocity());
				} else if (asteroids[i].getMoveAngle() > 360) {
					asteroids[i].setMoveAngle(asteroids[i].getRotationVelocity());
				}
			}
		}
	}
	
	public void checkCollisions(){
		for (int i=0;i<ASTEROIDS;i++){
			if (asteroids[i].isAlive()) {
				for (int b=0;b<BULLETS;b++) {
					if (bullet[b].isAlive()){
						if (asteroids[i].getBounds().contains(bullet[b].getX(),bullet[b].getY())){
							bullet[b].setAlive(false);
							asteroids[i].setAlive(false);
							continue;
						}
					}
				}
				
				if (asteroids[i].getBounds().intersects(ship.getBounds())){
					asteroids[i].setAlive(false);
					ship.setX(320);
					ship.setY(240);
					ship.setFaceAngle(0);
					ship.setVelX(0);
					ship.setVelY(0);
					continue;
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT:
				ship.incFaceAngle(-10);
				if (ship.getFaceAngle()<0) ship.setFaceAngle(360-5);
				break;
			case KeyEvent.VK_RIGHT:
				ship.incFaceAngle(10);
				if (ship.getFaceAngle()>360) ship.setFaceAngle(5);
				break;
			case KeyEvent.VK_UP:
				ship.setMoveAngle(ship.getFaceAngle()-90);
				ship.incVelX(calcAngleMoveX(ship.getMoveAngle())*0.1);
				ship.incVelY(calcAngleMoveY(ship.getMoveAngle())*0.1);
				break;
//			case KeyEvent.VK_DOWN:
//				ship.setMoveAngle(ship.getFaceAngle()-90);
//				ship.incVelX(calcAngleMoveX(ship.getMoveAngle())*-0.1);
//				ship.incVelY(calcAngleMoveY(ship.getMoveAngle())*-0.1);
//				break;				
			case KeyEvent.VK_CONTROL:
			case KeyEvent.VK_ENTER:
			case KeyEvent.VK_SPACE:
				currentBullet++;
				if (currentBullet > BULLETS-1) currentBullet=0;
				bullet[currentBullet].setAlive(true);
				
				bullet[currentBullet].setX(ship.getX());
				bullet[currentBullet].setY(ship.getY());
				bullet[currentBullet].setMoveAngle(ship.getFaceAngle()-90);
				
				double angle = bullet[currentBullet].getMoveAngle();
				double svx = ship.getVelX();
				double svy = ship.getVelY();
				
				bullet[currentBullet].setVelX(svx+calcAngleMoveX(angle)*2);
				bullet[currentBullet].setVelY(svy+calcAngleMoveY(angle)*2);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public double calcAngleMoveX(double angle) {
		//Para converter de Grado () para Radiano (intervalo de 1-360)
		return Math.cos(angle*Math.PI/180); // Quanto mais proximo de 90, menos ele se movimenta no eixo X, quanto mais proximo de 0 mais ele se movimenta no eixo X
	}
	
	public double calcAngleMoveY(double angle) {
		return Math.sin(angle*Math.PI/180); // Quanto mais proximo de 90, mais ele se movimenta no eixo Y, quanto mais proximo de 0 menos ele se movimenta no eixo Y
	}
	
	
	public void start() {
		gameLoop = new Thread(this);
		gameLoop.start();
	}

	public static void main (String args[]){
		Asteroids jogo = new Asteroids();
		JFrame frm = new JFrame("Aster�ide v1.0");
		frm.getContentPane().add(jogo);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(640,480);
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
		jogo.start();
	}
	
}
