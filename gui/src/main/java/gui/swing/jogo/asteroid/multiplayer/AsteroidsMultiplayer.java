package gui.swing.jogo.asteroid.multiplayer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.swing.jogo.asteroid.multiplayer.desenho.BaseVectorShape;
import gui.swing.jogo.asteroid.multiplayer.desenho.Bullet;
import gui.swing.jogo.asteroid.multiplayer.desenho.Ship;

@SuppressWarnings("serial")
public class AsteroidsMultiplayer extends JPanel implements Runnable, KeyListener {

	final int BULLETS = 10;
	
	Thread gameLoop;
	BufferedImage backBuffer;
	Graphics2D g2d;
	boolean showBounds = false;

	Bullet bullet[] = new Bullet[BULLETS];
	int currentBullet=0;
	
	Ship ship = new Ship();
	
	AffineTransform identity = new AffineTransform();
	
	Random random = new Random();
	
	DatagramSocket ds;
	ByteArrayOutputStream bos;
	ByteArrayInputStream bis;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	InetAddress address;
	
	Vector<BaseVectorShape> networkObjects1 = new Vector<BaseVectorShape>();
	Vector<BaseVectorShape> networkObjects2 = new Vector<BaseVectorShape>();

	public AsteroidsMultiplayer() {
		super();
		
		 try {
			ds = new DatagramSocket();
			address = InetAddress.getByName("255.255.255.255");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		 
		bos = new ByteArrayOutputStream();
		try {
			oos = new ObjectOutputStream(bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ship.setCor(Color.RED);
		
		setFocusable(true); //Isso � para o teclado funcione, sen�o o foco n�o vai para o painel
		backBuffer = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();
		
		//centraliza a nave
		ship.setX(320);
		ship.setY(240);
		
		for (int i=0;i<BULLETS;i++) {
			bullet[i] = new Bullet(ship.getCor());
		}
		
		addKeyListener(this);
		
		new Thread() {
			byte[] bVectorShape = new byte[65536];
			DatagramSocket ds;
			
			public void run() {
				try {
					ds = new DatagramSocket(5555);
				} catch (SocketException e) {
					e.printStackTrace();
				}
				while (true) {
					DatagramPacket dp = new DatagramPacket(bVectorShape, bVectorShape.length);
					try {
						ds.receive(dp);
						bis = new ByteArrayInputStream(dp.getData());
						ois = new ObjectInputStream(bis);
						try {
							BaseVectorShape bvs = (BaseVectorShape) ois.readObject();
								networkObjects1.add(bvs);
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						ois.close();
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
		
	@Override
	public void paint(Graphics g) {
		g2d.setTransform(identity);
		
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, 640, 480);
		
		networkObjects2.addAll(networkObjects1);
		networkObjects1.removeAll(networkObjects2);
		
		Iterator<BaseVectorShape> i = networkObjects2.iterator();
		
		while (i.hasNext()) {
			BaseVectorShape bvs = i.next();
			g2d.setTransform(identity);
			g2d.setColor(bvs.getCor());
			g2d.translate(bvs.getX(), bvs.getY());
			g2d.rotate(Math.toRadians(bvs.getFaceAngle()));
			g2d.fill(bvs.getShape());
		}
				
		g.drawImage(backBuffer, 0, 0, this);
		networkObjects2.removeAllElements();
	}	
	
	@Override
	public void run() {
		Thread t = Thread.currentThread();
		while (t == gameLoop){
			try {
				gameUpdate();
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			repaint();
		}
	}
	
	public void gameUpdate(){
		updateShip();
		updateBullets();
		checkCollisions();
		sendShip();
		sendBullets();
	}
	
	private void sendShip() {
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(ship);
			byte[] bShip = bos.toByteArray();
			DatagramPacket dp = new DatagramPacket(bShip, bShip.length, address, 5555);
			ds.send(dp);
			oos.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendBullets() {
		try {
			for (Bullet b : bullet) {
				bos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(bos);
				oos.writeObject(b);
				byte[] bBullet = bos.toByteArray();
				DatagramPacket dp = new DatagramPacket(bBullet, bBullet.length, address, 5555);
				ds.send(dp);
				oos.close();
				bos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void checkCollisions(){

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
		AsteroidsMultiplayer jogo = new AsteroidsMultiplayer();
		JFrame frm = new JFrame("Aster�ide v1.0");
		frm.getContentPane().add(jogo);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setSize(640,480);
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
		jogo.start();
	}
	
}
