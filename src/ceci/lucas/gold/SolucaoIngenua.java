package ceci.lucas.gold;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * tenta encaixar o máximo de programas possível dentro de um período
 */
public class SolucaoIngenua implements Escalonador {

	public List<List<Programa>> escalona(List<Programa> periodo) {
		List<List<Programa>> escalonado = new ArrayList<List<Programa>>();
		List<Programa> todos = new ArrayList<Programa>(periodo);

		while(!todos.isEmpty()) {
			int restante = Main.MAXIMO_DE_TEMPO;
			List<Programa> dia = new ArrayList<Programa>();
			Iterator<Programa> iterator = todos.iterator();
			while(iterator.hasNext() && restante > 0) {
				Programa programa = iterator.next();
				if (programa.getPj() <= restante) {
					iterator.remove();
					restante -= programa.getPj();
					dia.add(programa);
				}
			}
			escalonado.add(dia);
		}
		return escalonado;
	}

	@Override
	public String toString() {
		return "tenta escalonar o máximo de programas dentro de um dia";
	}
}
