package ceci.lucas.gold;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ceci.lucas.gold.escalonador.ComComerciais;
import ceci.lucas.gold.escalonador.Escalonador;
import ceci.lucas.gold.escalonador.SolucaoIngenua;

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

		Escalonamento escalonadoManha = escalonador.escalona(manha);
		Escalonamento escalonadoTarde = escalonador.escalona(tarde);
		Escalonamento escalonadoNoite = escalonador.escalona(noite);

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

	private static void grafico(Escalonamento escalonado, int i) {
		int restantes = MAXIMO_DE_TEMPO;
		if (escalonado.numeroDias() > i) {
			for (Programa programa : escalonado.getDia(i)) {
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

	private static void imprime(Escalonamento escalonado, int dia) {
		if (escalonado.numeroDias() > dia) {
			System.out.print(escalonado.getDia(dia));
		} else {
			System.out.print("[]");
		}
	}

	private static int maior(Escalonamento escalonadoManha, Escalonamento escalonadoTarde,
			Escalonamento escalonadoNoite) {
		return Math.max(Math.max(escalonadoManha.numeroDias(), escalonadoTarde.numeroDias()), escalonadoNoite.numeroDias());
	}

	private static List<Programa> leProgramas(Scanner teclado) {
		List<Programa> programas = new ArrayList<Programa>();
		int n = teclado.nextInt();
		System.out.println("Digite os pj's (unidades de meia hora):");
		for (int i = 0; i < n; i++) {
			int pj = teclado.nextInt();
			programas.add(new Programa(pj));
		}
		return programas;
	}
}
