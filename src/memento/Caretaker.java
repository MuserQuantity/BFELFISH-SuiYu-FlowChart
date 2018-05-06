package memento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import symbols.Symbol;

public class Caretaker implements Serializable{
	public ArrayList<Memento> states;
	public int currentState = 0;

	public Caretaker() {
		states = new ArrayList<Memento>(100);
		states.add(new Memento(new LinkedList<Symbol>()));
	}

	public void add(Memento state) {
		currentState++;
		for (int i = states.size() - 1; i >= currentState; i--) {
			states.remove(i);
		}
		states.add(state);
//		System.out.println("add方法");
//		for(int i=0;i<=currentState;i++) {
//			System.out.println("currentState="+i);
//			System.out.println(states.get(i).getState());
//		}
//		System.out.println();
	}

	public LinkedList<Symbol> getLast(){
		return states.get(currentState).getState();
	}
	public LinkedList<Symbol> getUndo() {
		states.get(0).getState().clear();
		if (currentState > 0) {
			currentState--;
			System.out.println("getUndo方法");
			System.out.println("currentState:"+currentState);
			System.out.println(states.get(currentState).getState());
			return states.get(currentState).getState();
		} else {
			return states.get(0).getState();
		}

	}

	public LinkedList<Symbol> getRedo() {
		if (currentState < states.size() - 1) {
			currentState++;
			return states.get(currentState).getState();
		} else {
			return states.get(states.size() - 1).getState();
		}
	}
}
