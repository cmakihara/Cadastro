package br.univel.reports.custom;

import java.util.Iterator;
import java.util.List;

import br.univel.Contato;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class CustomContatoReport implements JRDataSource {
	
	// private int contador = 0;
	
	private Iterator<Contato> it;
	private Contato selecionado;

	public CustomContatoReport(List<Contato> lista) {
		this.it = lista.iterator();
	}

	@Override
	public Object getFieldValue(JRField field) throws JRException {
		
		if ("id".equals(field.getName())) {
			return selecionado.getId();
		}
		if ("nome".equals(field.getName())) {
			return selecionado.getNome();
					
		}
		if ("telefone".equals(field.getName())) {
			return selecionado.getTelefone();
		}
		
		throw new RuntimeException("Deu ruim!");
	}

	@Override
	public boolean next() throws JRException {
		//return contador++ < 100;
		if (this.it.hasNext()) {
			this.selecionado = it.next();
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
