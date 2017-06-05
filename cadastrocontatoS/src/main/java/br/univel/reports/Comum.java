package br.univel.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Locale;

import br.univel.ConexaoDB;
import br.univel.Contato;
import br.univel.ContatoDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Comum {
	
	public static void main(String[] args) {
		
		String file = "C:\\Users\\Maki\\JaspersoftWorkspace\\MyReports\\teste.jasper";
		
		JasperPrint jasperPrintPDF;
		try {
			Connection con = ConexaoDB.getInstance().getConnection();
			
			jasperPrintPDF = JasperFillManager.fillReport(file, null, con);
			Locale locale = Locale.getDefault();
			JasperViewer.viewReport(jasperPrintPDF, false, locale);
			
			JasperExportManager.exportReportToPdfFile(jasperPrintPDF, "teste.pdf");
			
			Desktop.getDesktop().open(new File("teste.pdf"));
			
			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ContatoDao dao = new ContatoDao();
		List<Contato> lista = dao.getTodos();
		lista.forEach(System.out::println);
		
		System.out.println("teste");
				
	}

}
