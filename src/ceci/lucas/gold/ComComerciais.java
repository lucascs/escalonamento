package ceci.lucas.gold;

import java.util.Iterator;
import java.util.List;

/**
 * Pelo menos um comercial depois do programa
 *
 */
public class ComComerciais implements Escalonador {

	private final Escalonador escalonador;

	public ComComerciais(Escalonador escalonador) {
		this.escalonador = escalonador;
	}

	public List<List<Programa>> escalona(List<Programa> periodo) {
		// aumenta a duração de todos os programas para ter pelo menos um comercial
		for (Programa programa : periodo) {
			programa.setPj(programa.getPj() + 1);
		}

		List<List<Programa>> resultado = escalonador.escalona(periodo);

		for (Programa programa : periodo) {
			programa.setPj(programa.getPj() - 1);
		}

		for (List<Programa> dia : resultado) {
			int restante = calculaTempoRestante(dia);

			int duracaoComercial = restante/dia.size(); //arredondado pra baixo

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
				restante --;
			}

		}
		return resultado;
	}

	private int calculaTempoRestante(List<Programa> dia) {
		int restante = Main.MAXIMO_DE_TEMPO;
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
