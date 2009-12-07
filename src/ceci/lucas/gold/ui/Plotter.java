package ceci.lucas.gold.ui;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import ceci.lucas.gold.Escalonamento;
import ceci.lucas.gold.Programa;
import ceci.lucas.gold.escalonador.Escalonador;

public class Plotter {

	private List<List<Programa>> todosProgramas;
	private Escalonador escalonador;

	private DefaultCategoryDataset dataset;
	private JFreeChart grafico;
	
	public Plotter(List<List<Programa>> todosProgramas) {
		this.todosProgramas = todosProgramas;
	}

	public void criaGrafico(String titulo){
		this.dataset = new DefaultCategoryDataset();
		this.grafico = 
			ChartFactory.createStackedBarChart("Escalonamento",
												"Dias", "Horarios", 
				dataset, PlotOrientation.VERTICAL, true, true, false);
	}
	
	public void plotaIndicador(Escalonador indicador){
		for (List<Programa> programasPeriodo : todosProgramas) {
			Escalonamento escalonamento = escalonador.escalona(programasPeriodo);
		}
		// Falta plotar...
	}
}
