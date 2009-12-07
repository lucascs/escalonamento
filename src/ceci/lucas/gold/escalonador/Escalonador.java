package ceci.lucas.gold.escalonador;

import java.util.List;

import ceci.lucas.gold.Escalonamento;
import ceci.lucas.gold.Programa;


public interface Escalonador {

	Escalonamento escalona(List<Programa> periodo);

}
