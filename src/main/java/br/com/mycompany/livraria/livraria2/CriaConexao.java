package br.com.mycompany.livraria.livraria2;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Statement;

public class CriaConexao {
	Connection con;
	private Connection oConn;
	private Statement sStmt;

	public CriaConexao() {
	}

	public Connection abrirBDConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/estoque";
			con = DriverManager.getConnection(url, "root", "admin");
			System.out.println("Conexão Ok");
			return con;
		} catch (Exception e) {
			System.out.println("Erro ao abrir conexão");
			e.printStackTrace();
			return null;
		}
	}

	public void fecharBDConn() {
		try {
			con.close();
			System.out.println("Conexão finalizada com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao fechar conexão com banco" + e.getMessage());
		}
	}
}


