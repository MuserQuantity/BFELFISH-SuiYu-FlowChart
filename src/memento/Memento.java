package memento;

import java.io.Serializable;
import java.util.LinkedList;

import symbols.Symbol;

public class Memento implements Serializable{
	
	final LinkedList<Symbol> state;
	
	public Memento(LinkedList<Symbol> state) {
		this.state = state;
//		int index=0;
//		for (Symbol symbol : state) {
//			this.state.set(index, symbol.clone());
//		}
//		System.out.println("Memento构造方法");
//		for (Symbol symbol : state) {
//			System.out.println(symbol);
//		}
//		System.out.println();
	}

	public LinkedList<Symbol> getState() {
		return state;
	}

}
