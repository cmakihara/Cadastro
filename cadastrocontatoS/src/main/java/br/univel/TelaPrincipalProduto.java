package br.univel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class TelaPrincipalProduto  extends TelaPrincipalBase2 {
	
	private Produto produtoSelecionado;
	
	private ProdutoModel modelo;
	
	public TelaPrincipalProduto() {
		super();
		limparCampos();
		configurarBotoes();
		configuraTabela();
		configuraMenus();
	}

	
	private void configuraMenus() {
		super.mntmImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportManager rm = new ReportManager();
				rm.imprimir();
			}
		});
		super.mntmExportarPdf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReportManager rm = new ReportManager();
				rm.exportar();				
			}
		});
				
		
	}


	private void configuraTabela() {
		
		ProdutoDao dao = new ProdutoDao();
		List<Produto> lista = dao.getTodos();
		
		this.modelo = new ProdutoModel(lista);
	
		super.table.setModel(modelo);
		
		super.table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int idx = table.getSelectedRow();
					if (idx < 0) {
						System.out.println("Não há linha selecionada");
					} else {
						System.out.println("Linha " + idx);
						carregarLinha(idx);
					}
				}
			}
		});

			
		
	}


	protected void carregarLinha(int idx) {
		Produto p = this.modelo.getProduto(idx);
		this.produtoSelecionado = p;
		this.labelAlerta.setText(CARREGADO_PARA_ALTERACAO);
		
		super.txfId.setText(String.valueOf(p.getId()));
		super.txfNome.setText(p.getNome());
		super.txfValor.setText(p.getValor());
		
		super.btnExcluir.setEnabled(true);
		
	}


	private void configurarBotoes() {
		super.btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Novo");
				novo();
			}
		});
		super.btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Salvar");
				salvar();
			}
		});
		super.btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou Excluir");
				excluir();
			}
		});
	}


	protected void excluir() {
		this.modelo.remover(this.produtoSelecionado);
		limparCampos();
	}


	protected void salvar() {
		if (produtoSelecionado == null) {
			Produto p = new Produto();
			
			String strId = txfId.getText().trim();
			String strNome = txfNome.getText().trim();
			String strValor = txfValor.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			p.setId(intId);
			p.setNome(strNome);
			p.setValor(strValor);
			
			this.modelo.adicionar(p);
			
			limparCampos();
			
		} else {
			
			String strId = txfId.getText().trim();
			String strNome = txfNome.getText().trim();
			String strValor = txfValor.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			this.produtoSelecionado.setId(intId);
			this.produtoSelecionado.setNome(strNome);
			this.produtoSelecionado.setValor(strValor);
			
			limparCampos();
			this.modelo.fireTableDataChanged();
		}
		
	}


	protected void novo() {
		this.produtoSelecionado = null;
		
		limparCampos();
	}


	private void limparCampos() {
		super.labelAlerta.setText("");
		super.txfId.setText("");
		super.txfNome.setText("");
		super.txfValor.setText("");
		
		super.btnExcluir.setEnabled(false);
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
//			UIManager.setLookAndFeel(
//			        UIManager.getSystemLookAndFeelClassName());
			
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalProduto frame = new TelaPrincipalProduto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



}
