package sistema.bin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Estoque.controller.LivroController;
import Interface.Livro;

public class Listagem extends JFrame {

	String[] coluna = { "", "Id", "Titulo", "Editora" };
	String[][] linhas = {};
	
	private DefaultTableModel tabela = new DefaultTableModel(linhas, coluna);
	private JScrollPane Scroll = null;
	private JTable Tabela = null;

	private Livro Livro = null;
	private LivroBin LivroBin = null;	 
	private LivroController LivroController = new LivroController();
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listagem frame = new Listagem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JTable getTabela() {
		if (Tabela == null) {
			Tabela = new JTable(tabela);
			Tabela.addMouseListener(new MouseAdapter() {

				public void mouseReleased(MouseEvent e) {
					int i = Tabela.getSelectedRow();
					Object x = Tabela.getValueAt(i, 1);
					String id = x + "" ;
					int cod = Integer.parseInt(id);
					LivroController.Buscar(cod, LivroBin);
					Livro.preenche_campo();
				}
			});
		}
		return Tabela;
	}

	private JScrollPane getScroll() {
		if (Scroll == null) {
			Scroll = new JScrollPane();
			Scroll.setBackground(SystemColor.text);
			Scroll.setViewportView(getTabela());
			Scroll.setBounds(10, 61, 611, 253);
			defineRenderers();
		}
		return Scroll;
	}

	private void defineRenderers() {
		Tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JTableHeader header = Tabela.getTableHeader();
		header.setPreferredSize(new Dimension(2000, 25));
		TableColumnModel modeloDaColuna = Tabela.getColumnModel();

		modeloDaColuna.getColumn(0).setPreferredWidth(30); // Numero da linha
		modeloDaColuna.getColumn(1).setPreferredWidth(50); // Id
		modeloDaColuna.getColumn(2).setPreferredWidth(150);// Titulo
		modeloDaColuna.getColumn(3).setPreferredWidth(150);// Editora
	}

	public Listagem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScroll(),null);
		
		JButton btnAtualizarTabela = new JButton("AtualizarTabela");
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				LivroController.preenche_tabela(Tabela);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		btnAtualizarTabela.setBounds(474, 37, 147, 23);
		contentPane.add(btnAtualizarTabela);
	}
	
	public void ReceberDados(Livro Livro, LivroBin LivroBin){
		this.Livro = Livro;
		this.LivroBin = LivroBin;
	}
}
