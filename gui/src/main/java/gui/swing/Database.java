package gui.swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Database db;
	private Connection con;
	
	ProdutoDAO produtoDAO;
	
	private Database() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db","postgres","postgres");
			produtoDAO = new ProdutoDAO(con);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Database getInstance() {
		if (db == null) {
			db = new Database();
		}
		return db;
	}	
	
	public static void main(String[] args) {
		Produto p = Database.getInstance().produtoDAO.getProdutoById(1);
		System.out.println(p.getNome());
	}
}










