package br.exemplolista;

import java.util.Iterator;

public interface MinhaLista<T> {
	
	public void adicionar(T item);
	
	public T getItemNoIndice(int index);
	
	public T removeItemDoIndice(int index);
	
	public void insereItemNoIndice(int index, T valor);
	
	public void mostrarTodosValores();
	
	public Iterator<T> iterator();

}
