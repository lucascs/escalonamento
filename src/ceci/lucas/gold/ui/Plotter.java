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
import ceci.lucas.gold.VariaveisMagicas;
import ceci.lucas.gold.escalonador.Escalonador;

public class Plotter {

	private List<List<Programa>> todosProgramas;

	private DefaultCategoryDataset dataset;
	private JFreeChart grafico;

	private GroupedStackedBarRenderer renderer;
	
	public Plotter(List<List<Programa>> todosProgramas) {
		this.todosProgramas = todosProgramas;
	}

	public void criaGrafico(String titulo){
		this.grafico = ChartFactory.createStackedBarChart(titulo,
												"Dias", "Horarios", 
				dataset, PlotOrientation.HORIZONTAL, false, true, true);
	}
	
	public void plotaIndicador(Escalonador escalonador){
		this.dataset = new DefaultCategoryDataset();
		List<Escalonamento> periodos = new ArrayList<Escalonamento>();
		for (List<Programa> programasPeriodo : todosProgramas) {
			periodos.add(escalonador.escalona(programasPeriodo));
		}
		
		KeyToGroupMap map = new KeyToGroupMap();
		
		int maiorPeriodo = 0;
		for (Escalonamento periodo : periodos) {
			if(periodo.numeroDias() > maiorPeriodo)
				maiorPeriodo = periodo.numeroDias();
		}
		for (Escalonamento periodo : periodos) {
			int i = 0;
			for(i = 0; i < periodo.numeroDias(); i++) {
				List<Programa> dia = periodo.getDia(i);
				for (Programa programa : dia) {
					dataset.addValue(programa.getPj(), "Programa " + programa.getIndice(), Integer.valueOf(i));
					dataset.addValue(programa.getComerciais(), "Comerciais " + programa.getIndice(), Integer.valueOf(i));
					map.mapKeyToGroup(programa.toString(),	i);
				}
				
			}
			while(maiorPeriodo > i) {
				double random = Math.random();
				dataset.addValue(VariaveisMagicas.MAXIMO_DE_TEMPO, "Polishop " + random, Integer.valueOf(i));
				map.mapKeyToGroup("Polishop " + random, i++);
			}
		}

		renderer = new GroupedStackedBarRenderer();
		renderer.setSeriesToGroupMap(map);
		
		
	}

	public JPanel getPanel(){
		return new ChartPanel(this.grafico);
	}
}
