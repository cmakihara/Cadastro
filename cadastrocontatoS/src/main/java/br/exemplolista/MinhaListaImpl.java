package br.exemplolista;

import java.util.Iterator;

public class MinhaListaImpl<T> implements MinhaLista<T> {

	class Vagao {
		private T valor;
		private Vagao prox;
		public T getValor() {
			return valor;
		}
		public void setValor(T valor) {
			this.valor = valor;
		}
		public Vagao getProx() {
			return prox;
		}
		public void setProx(Vagao prox) {
			this.prox = prox;
		}
	}
	
	private Vagao nodeInicial = new Vagao();
	private Vagao nodeAtual = nodeInicial;
	
	@Override
	public void adicionar(T item) {
		this.nodeAtual.setValor(item);
		Vagao proximo = new Vagao();
		this.nodeAtual.setProx(proximo);
		nodeAtual = proximo;
	}

	@Override
	public T getItemNoIndice(int index) {
		// return valores[index];
		Vagao step = nodeInicial;
		for (int i = 0; i < index; i ++) {
			step = step.getProx();
		}
		return step.getValor();
	}

	@Override
	public T removeItemDoIndice(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insereItemNoIndice(int index, T valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarTodosValores() {
		Vagao step = nodeInicial;
		while(step.getProx() != null) {
			System.out.println("Mostrando " + step.getValor());
			step = step.getProx();
		}
	}

	@Override
	public Iterator<T> iterator() {
		
		
		return new Iterator<T>() {
			
			Vagao step = nodeInicial;
			
			@Override
			public boolean hasNext() {
//				if (step.getProx() != null) {
//					return true;
//				} else {
//					return false;
//				}
				return step.getProx() != null;
			}

			@Override
			public T next() {
				T valor = step.getValor();
				step = step.getProx();
				return valor;
			}
		};
	}

	
	
	
	
	
	
	
}
