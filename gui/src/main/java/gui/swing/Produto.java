package gui.swing;

public class Produto {
	
	private Long idProduto;
	private String nome;
	private float valor;
	private int quantidade;
	private TipoProdutoEnum tipo;
	
	public Produto() {
		idProduto = null;
		nome = "";
		valor = 0;
		quantidade = 0;
		tipo = null;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public TipoProdutoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoProdutoEnum tipo) {
		this.tipo = tipo;
	}
	
	
	
}
