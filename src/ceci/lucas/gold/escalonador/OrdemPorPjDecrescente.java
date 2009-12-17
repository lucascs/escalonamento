package ceci.lucas.gold.escalonador;

import java.util.Comparator;

import ceci.lucas.gold.Programa;

public class OrdemPorPjDecrescente implements Comparator<Programa>{

	public int compare(Programa o1, Programa o2) {
		return o2.getPj() - o1.getPj();
	}

}
