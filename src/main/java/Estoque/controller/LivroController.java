package Estoque.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import br.com.mycompany.livraria.livraria2.CriaConexao;
import sistema.bin.LivroBin;

public class LivroController {

	LivroBin LivroBin = new LivroBin();

	public void InserirDados(String titulo, String editora) {
		CriaConexao banco = new CriaConexao();

		try {

			Connection ExConn = (Connection) banco.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = "INSERT INTO estoque (id, titulo, editora) VALUES (null,'" + titulo + "','" + editora
					+ "' );";
			System.out.println(sSQL);
			boolean res = stmt.execute(sSQL);

			JOptionPane.showMessageDialog(null,
					(!res) ? "Dados inseridos com sucesso!" : "" + "Os dados não puderam ser inseridos");
			stmt.close();
			banco.fecharBDConn();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Os dados não puderam ser inseridos");
		}
	}

	public void Excluir(int id) {
		CriaConexao banco = new CriaConexao();
		try {

			Connection ExConn = (Connection) banco.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = "DELETE FROM estoque WHERE id = " + id + ";";
			boolean res = stmt.execute(sSQL);
			JOptionPane.showMessageDialog(null,
					(!res) ? "Dados excluídos com sucesso!" : "" + "Os dados não puderam ser excluídos");
			stmt.close();
			banco.fecharBDConn();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Os dados não puderam ser inseridos");
		}
	}

	public String Alterar(String titulo, String editora, int id) {
		// , LivroBin LivroBin

		CriaConexao banco = new CriaConexao();

		String retorno = "erro";
		int res;
		try {

			Connection ExConn = (Connection) banco.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			res = stmt.executeUpdate(
					"UPDATE estoque SET titulo = '" + titulo + "', editora = '" + editora + "' WHERE id = " + id);
			if (res == 1)
				JOptionPane.showMessageDialog(null, "Os dados foram alterados com sucesso");
			stmt.close();
			banco.fecharBDConn();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Os dados não puderam ser alterados");
		}
		return retorno;

	}

	public void Buscar(int id, LivroBin LivroBin) {
		CriaConexao banco = new CriaConexao();
		try {

			Connection ExConn = (Connection) banco.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = "SELECT * FROM estoque WHERE id = " + id;
			ResultSet rs = stmt.executeQuery(sSQL);

			while (rs.next()) {
				LivroBin.setId(rs.getInt("id"));
				LivroBin.setTitulo(rs.getString("titulo"));
				LivroBin.setEditora(rs.getString("editora"));
			}
			stmt.close();
			banco.fecharBDConn();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Os dados não puderam ser encontrados");
		}

	}

	public void preenche_tabela(JTable Tabela) throws SQLException {
		CriaConexao banco = new CriaConexao();
		Connection Conn = (Connection) banco.abrirBDConn();
		try {
			DefaultTableModel modelo = (DefaultTableModel) Tabela.getModel();
			modelo.setNumRows(0);

			Statement statement = (Statement) Conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet st = statement.executeQuery("SELECT * FROM estoque");
			while (st.next()) {
				modelo.addRow(new Object[] { Tabela.getRowCount() + 1, st.getInt("id"), st.getString("Titulo"),
						st.getString("Editora"),

				});
			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro ao adicionar na tabela");
		}
	}
}