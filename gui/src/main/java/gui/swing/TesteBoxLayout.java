package gui.swing;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TesteBoxLayout extends JFrame {
	
	public TesteBoxLayout() {
		JTabbedPane tbp = new JTabbedPane();
		JPanel pnl = new JPanel();
		pnl.setLayout(new BoxLayout(pnl,BoxLayout.PAGE_AXIS));
		pnl.add(new JButton("asdsa"));
		pnl.add(new JButton("adsadsadasd"));
		pnl.add(new JButton("asdasdd"));
		pnl.add(new JTextField(20));
		tbp.addTab("dasd" ,pnl);
		this.getContentPane().add(tbp);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TesteBoxLayout();
	}

}
