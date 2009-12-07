package ceci.lucas.gold;

import java.util.Collections;
import java.util.List;

public class Escalonamento {

	private final List<List<Programa>> escalonamento;

	public Escalonamento(List<List<Programa>> escalonamento) {
		this.escalonamento = escalonamento;
	}
	
	public List<Programa> getDia(int dia){
		if(dia < escalonamento.size())
			return escalonamento.get(dia - 1);
		return Collections.emptyList();
	}
	
	public int numeroDias() {
		return escalonamento.size();
	}

}
