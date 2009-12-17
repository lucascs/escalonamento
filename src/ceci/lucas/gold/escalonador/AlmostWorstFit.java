package ceci.lucas.gold.escalonador;

import java.util.ArrayList;
import java.util.List;

import ceci.lucas.gold.Escalonamento;
import ceci.lucas.gold.Programa;
import ceci.lucas.gold.VariaveisMagicas;

/**
 * encaixa no primeiro que sobra o mínimo de tempo
 */
public class AlmostWorstFit implements Escalonador {

	public Escalonamento escalona(List<Programa> periodo) {
		List<List<Programa>> escalonado = new ArrayList<List<Programa>>();
		List<Integer> temposRestantes = new ArrayList<Integer>();

		for (Programa next : periodo) {
			int maximo = -1;
			List<Programa> melhor = null;
			List<Programa> segundoMelhor = null;
			int melhorIndice = 0;
			int segundoMelhorIndice = 0;
			for (int i = 0; i < escalonado.size(); i++) {
				Integer restante = temposRestantes.get(i);
				if (restante >= next.getPj() && restante - next.getPj() > maximo) {
					segundoMelhor = melhor;
					segundoMelhorIndice = melhorIndice;
					melhor = escalonado.get(i);
					melhorIndice = i;
					maximo = restante - next.getPj();
				}
			}
			if (segundoMelhor == null && melhor != null) {
				segundoMelhor = melhor;
				segundoMelhorIndice = melhorIndice;
			} else if (segundoMelhor == null) {
				segundoMelhor = new ArrayList<Programa>();
				escalonado.add(segundoMelhor);
				segundoMelhorIndice = temposRestantes.size();
				temposRestantes.add(VariaveisMagicas.MAXIMO_DE_TEMPO);
			}
			segundoMelhor.add(next);
			temposRestantes.set(segundoMelhorIndice, temposRestantes.get(segundoMelhorIndice) - next.getPj());
		}
		return new Escalonamento(escalonado);
	}

	@Override
	public String toString() {
		return "tenta escalonar o máximo de programas dentro de um dia";
	}
}
