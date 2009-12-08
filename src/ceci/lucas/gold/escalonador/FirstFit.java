package ceci.lucas.gold.escalonador;

import java.util.ArrayList;
import java.util.List;

import ceci.lucas.gold.Escalonamento;
import ceci.lucas.gold.Programa;
import ceci.lucas.gold.VariaveisMagicas;

/**
 * encaixa no primeiro período possível
 */
public class FirstFit implements Escalonador {

	public Escalonamento escalona(List<Programa> periodo) {
		List<List<Programa>> escalonado = new ArrayList<List<Programa>>();
		List<Integer> temposRestantes = new ArrayList<Integer>();

		OUTER: for (Programa next : periodo) {
			for (int i = 0; i < escalonado.size(); i++) {
				if (temposRestantes.get(i) >= next.getPj()) {
					escalonado.get(i).add(next);
					temposRestantes.set(i, temposRestantes.get(i) - next.getPj());
					continue OUTER;
				}
			}
			List<Programa> novo = new ArrayList<Programa>();
			novo.add(next);
			escalonado.add(novo);
			temposRestantes.add(VariaveisMagicas.MAXIMO_DE_TEMPO - next.getPj());
		}
		return new Escalonamento(escalonado);
	}

	@Override
	public String toString() {
		return "tenta escalonar o máximo de programas dentro de um dia";
	}
}
