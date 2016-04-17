package gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JFrame {

	private JMenuBar mnuBar = new JMenuBar();

	private JMenu mnuCadastro = new JMenu("Cadastros");

	private JMenuItem mniCliente = new JMenuItem("Cliente");
	private JMenuItem mniFornecedor = new JMenuItem("Fornecedor");

	public JDesktopPane dktPane = new JDesktopPane();

	Tela telaCadCliente, telaCadFornecedor, telaCadProduto;

	public Menu() {
		getContentPane().add(dktPane);

		mnuBar.add(mnuCadastro);
		mnuCadastro.add(mniCliente);
		mnuCadastro.add(mniFornecedor);
		setJMenuBar(mnuBar);

		mniCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mniCliente_actionPerformed(e);
			}
		});

		mniFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mniFornecedor_actionPerformed(e);
			}
		});

		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	protected void mniFornecedor_actionPerformed(ActionEvent e) {
		if (telaCadFornecedor == null)
			telaCadFornecedor = new Tela("Cadastro de Fornecedor",this);
		telaCadFornecedor.setVisible(true);
		dktPane.moveToFront(telaCadFornecedor);
	}

	protected void mniCliente_actionPerformed(ActionEvent e) {
		if (telaCadCliente==null)
			telaCadCliente = new Tela("Cadastro de Cliente",this);
		telaCadCliente.setVisible(true);
		dktPane.moveToFront(telaCadCliente);
	}

	public static void main(String args[]) {
		Menu menu = new Menu();
	}
}