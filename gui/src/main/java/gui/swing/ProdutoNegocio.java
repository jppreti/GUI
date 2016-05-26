package gui.swing;

import java.util.List;

public class ProdutoNegocio {

	ProdutoDAO dao;
	
	public ProdutoNegocio(ProdutoDAO dao) {
		this.dao = dao;
	}
	
	public void salvar(Produto p) throws Exception {
		valida(p);
		dao.salvar(p);
	}
	
	public void alterar(Produto p) throws Exception {
		valida(p);
		dao.alterar(p);
	}
	
	public void excluir(Produto p) throws Exception {
		if (p.getIdProduto()==null) {
			throw new Exception("Não é possível excluir um produto que não tenha código!");
		} else {
			dao.excluir(p);
		}
	}
	
	public Produto getProdutoById(long id) {
		return dao.getProdutoById(id);
	}
	
	public List<Produto> getProdutoByNome(String nome) {
		return dao.getProdutoByNome(nome);
	}	
	
	public List<Produto> getProdutoByTipo(String tipoProduto) {
		return dao.getProdutoByTipo(tipoProduto);
	}
	
	private void valida(Produto p) throws Exception {
		StringBuilder mensagem = new StringBuilder();
		if (p.getNome()==null || p.getNome().trim().length()<5) {
			mensagem.append("Nome do produto deve conter pelo menos 5 caracteres!");
		}
		if (p.getValor()<0) {
			mensagem.append("Valor do produto não pode ser negativo!");
		}
		if (p.getTipo() == null) {
			mensagem.append("Tipo do produto é de preenchimento obrigatório!");
		}
		if (p.getQuantidade()<0) {
			mensagem.append("Quantidade do produto não pode ser negativa!");
		}
		
		if (mensagem.length()>0) throw new Exception(mensagem.toString());
	}
	
}
