/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorRegistroAcessoNegado;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Motivo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.RegistroAcessoNegado;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thiagobrezinski
 */
public class TelaRegistroAcessoNegado extends JFrame {

    private ControladorRegistroAcessoNegado owner;
	private GridBagConstraints constraints;
	private JScrollPane spBaseTabela;
	private JTable tbItens;
	private JButton btVoltar;
	private JButton btFiltrar;
	private JComboBox comboFiltroMotivo;
	private JTextField tfFiltroMatricula;
	private JLabel lbFiltroMotivo;
	private JLabel lbFiltroMatricula;
	private List<Integer> matriculas;
	private JPanel painelFiltroMotivo;
	private JPanel painelFiltroMatricula;
	private JPanel painelBotoes;
	

    public TelaRegistroAcessoNegado(ControladorRegistroAcessoNegado owner) {
		super(Constantes.REGISTRO_TITULO);
        this.owner = owner;
		configuraTela();
    }

	private void configuraTela() {
		
		//Configuracao container
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());

		//Configuracao constraints
		this.constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 15;
        constraints.gridheight = 15;
        constraints.gridx = 0;
        constraints.gridy = 0;
		
		//Configuracao tbItens
		this.tbItens = new JTable();
		tbItens.setAutoCreateRowSorter(true);
		tbItens.setPreferredScrollableViewportSize(new Dimension(500, 300));
		tbItens.setFillsViewportHeight(true);
		spBaseTabela = new JScrollPane(tbItens);
		container.add(spBaseTabela, constraints);
		
		//Configuracao btManager
		GerenciadorBotoes btManager = new GerenciadorBotoes();
		
		//Configuracao lbFiltroMotivo
		lbFiltroMotivo = new JLabel(Constantes.REGISTRO_LABEL_FILTRO_MOTIVO);
		
		//Configuracao comboFiltroMotivo
		Object[] motivos = {
			Constantes.REGISTRO_FILTRO_TODOS,
			Motivo.MATRICULA_INEXISTENTE,
			Motivo.HORARIO_NAO_PERMITIDO,
			Motivo.CARGO_SEM_ACESSO,
			Motivo.ACESSO_BLOQUEADO
		};
		comboFiltroMotivo = new JComboBox(motivos);
		
		//Configuracao lbFiltroMatricula
		lbFiltroMatricula = new JLabel(Constantes.REGISTRO_LABEL_FILTRO_MATRICULA);
		
		//Configuracao tfFiltroMatricula
		tfFiltroMatricula = new JTextField(10);
		
		//Configuracao btFiltrar
		btFiltrar = new JButton(Constantes.REGISTRO_BOTAO_FILTRAR);
		btFiltrar.addActionListener(btManager);
		
		//Configuracao btVoltar
		btVoltar = new JButton(Constantes.COMUM_BOTAO_VOLTAR);
		btVoltar.addActionListener(btManager);
		
		//Configuracao painelFiltroMotivo
        this.painelFiltroMotivo = new JPanel();
        painelFiltroMotivo.setLayout(new BoxLayout(painelFiltroMotivo, BoxLayout.LINE_AXIS));
        this.painelFiltroMotivo.setVisible(true);
		painelFiltroMotivo.add(lbFiltroMotivo);
		painelFiltroMotivo.add(comboFiltroMotivo);
		constraints.gridy = 20;
        container.add(painelFiltroMotivo, constraints);

		//Configuracao painelFiltroMatricula
        this.painelFiltroMatricula = new JPanel();
        painelFiltroMatricula.setLayout(new BoxLayout(painelFiltroMatricula, BoxLayout.LINE_AXIS));
        this.painelFiltroMatricula.setVisible(true);
		painelFiltroMatricula.add(lbFiltroMatricula);
		painelFiltroMatricula.add(tfFiltroMatricula);
		constraints.gridy = 40;
        container.add(painelFiltroMatricula, constraints);
		
		//Configuracao painelBotoes
		this.painelBotoes = new JPanel();
		painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
		this.painelBotoes.setVisible(true);
		painelBotoes.add(btFiltrar);
        painelBotoes.add(btVoltar);
		constraints.gridy = 60;
		container.add(painelBotoes, constraints);
		
		setSize(600, 500);
		setLocationRelativeTo(null);
	}
	
	public void updateData(String filtroMotivo, String filtroMatricula) {
		
		//Configuracao modelTbItens
		DefaultTableModel modelTbItens = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelTbItens.addColumn(Constantes.COMUM_DATA);
		modelTbItens.addColumn(Constantes.COMUM_MATRICULA);
		modelTbItens.addColumn(Constantes.REGISTRO_MOTIVO);
		
		Collection<RegistroAcessoNegado> listaRegistros = ControladorRegistroAcessoNegado
				.getInstance()
				.filtraRegistros(filtroMotivo, filtroMatricula);
		
		for(RegistroAcessoNegado registro : listaRegistros) {
			modelTbItens.addRow(new Object[]{
				registro.getData(),
				registro.getMatricula(),
				registro.getMotivo()
			});
		}
		tbItens.setModel(modelTbItens);
		
		this.repaint();
	}
	
    /**
     * Exibe o menu de escolha do filtro para emissao do relatorio e trata se o
     * input recebido eh realmente um inteiro.
     *
     * @return int opcao escolhida pelo usuario
     */
    public void exibeMenuRelatorio() {
		updateData(Constantes.REGISTRO_FILTRO_TODOS, Constantes.REGISTRO_FILTRO_VAZIO);
		setVisible(true);
    }

	public void exibeFiltroMatriculaInvalido() {
		JOptionPane.showMessageDialog(
			null,
			Constantes.REGISTRO_FILTRO_MATRICULA_INVALIDA,
			Constantes.REGISTRO_FILTRO_INVALIDO,
			JOptionPane.PLAIN_MESSAGE
		);
	}

	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btVoltar)) {
				setVisible(false);
			} else if(e.getSource().equals(btFiltrar)) {
				if(ControladorRegistroAcessoNegado.getInstance().verificaFiltroMatricula(tfFiltroMatricula.getText())) {
					updateData(
						comboFiltroMotivo.getSelectedItem().toString(),
						tfFiltroMatricula.getText()
					);
				} else {
					exibeFiltroMatriculaInvalido();
				}
			}
		}
		
	}
	
}
