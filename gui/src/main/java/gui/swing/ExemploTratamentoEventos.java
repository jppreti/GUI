package gui.swing;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExemploTratamentoEventos extends JFrame {

	JButton btn1,btn2;
	
	public ExemploTratamentoEventos() {
		super("Exemplo de Trantamento de Eventos");
		
		Container ct = this.getContentPane();
		ct.setLayout(new FlowLayout());
		
		btn1 = new JButton("Botão 1");
		btn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btn1_actionPerformed(e);
			}
		});
		
		btn1.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btn1_mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn1_mouseExited(e);
			}			
		});
		
		btn2 = new JButton("Botão 2");
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btn2_actionPerformed(e);
			}
		});
		
		ct.add(btn1);
		ct.add(btn2);
		
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	protected void btn1_mouseExited(MouseEvent e) {
		btn1.setText("Botão 1");
	}

	protected void btn1_mouseEntered(MouseEvent e) {
		btn1.setText("Mouse na Área!");
	}

	protected void btn2_actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Botão 2 Clicado!");		
	}

	protected void btn1_actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Botão 1 Clicado!");
	}

	public static void main(String[] args) {
		new ExemploTratamentoEventos();
	}
	
}
