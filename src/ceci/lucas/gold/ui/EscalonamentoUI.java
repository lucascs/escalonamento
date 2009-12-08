package ceci.lucas.gold.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
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
import javax.swing.JTabbedPane;

import ceci.lucas.gold.Programa;
import ceci.lucas.gold.escalonador.ComComerciais;
import ceci.lucas.gold.escalonador.FirstFit;
import ceci.lucas.gold.leitor.LeitorEntrada;

public class EscalonamentoUI {

	private JFrame janelaPrincipal;
	private JPanel painelPrincipal;
	private Container painelBotoes;
	private JTabbedPane abas;

	public static void main(String[] args) {
		new EscalonamentoUI().montaTela();
	}

	public void montaTela() {
		montaJanelaPrincipal();
		montaPainelPrincipal();
		montaPainelBotoes();
		montaBotaoCarregar();
		montaBotaoSair();
		montaAbas();
		mostraTudo();
	}

	private void montaJanelaPrincipal() {
		janelaPrincipal = new JFrame("Escalonamento de programação televisiva");
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void montaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		janelaPrincipal.add(painelPrincipal);
	}

	private void montaPainelBotoes() {
		painelBotoes = new JPanel();
		painelBotoes.setLayout(new GridLayout());
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void montaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar Entrada");
		botaoCarregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser(".");
				int retorno = fileChooser.showOpenDialog(null);

				if (retorno == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						List<List<Programa>> todosProgramas = new LeitorEntrada().carrega(new FileReader(file));
						Plotter plotter = new Plotter(todosProgramas);
						plotter.plotaIndicador(new ComComerciais(new FirstFit()));
						plotter.criaGrafico("Escalonamento");
						JPanel panel = plotter.getPanel();
						abas.addTab("Escalonamento", panel);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
		painelBotoes.add(botaoCarregar);
	}

	private void montaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		painelBotoes.add(botaoSair);
	}

	private void montaAbas(){
		abas = new JTabbedPane();
		painelPrincipal.add(abas);
	}
	
	private void mostraTudo() {
		janelaPrincipal.pack();
		janelaPrincipal.setSize(700, 550);
		janelaPrincipal.setVisible(true);
	}
}
