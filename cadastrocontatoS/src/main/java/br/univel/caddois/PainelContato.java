package br.univel.caddois;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import br.univel.Contato;
import br.univel.ContatoDao;
import br.univel.ContatoModel;
import br.univel.ReportManager;

public class PainelContato extends PainelContatoBase {
	private Contato contatoSelecionado;
	
	private ContatoModel modelo;
	
	public PainelContato() {
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
		
		ContatoDao dao = new ContatoDao();
		List<Contato> lista = dao.getTodos();
		
		this.modelo = new ContatoModel(lista);
		// this.modelo = new ContatoModel();
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
		Contato c = this.modelo.getContato(idx);
		this.contatoSelecionado = c;
		this.labelAlerta.setText(CARREGADO_PARA_ALTERACAO);
		
		super.txfId.setText(String.valueOf(c.getId()));
		super.txfNome.setText(c.getNome());
		super.txfTelefone.setText(c.getTelefone());
		
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
		this.modelo.remover(this.contatoSelecionado);
		limparCampos();
	}


	protected void salvar() {
		if (contatoSelecionado == null) {
			Contato c = new Contato();
			
			String strId = txfId.getText().trim();
			String strNome = txfNome.getText().trim();
			String strTelefone = txfTelefone.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			c.setId(intId);
			c.setNome(strNome);
			c.setTelefone(strTelefone);
			
			this.modelo.adicionar(c);
			
			limparCampos();
			
		} else {
			
			String strId = txfId.getText().trim();
			String strNome = txfNome.getText().trim();
			String strTelefone = txfTelefone.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			this.contatoSelecionado.setId(intId);
			this.contatoSelecionado.setNome(strNome);
			this.contatoSelecionado.setTelefone(strTelefone);
			
			limparCampos();
			this.modelo.fireTableDataChanged();
		}
		
	}


	protected void novo() {
		this.contatoSelecionado = null;
		
		limparCampos();
	}


	private void limparCampos() {
		super.labelAlerta.setText("");
		super.txfId.setText("");
		super.txfNome.setText("");
		super.txfTelefone.setText("");
		
		super.btnExcluir.setEnabled(false);
	}
}
