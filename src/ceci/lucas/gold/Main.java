package ceci.lucas.gold;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.output.NullOutputStream;

import ceci.lucas.gold.escalonador.BestFit;
import ceci.lucas.gold.escalonador.ComComerciais;
import ceci.lucas.gold.escalonador.Escalonador;
import ceci.lucas.gold.escalonador.FirstFit;
import ceci.lucas.gold.escalonador.NextFit;
import ceci.lucas.gold.escalonador.WorstFit;

public class Main {


	public static void main(String[] args) throws IOException {
		InputStream entrada = System.in;
		PrintStream dicas = System.out;
		PrintStream saida = System.out;

		if (args.length > 0) {
			entrada = new FileInputStream(args[0]);
			dicas = new PrintStream(new NullOutputStream());
		}
		if (args.length > 1) {
			saida = new PrintStream(args[1]);
		}


		Scanner teclado = new Scanner(entrada);

		dicas.println("Digite o número de programas de manhã:");
		List<Programa> manha = leProgramas(teclado, dicas);

		dicas.println("Digite o número de programas de tarde:");
		List<Programa> tarde = leProgramas(teclado, dicas);

		dicas.println("Digite o número de programas de noite:");
		List<Programa> noite = leProgramas(teclado, dicas);


		for (Escalonador heuristica : Arrays.asList(new NextFit(), new BestFit(), new WorstFit(), new FirstFit())) {
			Escalonador escalonador = new ComComerciais(heuristica);
			Escalonamento escalonadoManha = escalonador.escalona(manha);
			Escalonamento escalonadoTarde = escalonador.escalona(tarde);
			Escalonamento escalonadoNoite = escalonador.escalona(noite);

			int maior = maior(escalonadoManha, escalonadoTarde, escalonadoNoite);
			saida.println("Estratégia: " + escalonador);
			for (int i = 0; i < maior; i++) {
				saida.println("Dia " + (i + 1) + ":");
				saida.print("Manha ");
				imprime(escalonadoManha, i, saida);
				saida.print(" Tarde ");
				imprime(escalonadoTarde, i, saida);
				saida.print(" Noite ");
				imprime(escalonadoNoite, i, saida);
				saida.println();
				saida.append('[');
				grafico(escalonadoManha, i, saida);
				saida.append('|');
				grafico(escalonadoTarde, i, saida);
				saida.append('|');
				grafico(escalonadoNoite, i, saida);
				saida.println("]");
			}
		}
		saida.println("Legenda: ");
		saida.println("x -> parte de um programa");
		saida.println("c -> intervalo comercial");
		saida.println("p -> comercial da polishop");
	}

	private static void grafico(Escalonamento escalonado, int i, PrintStream saida) {
		int restantes = VariaveisMagicas.MAXIMO_DE_TEMPO;
		if (escalonado.numeroDias() > i) {
			for (Programa programa : escalonado.getDia(i)) {
				repete('x', programa.getPj(), saida);
				repete('c', programa.getComerciais(), saida);
				restantes -= programa.getDuracao();
			}
		}
		repete('p', restantes, saida);
	}

	private static void repete(char c, int pj, PrintStream saida) {
		for (int i = 0; i < pj; i++) {
			saida.append(c);
		}
	}

	private static void imprime(Escalonamento escalonado, int dia, PrintStream saida) {
		if (escalonado.numeroDias() > dia) {
			saida.print(escalonado.getDia(dia));
		} else {
			saida.print("[]");
		}
	}

	private static int maior(Escalonamento escalonadoManha, Escalonamento escalonadoTarde,
			Escalonamento escalonadoNoite) {
		return Math.max(Math.max(escalonadoManha.numeroDias(), escalonadoTarde.numeroDias()), escalonadoNoite.numeroDias());
	}

	private static List<Programa> leProgramas(Scanner teclado, PrintStream saida) {
		List<Programa> programas = new ArrayList<Programa>();
		int n = teclado.nextInt();
		saida.println("Digite os pj's (unidades de meia hora):");
		for (int i = 0; i < n; i++) {
			int pj = teclado.nextInt();
			programas.add(new Programa(pj));
		}
		return programas;
	}
}
