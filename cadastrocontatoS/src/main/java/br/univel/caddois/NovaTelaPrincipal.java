package br.univel.caddois;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NovaTelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovaTelaPrincipal frame = new NovaTelaPrincipal();
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
	public NovaTelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JButton btnContato = new JButton("Contato");
		btnContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarAbaContato();
			}
		});
		panel.add(btnContato);
		
		JButton btnProduto = new JButton("Produto");
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarAbaProduto();
			}
		});
		panel.add(btnProduto);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

	protected void adicionarAbaProduto() {
		JPanel painelProduto = new PainelProduto();
		
		PainelWrapper wrapper = new PainelWrapper();
		wrapper.setConteudo(painelProduto);
		wrapper.setTitulo("Cadastro de Produto");
		
		wrapper.setAcaoFechar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);
			}
		});
		
		
		
		
		
		tabbedPane.addTab("Produto", wrapper);
		
	}

	protected void adicionarAbaContato() {
		JPanel painelContato = new PainelContato();
		
		PainelWrapper wrapper = new PainelWrapper();
		wrapper.setConteudo(painelContato);
		wrapper.setTitulo("Cadastro de Contatos");
		
		wrapper.setAcaoFechar(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);
			}
		});
		
		
		
		
		
		tabbedPane.addTab("Contato", wrapper);
		
	}

}
