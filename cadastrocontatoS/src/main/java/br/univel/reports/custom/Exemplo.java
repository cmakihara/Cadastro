package br.univel.reports.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Exemplo {

	
	public static void main(String[] args) {
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		lista.add(6);
		
		for (int i = 0; i < lista.size(); i++) {
			int num = lista.get(i);
			System.out.println(num);
		}
		
		for (Integer num : lista) {
			System.out.println(num);
		}
		
		lista.forEach(System.out::println);
		
		lista.forEach(num -> System.out.println(num));
		
		lista.forEach(num -> { 
			
			int i = num + 2;
			System.out.println(i);
			
		});
		
		Iterator<Integer> it = lista.iterator();
		while (it.hasNext()) {
			Integer num = it.next();
			System.out.println(num);
		}
		
		
		
		
		
		
	}
	
}
