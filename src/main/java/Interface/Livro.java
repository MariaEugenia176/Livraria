package Interface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Estoque.controller.LivroController;
import sistema.bin.Listagem;
import sistema.bin.LivroBin;

public class Livro extends JFrame {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtEditora;
	private int Id;

	LivroController LivroController = new LivroController();
	LivroBin LivroBin = new LivroBin();
	Listagem Listagem = new Listagem();
	static Livro frame = new Livro();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Livro() {
		setTitle("Livraria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCadastroLivraria = new JLabel("Cadastro Livros");
		lblCadastroLivraria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCadastroLivraria.setBounds(10, 11, 185, 37);
		contentPane.add(lblCadastroLivraria);

		JLabel lblTtulo = new JLabel("Título:");
		lblTtulo.setBounds(10, 58, 46, 14);
		contentPane.add(lblTtulo);

		JLabel lblEditora = new JLabel("Editora:");
		lblEditora.setBounds(10, 92, 46, 14);
		contentPane.add(lblEditora);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(62, 55, 301, 17);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);

		txtEditora = new JTextField();
		txtEditora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtEditora.setColumns(10);
		txtEditora.setBounds(62, 89, 301, 17);
		contentPane.add(txtEditora);

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = txtTitulo.getText();
				String editora = txtEditora.getText();

				if (txtTitulo.getText() == null || txtTitulo.getText().trim().equals("") || txtEditora.getText() == null || txtEditora.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Não é possível fazer a inclusão com campos em branco");
					
				}else if (LivroController.buscarLivroExistente(txtTitulo.getText())){
					JOptionPane.showMessageDialog(null, "Não é possível incluir livros repetidos");
				} 
				else {
					LivroController.InserirDados(titulo, editora);
				}
			}
		});
		
		
		btnIncluir.setBounds(103, 132, 80, 23);
		contentPane.add(btnIncluir);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listagem.ReceberDados(frame, LivroBin);
				Listagem.setVisible(true);
				LivroController.Excluir(txtTitulo.getText(), txtEditora.getText(), Id);

			}
		});
		btnExcluir.setBounds(283, 132, 80, 23);
		contentPane.add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTitulo.setText("");
				txtEditora.setText("");
			}
		});
		btnLimpar.setBounds(10, 132, 80, 23);
		contentPane.add(btnLimpar);
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Listagem.ReceberDados(frame, LivroBin);
				Listagem.setVisible(true);
				LivroController.Alterar(txtTitulo.getText(), txtEditora.getText(), Id);

			}
		});
		btnAlterar.setBounds(193, 132, 80, 23);
		contentPane.add(btnAlterar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Listagem.ReceberDados(frame, LivroBin);
				Listagem.setVisible(true);

			}
		});
		btnBuscar.setBounds(283, 166, 80, 23);
		contentPane.add(btnBuscar);
	}

	public void preenche_campo() {
		txtTitulo.setText(LivroBin.getTitulo());
		txtEditora.setText(LivroBin.getEditora());
		Id = LivroBin.getId();

	}
}
