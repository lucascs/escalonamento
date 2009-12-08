package ceci.lucas.gold.escalonador;

import java.util.ArrayList;
import java.util.List;

import ceci.lucas.gold.Escalonamento;
import ceci.lucas.gold.Programa;
import ceci.lucas.gold.VariaveisMagicas;

/**
 * encaixa no primeiro que sobra o mínimo de tempo
 */
public class BestFit implements Escalonador {

	public Escalonamento escalona(List<Programa> periodo) {
		List<List<Programa>> escalonado = new ArrayList<List<Programa>>();
		List<Integer> temposRestantes = new ArrayList<Integer>();

		for (Programa next : periodo) {
			int minimo = VariaveisMagicas.MAXIMO_DE_TEMPO;
			List<Programa> melhor = null;
			int melhorIndice = 0;
			for (int i = 0; i < escalonado.size(); i++) {
				Integer restante = temposRestantes.get(i);
				if (restante >= next.getPj() && restante - next.getPj() < minimo) {
					melhor = escalonado.get(i);
					melhorIndice = i;
					minimo = restante - next.getPj();
				}
			}
			if (melhor == null) {
				melhor = new ArrayList<Programa>();
				escalonado.add(melhor);
				melhorIndice = temposRestantes.size();
				temposRestantes.add(VariaveisMagicas.MAXIMO_DE_TEMPO);
			}
			melhor.add(next);
			temposRestantes.set(melhorIndice, temposRestantes.get(melhorIndice) - next.getPj());
		}
		return new Escalonamento(escalonado);
	}

	@Override
	public String toString() {
		return "tenta escalonar o máximo de programas dentro de um dia";
	}
}
