package ceci.lucas.gold.escalonador;

import java.util.Collections;
import java.util.List;

import ceci.lucas.gold.Escalonamento;
import ceci.lucas.gold.Programa;

public class BestFitDecreasing implements Escalonador {

	public Escalonamento escalona(List<Programa> periodo) {
		Collections.sort(periodo, new OrdemPorPjDecrescente());
		return new BestFit().escalona(periodo);
	}

}
