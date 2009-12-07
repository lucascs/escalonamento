package ceci.lucas.gold.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.data.KeyToGroupMap;
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
		List<Escalonamento> periodos = new ArrayList<Escalonamento>();
		for (List<Programa> programasPeriodo : todosProgramas) {
			periodos.add(escalonador.escalona(programasPeriodo));
		}
		
		KeyToGroupMap map = new KeyToGroupMap();
		for (Escalonamento periodo : periodos) {
			for(int i = 0; i < periodo.numeroDias(); i++) {
				List<Programa> dia = periodo.getDia(i);
				for (Programa programa : dia) {
					dataset.addValue(programa.getDuracao(), "linha " + i, Integer.valueOf(programa.getInicio()));
					
				}
				map.mapKeyToGroup("linha " + i,	i);
			}
		}

		GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
		renderer.setSeriesToGroupMap(map);
		
		// Falta separar programas por cores...
	}

	public JPanel getPanel(){
		return new ChartPanel(this.grafico);
	}
}
