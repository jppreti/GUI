package gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FrmPrincipal extends JFrame {
	
	JMenuBar mnuBar;
	JMenu mnuCadastro;
	JMenuItem mniProduto, mniSair;
	
	FrmProduto frmProduto;
	
	public FrmPrincipal() {
		super("Sistema de Venda de Produtos ...");
		
		mnuBar = new JMenuBar();
		mnuCadastro = new JMenu("Cadastro");
		mniProduto = new JMenuItem("Produto");
		mniSair = new JMenuItem("Sair");
		
		mnuCadastro.add(mniProduto);
		mnuCadastro.addSeparator();
		mnuCadastro.add(mniSair);
		
		mnuBar.add(mnuCadastro);
		
		this.setJMenuBar(mnuBar);
		
		mniProduto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				mniProduto_actionPerformed(evt);
			}			
		});

		mniSair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				mniSair_actionPerformed(evt);
			}			
		});		
		
		this.setSize(800,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	protected void mniSair_actionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	protected void mniProduto_actionPerformed(ActionEvent evt) {
		if (frmProduto == null)
			frmProduto = new FrmProduto();
		else
			frmProduto.setVisible(true);
	}

	public static void main(String[] args) {
		new FrmPrincipal();
	}
}
