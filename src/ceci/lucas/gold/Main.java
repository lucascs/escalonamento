package ceci.lucas.gold;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	public static final int MAXIMO_DE_TEMPO = 12;

	private static int count = 0;

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		System.out.println("Digite o número de programas de manhã:");
		List<Programa> manha = leProgramas(teclado);

		System.out.println("Digite o número de programas de tarde:");
		List<Programa> tarde = leProgramas(teclado);

		System.out.println("Digite o número de programas de noite:");
		List<Programa> noite = leProgramas(teclado);

		Escalonador escalonador = new ComComerciais(new SolucaoIngenua());

		List<List<Programa>> escalonadoManha = escalonador.escalona(manha);
		List<List<Programa>> escalonadoTarde = escalonador.escalona(tarde);
		List<List<Programa>> escalonadoNoite = escalonador.escalona(noite);

		int maior = maior(escalonadoManha, escalonadoTarde, escalonadoNoite);
		System.out.println("Estratégia: " + escalonador);
		for (int i = 0; i < maior; i++) {
			System.out.println("Dia " + (i + 1) + ":");
			System.out.print("Manha ");
			imprime(escalonadoManha, i);
			System.out.print(" Tarde ");
			imprime(escalonadoTarde, i);
			System.out.print(" Noite ");
			imprime(escalonadoNoite, i);
			System.out.println();
			System.out.append('[');
			grafico(escalonadoManha, i);
			System.out.append('|');
			grafico(escalonadoTarde, i);
			System.out.append('|');
			grafico(escalonadoNoite, i);
			System.out.println("]");
		}
		System.out.println("Legenda: ");
		System.out.println("x -> parte de um programa");
		System.out.println("c -> intervalo comercial");
		System.out.println("p -> comercial da polishop");
	}

	private static void grafico(List<List<Programa>> escalonado, int i) {
		int restantes = MAXIMO_DE_TEMPO;
		if (escalonado.size() > i) {
			for (Programa programa : escalonado.get(i)) {
				repete('x', programa.getPj());
				repete('c', programa.getComerciais());
				restantes -= programa.getDuracao();
			}
		}
		repete('p', restantes);
	}



	private static void repete(char c, int pj) {
		for (int i = 0; i < pj; i++) {
			System.out.append(c);
		}
	}

	private static void imprime(List<List<Programa>> escalonado, int dia) {
		if (escalonado.size() > dia) {
			System.out.print(escalonado.get(dia));
		} else {
			System.out.print("[]");
		}
	}

	private static int maior(List<List<Programa>> escalonadoManha, List<List<Programa>> escalonadoTarde,
			List<List<Programa>> escalonadoNoite) {
		return Math.max(Math.max(escalonadoManha.size(), escalonadoTarde.size()), escalonadoNoite.size());
	}

	private static List<Programa> leProgramas(Scanner teclado) {
		List<Programa> programas = new ArrayList<Programa>();
		int n = teclado.nextInt();
		for (int i = 0; i < n; i++) {
			programas.add(new Programa(count++));
		}

		System.out.println("Digite os pj's (unidades de meia hora):");
		for (int i = 0; i < n; i++) {
			programas.get(i).setPj(teclado.nextInt());
		}
		return programas;
	}
}
