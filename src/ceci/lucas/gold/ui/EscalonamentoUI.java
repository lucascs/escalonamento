package ceci.lucas.gold.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ceci.lucas.gold.Programa;
import ceci.lucas.gold.leitor.LeitorEntrada;

public class EscalonamentoUI {

	private JFrame janelaPrincipal;
	private JPanel painelPrincipal;

	public void montaTela() {
		montaJanelaPrincipal();
		montaPainelPrincipal();
		montaBotaoCarregar();
		montaBotaoEscalonar();
		montaGrafico();
		mostraTudo();
	}

	private void montaJanelaPrincipal() {
		janelaPrincipal = new JFrame("Escalonamento de programação televisiva");
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void montaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janelaPrincipal.add(janelaPrincipal);
	}
	
	private void montaBotaoCarregar() {
		JButton botaoCarregar = new JButton();
		botaoCarregar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(".");
				File file = fileChooser.getSelectedFile();
				try {
					List<List<Programa>> todosProgramas = new LeitorEntrada().carrega(new FileReader(file));
					new Plotter(todosProgramas);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void montaBotaoEscalonar() {
		// TODO Auto-generated method stub
		
	}

	private void montaGrafico() {
		// TODO Auto-generated method stub
		
	}

	private void mostraTudo() {
		// TODO Auto-generated method stub
		
	}
}
