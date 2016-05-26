package gui.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FrmProduto extends JFrame{
    
    private JLabel lblPalavraChave, lblBusca, lblNome, lblValor, lblTipo, lblQuantidade;
    private JButton btnPesquisar, btnNovo, btnConfirmar, btnAlterar, btnCancelar, btnExcluir;
    private JTextField txtPalavraChave, txtNome, txtValor;
    private JComboBox cmbBusca, cmbTipo;
    private JSpinner spnQuantidade;
    private JTable tblConsulta;
    private JTabbedPane tbpPrincipal;
    private JScrollPane scpConsulta;
    private JPanel pnlPesquisa, pnlOpcoes, pnlEditar;
    
    private boolean novoProduto = true;
    private ProdutoNegocio produtoNegocio = new ProdutoNegocio(Database.getInstance().produtoDAO);

    public FrmProduto() {
        super("Manutencao");
        
        //INSTANCIAR LABELS
        lblPalavraChave = new JLabel("Palavra Chave");
        lblBusca        = new JLabel("Busca");
        lblNome         = new JLabel("Nome");
        lblValor        = new JLabel("Valor");
        lblTipo         = new JLabel("Tipo");
        lblQuantidade   = new JLabel("Quantidade");
        //INSTANCIA BOTOES
        btnPesquisar     = new JButton("Pesquisa");
        btnNovo         = new JButton("Novo");
        btnConfirmar     = new JButton("Confirma");
        btnAlterar       = new JButton("Altera");
        btnCancelar      = new JButton("Cancela");
        btnExcluir      = new JButton("Excluir");
        //INSTANCIA CAMPO DE TEXTO
        txtPalavraChave = new JTextField(30);
        txtNome         = new JTextField(30);
        txtValor        = new JTextField(10);
        //INSTANCIA COMBOBOX
        cmbBusca        = new JComboBox();
        cmbBusca.addItem("Nome");
        cmbBusca.addItem("Tipo");
        cmbTipo         = new JComboBox();
        //INSTANCIA SPINNER
        spnQuantidade   = new JSpinner();
        //INSTANCIA TABELA DE ABAS
        tbpPrincipal    = new JTabbedPane();
        //INSTANCIA TABELA
        tblConsulta     = new JTable();
        //INSTANCIA SCROLLPANE
        scpConsulta     = new JScrollPane(tblConsulta);
        //INSTANCIA JPANEL
        pnlPesquisa     = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlOpcoes       = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlEditar       = new JPanel();
        
        //Preenchendo Tipo Produto
        TipoProdutoEnum[] tipos = TipoProdutoEnum.values();
        for (int i = 0; i < tipos.length; i++)
        	cmbTipo.addItem(tipos[i]);
        
        //MONTAR O FRAME
        
        //PAINEL NORTE
        pnlPesquisa.add(lblPalavraChave);
        pnlPesquisa.add(txtPalavraChave);
        pnlPesquisa.add(lblBusca);
        pnlPesquisa.add(cmbBusca);
        pnlPesquisa.add(btnPesquisar);
        
        //PAINEL SUL
        pnlOpcoes.add(btnNovo);
        pnlOpcoes.add(btnConfirmar);
        pnlOpcoes.add(btnAlterar);
        pnlOpcoes.add(btnCancelar);
        pnlOpcoes.add(btnExcluir);

        //PAINEL EDICAO
        pnlEditar.setLayout(new BoxLayout(pnlEditar,BoxLayout.PAGE_AXIS));
        pnlEditar.add(lblNome);
        txtNome.setAlignmentX(LEFT_ALIGNMENT);
        txtNome.setMaximumSize(txtNome.getPreferredSize());
        pnlEditar.add(txtNome);
        pnlEditar.add(lblValor);
        txtValor.setAlignmentX(LEFT_ALIGNMENT);
        txtValor.setMaximumSize(txtValor.getPreferredSize());
        pnlEditar.add(txtValor);
        pnlEditar.add(lblTipo);
        cmbTipo.setAlignmentX(LEFT_ALIGNMENT);
        cmbTipo.setMaximumSize(cmbTipo.getPreferredSize());
        pnlEditar.add(cmbTipo);
        pnlEditar.add(lblQuantidade);
        spnQuantidade.setAlignmentX(LEFT_ALIGNMENT);
        spnQuantidade.setMaximumSize(spnQuantidade.getPreferredSize());
        pnlEditar.add(spnQuantidade);

        //ABAS DO CENTRO DO FORMULARIO
        tbpPrincipal.addTab("Consulta",null,scpConsulta,"Realize a consulta dos registros aqui!");
        tbpPrincipal.addTab("Edicao",null,pnlEditar,"Realize a edição do registro selecionado aqui!");
        
        //ADICIONA COMPONENTES NO FRAME
        Container ct = this.getContentPane();
        ct.setLayout(new BorderLayout());
        ct.add(pnlPesquisa,BorderLayout.NORTH);
        ct.add(pnlOpcoes,BorderLayout.SOUTH);
        ct.add(tbpPrincipal,BorderLayout.CENTER);
        
        btnPesquisar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnPesquisar_actionPerformed(e);
			}
        });
        
        btnNovo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnNovo_actionPerformed(e);
			}
        });
        
        btnConfirmar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnConfirmar_actionPerformed(e);
			}
        });

        btnAlterar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnAlterar_actionPerformed(e);
			}
        });

        btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnCancelar_actionPerformed(e);
			}
        });

        btnExcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				btnExcluir_actionPerformed(e);
			}
        });
        
        txtPalavraChave.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent evt) {
				txtPalavraChave_keyPressed(evt);
			}
        });
        
        tbpPrincipal.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent evt) {
				tbpPrincipal_stateChanged(evt);
			}
        });
        
        modoEdicao(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
    protected void tbpPrincipal_stateChanged(ChangeEvent evt) {
		if (tbpPrincipal.getSelectedIndex()==1) {
			int linha = tblConsulta.getSelectedRow();
			if (linha>=0) {
				Produto p = ((ProdutoTableModel)tblConsulta.getModel()).getProduto(linha);
				txtNome.setText(p.getNome());
				txtValor.setText(p.getValor()+"");
				spnQuantidade.setValue(p.getQuantidade());
				cmbTipo.setSelectedItem(p.getTipo());
			}
		}
	}

	protected void txtPalavraChave_keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == evt.VK_ENTER) btnPesquisar.doClick();
	}

	protected void btnExcluir_actionPerformed(ActionEvent e) {
		int linha = tblConsulta.getSelectedRow();
		if (linha>=0) {
			int r = JOptionPane.showConfirmDialog(this, "Deseja Realmente Excluir o Registro?");
			if (r == JOptionPane.YES_OPTION) {
				Produto p = ((ProdutoTableModel)tblConsulta.getModel()).getProduto(linha);
				try {
					produtoNegocio.excluir(p);
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(this, exc.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	protected void btnCancelar_actionPerformed(ActionEvent e) {
		modoEdicao(false);
	}

	protected void btnAlterar_actionPerformed(ActionEvent e) {
		int linha = tblConsulta.getSelectedRow();
		if (linha>=0) {
			modoEdicao(true);
			novoProduto = false;
			tbpPrincipal.setSelectedIndex(1);
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um produto antes!","Erro",JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void btnConfirmar_actionPerformed(ActionEvent e) {
		Produto p = new Produto();
		p.setNome(txtNome.getText());
		p.setQuantidade((Integer) spnQuantidade.getValue());
		p.setValor(Float.parseFloat(txtValor.getText()));
		p.setTipo((TipoProdutoEnum) cmbTipo.getSelectedItem());
		
		if (novoProduto) {
			try {
				produtoNegocio.salvar(p);
				modoEdicao(false);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(this, exc.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
			}
		} else {
			Long id = ((ProdutoTableModel)tblConsulta.getModel())
						.getProduto(tblConsulta.getSelectedRow()).getIdProduto();
			p.setIdProduto(id);
			try {
				produtoNegocio.alterar(p);
				modoEdicao(false);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(this, exc.getMessage(),"Erro",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	protected void btnNovo_actionPerformed(ActionEvent e) {
		modoEdicao(true);
		novoProduto = true;
	}

	protected void btnPesquisar_actionPerformed(ActionEvent e) {
		if (isModoEdicao()) {
			int r = JOptionPane.showConfirmDialog(this, "Voce está editando um Registro. Deseja cancelar a Edição?");
			
			if (r == JOptionPane.YES_OPTION) {
				btnCancelar.doClick();
			}
			
			if (r == JOptionPane.NO_OPTION) {
				//Nao Faz Nada
			}
		} else {
			List<Produto> produtos = null;
			if (cmbBusca.getSelectedItem().equals("Nome")) {
				produtos = produtoNegocio.getProdutoByNome(txtPalavraChave.getText());
			}
			if (cmbBusca.getSelectedItem().equals("Tipo")) {
				produtos = produtoNegocio.getProdutoByTipo(txtPalavraChave.getText());
			}
			//preenche a tabela com os dados de produtos
			ProdutoTableModel model = new ProdutoTableModel(produtos);
			tblConsulta.setModel(model);
		}
	}
	
	private void modoEdicao(boolean status) {
		btnNovo.setEnabled(!status);
		btnConfirmar.setEnabled(status);
		btnAlterar.setEnabled(!status);
		btnCancelar.setEnabled(status);
		btnExcluir.setEnabled(!status);
		
		txtNome.setEditable(status);
		txtValor.setEditable(status);
		cmbTipo.setEnabled(status);
		spnQuantidade.setEnabled(status);
	}
	
	private boolean isModoEdicao() {
		return btnCancelar.isEnabled();
	}

	public static void main (String args[]){
        new FrmProduto();
    }
    
}
