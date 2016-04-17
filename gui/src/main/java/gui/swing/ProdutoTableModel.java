package gui.swing;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ProdutoTableModel implements TableModel {
	
	private List<Produto> produtos; 

	public ProdutoTableModel(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public void addTableModelListener(TableModelListener arg0) {
		
	}

	public Class<?> getColumnClass(int col) {
		switch (col) {
			case 0: return Long.class;
			case 1: return String.class;
			case 2: return Float.class;
			case 3: return Integer.class;
			case 4: return TipoProdutoEnum.class;
			default: return String.class;
		}
	}

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0: return "C—digo";
		case 1: return "Nome";
		case 2: return "Valor";
		case 3: return "Qtde";
		case 4: return "Tipo";
		default: return "";
	}

	}

	public int getRowCount() {
		return produtos.size();
	}

	public Object getValueAt(int row, int col) {
		switch (col) {
			case 0: return produtos.get(row).getIdProduto();
			case 1: return produtos.get(row).getNome();
			case 2: return produtos.get(row).getValor();
			case 3: return produtos.get(row).getQuantidade();
			case 4: return produtos.get(row).getTipo();
			default: return "";
		}
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void removeTableModelListener(TableModelListener arg0) {
		
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		
	}
	
	public Produto getProduto(int row) {
		return produtos.get(row);
	}

}








