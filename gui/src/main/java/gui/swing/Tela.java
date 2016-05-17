package gui.swing;

import javax.swing.JInternalFrame;

public class Tela extends JInternalFrame {

	private Menu telaPrincipal;

	public Tela(String titulo, Menu telaPrincipal) {
		super(titulo, true, true, true, true);
		setSize(300, 200);
		setVisible(true);
		this.telaPrincipal = telaPrincipal;
		telaPrincipal.dktPane.add(this);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//setLocation(telaPrincipal.getX(), telaPrincipal.getY());
	}
}
