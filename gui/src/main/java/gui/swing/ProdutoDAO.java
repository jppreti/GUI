package gui.swing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	
	private Connection con;
	
	private static final String SQL_INSERT, SQL_UPDATE, SQL_DELETE, SQL_SELECT_ID, SQL_SELECT_NOME, SQL_SELECT_TIPO;
	private PreparedStatement pstmtInsert, pstmtUpdate, pstmtDelete, pstmtSelectId, pstmtSelectNome, pstmtSelectTipo;
	
	static {
		SQL_INSERT = "INSERT INTO produto (nome, valor, quantidade, tipo) VALUES (?,?,?,?)";
		SQL_UPDATE = "UPDATE produto SET nome=?, valor=?, quantidade=?, tipo=? WHERE idproduto=?";
		SQL_DELETE = "DELETE FROM produto WHERE idproduto = ?";
		SQL_SELECT_ID = "SELECT * FROM produto WHERE idproduto = ?";
		SQL_SELECT_NOME = "SELECT * FROM produto WHERE nome ILIKE ?";
		SQL_SELECT_TIPO = "SELECT * FROM produto WHERE tipo ILIKE ?";
	}
	
	public ProdutoDAO(Connection con) {
		this.con = con;
		try {
			pstmtInsert = con.prepareStatement(SQL_INSERT);
			pstmtUpdate = con.prepareStatement(SQL_UPDATE);
			pstmtDelete = con.prepareStatement(SQL_DELETE);
			pstmtSelectId = con.prepareStatement(SQL_SELECT_ID);
			pstmtSelectNome = con.prepareStatement(SQL_SELECT_NOME);
			pstmtSelectTipo = con.prepareStatement(SQL_SELECT_TIPO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void salvar(Produto p) {
		try {
			pstmtInsert.setString(1, p.getNome());
			pstmtInsert.setFloat(2, p.getValor());
			pstmtInsert.setInt(3, p.getQuantidade());
			pstmtInsert.setString(4, p.getTipo().toString());
			pstmtInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Produto p) {
		try {
			pstmtUpdate.setString(1, p.getNome());
			pstmtUpdate.setFloat(2, p.getValor());
			pstmtUpdate.setInt(3, p.getQuantidade());
			pstmtUpdate.setString(4, p.getTipo().toString());
			pstmtUpdate.setLong(5, p.getIdProduto());
			pstmtUpdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public void excluir(Produto p) {
		try {
			pstmtDelete.setLong(1, p.getIdProduto());
			pstmtDelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Produto getProduto(ResultSet rs) {
		Produto p = new Produto();
		try {
			p.setIdProduto(rs.getLong("idproduto"));
			p.setNome(rs.getString("nome"));
			p.setValor(rs.getFloat("valor"));
			p.setQuantidade(rs.getInt("quantidade"));
			p.setTipo(TipoProdutoEnum.valueOf(rs.getString("tipo")));
			return p;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public Produto getProdutoById(long id) {
		try {
			pstmtSelectId.setLong(1, id);
			ResultSet rs = pstmtSelectId.executeQuery();
			if (rs.next()) return getProduto(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Produto> getProdutoByNome(String nome) {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			pstmtSelectNome.setString(1, "%"+nome+"%");
			ResultSet rs = pstmtSelectNome.executeQuery();
			while (rs.next()) {
				produtos.add(getProduto(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}	
	
	public List<Produto> getProdutoByTipo(String tipoProduto) {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			pstmtSelectTipo.setString(1, "%"+tipoProduto+"%");
			ResultSet rs = pstmtSelectTipo.executeQuery();
			while (rs.next()) {
				produtos.add(getProduto(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;

	}
}





