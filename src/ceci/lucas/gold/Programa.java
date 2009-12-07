package ceci.lucas.gold;

public class Programa {

	private int pj;

	private int dj;

	private int rj;

	private int comerciais;

	private final int indice;
	private static int contagemGeralIndice = 0;

	private static int pegaIndice() {
		return contagemGeralIndice++;
	}
	
	public Programa(int pj) {
		this.indice = pegaIndice();
		this.pj = pj;
	}

	public int getComerciais() {
		return comerciais;
	}

	public void setComerciais(int comerciais) {
		this.comerciais = comerciais;
	}

	public int getPj() {
		return pj;
	}

	public void setPj(int pj) {
		this.pj = pj;
	}

	public int getDj() {
		return dj;
	}

	public void setDj(int dj) {
		this.dj = dj;
	}

	public int getRj() {
		return rj;
	}

	public void setRj(int rj) {
		this.rj = rj;
	}

	@Override
	public String toString() {
		return "[" + indice + "]";
	}

	public int getDuracao() {
		return pj + comerciais;
	}
}
