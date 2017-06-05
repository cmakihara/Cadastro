package br.exemplolista;

import java.util.Iterator;

public class TesteDaMinhaLista {

	
	public static void main(String[] args) {
		
		
		MinhaLista<Integer> lista = new MinhaListaImpl<>();

		for (int i = 0; i < 1000; i++) {
			System.out.println("Adicionando " + i);
			lista.adicionar(i);
		}
		
//		lista.mostrarTodosValores();
		
		Iterator<Integer> it = lista.iterator();
		while (it.hasNext()) {
			Integer val = it.next();
			System.out.println("Most Dif " + val);
		}
		
		
		
		
		
		
		
		
		
		
	}
	
}
