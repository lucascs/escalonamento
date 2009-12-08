package ceci.lucas.gold.escalonador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ceci.lucas.gold.Escalonamento;
import ceci.lucas.gold.Programa;
import ceci.lucas.gold.VariaveisMagicas;

/**
 * tenta encaixar o máximo de programas possível dentro de um período
 */
public class NextFit implements Escalonador {

	public Escalonamento escalona(List<Programa> periodo) {
		List<List<Programa>> escalonado = new ArrayList<List<Programa>>();
		List<Programa> todos = new ArrayList<Programa>(periodo);

		while (!todos.isEmpty()) {
			int restante = VariaveisMagicas.MAXIMO_DE_TEMPO;
			List<Programa> dia = new ArrayList<Programa>();
			Iterator<Programa> iterator = todos.iterator();
			while (iterator.hasNext() && restante > 0) {
				Programa programa = iterator.next();
				if (programa.getPj() <= restante) {
					iterator.remove();
					restante -= programa.getPj();
					dia.add(programa);
				}
			}
			escalonado.add(dia);
		}
		return new Escalonamento(escalonado);
	}

	@Override
	public String toString() {
		return "tenta escalonar o máximo de programas dentro de um dia";
	}
}
