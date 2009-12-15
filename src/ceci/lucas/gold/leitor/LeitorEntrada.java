package ceci.lucas.gold.leitor;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ceci.lucas.gold.Programa;

public class LeitorEntrada {

	@SuppressWarnings("unchecked")
	public List<List<Programa>> carrega(FileReader leitor) {
		Scanner entrada = new Scanner(leitor);
//		System.out.println("Digite o número de programas de manhã:");
		List<Programa> manha = leProgramas(entrada);

//		System.out.println("Digite o número de programas de tarde:");
		List<Programa> tarde = leProgramas(entrada);

//		System.out.println("Digite o número de programas de noite:");
		List<Programa> noite = leProgramas(entrada);
		
		return Arrays.asList(manha, tarde, noite);
	}

	private List<Programa> leProgramas(Scanner entrada) {
		List<Programa> programas = new ArrayList<Programa>();
		int programasNoPeriodo = entrada.nextInt();
		for (int i = 0; i < programasNoPeriodo; i++) {
			int pj = entrada.nextInt();
			programas.add(new Programa(pj));
		}

		return programas;
	}

}
