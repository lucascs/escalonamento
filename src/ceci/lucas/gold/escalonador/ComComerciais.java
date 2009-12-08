package ceci.lucas.gold.escalonador;

import java.util.Iterator;
import java.util.List;

import ceci.lucas.gold.Escalonamento;
import ceci.lucas.gold.Programa;
import ceci.lucas.gold.VariaveisMagicas;

/**
 * Pelo menos um comercial depois do programa
 * 
 */
public class ComComerciais implements Escalonador {

	private final Escalonador escalonador;

	public ComComerciais(Escalonador escalonador) {
		this.escalonador = escalonador;
	}

	public Escalonamento escalona(List<Programa> periodo) {
		// aumenta a duração de todos os programas para ter pelo menos um
		// comercial
		for (Programa programa : periodo) {
			programa.setPj(programa.getPj() + 1);
		}

		Escalonamento resultado = escalonador.escalona(periodo);

		for (Programa programa : periodo) {
			programa.setPj(programa.getPj() - 1);
		}

		for(int i = 0; i < resultado.numeroDias(); i++) {
			List<Programa> dia = resultado.getDia(i);
			int restante = calculaTempoRestante(dia);

			int duracaoComercial = restante / dia.size(); // arredondado pra
															// baixo

			if (duracaoComercial > 0) {
				for (Programa programa : dia) {
					programa.setComerciais(duracaoComercial);
				}
			}

			// como a duração foi arredondada pra baixo, dividimos o que sobrou
			// nos primeiros programas
			restante -= duracaoComercial * dia.size();

			Iterator<Programa> iterator = dia.iterator();
			while (restante > 0 && iterator.hasNext()) {
				iterator.next().setComerciais(1);
				restante--;
			}
			if(restante != 0) System.out.println("Restante = " + restante);
			dia.get(0).setComerciais(dia.get(0).getComerciais() + restante);
		}
		return resultado;
	}

	private int calculaTempoRestante(List<Programa> dia) {
		int restante = VariaveisMagicas.MAXIMO_DE_TEMPO;
		for (Programa programa : dia) {
			restante -= programa.getDuracao();
		}
		return restante;
	}

	@Override
	public String toString() {
		return escalonador.toString() + " e adiciona pelo menos um comercial entre os programas";
	}

}
