package ceci.lucas.gold;

import java.io.PrintStream;
import java.util.Random;

public class Gerador {

	public static void main(String[] args) throws Exception {
		Random random = new Random();
		PrintStream stream = new PrintStream("teste2.txt");

		for(int i = 0; i < 3; i++) {
			int n = random.nextInt(15) + 15;
			stream.println(n);
			for (int j = 0; j < n; j++) {
				stream.print(random.nextInt(VariaveisMagicas.MAXIMO_DE_TEMPO - 2) + 1);
				stream.print(" ");
			}
			stream.println();
		}

		stream.close();
	}
}
