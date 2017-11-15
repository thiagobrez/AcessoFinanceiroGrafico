/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorPrincipal;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
//	private DefaultTableModel model;
//	private TableRowSorter<DefaultTableModel> sorter;
	private JButton btVoltar;
	private JComboBox comboFiltroMotivo;
	private JComboBox comboFiltroMatricula;
	private List<Integer> matriculas;
	

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
		constraints.gridwidth = 4;
		constraints.gridheight = 4;
//		constraints.gridx = 2;
		constraints.gridy = 1;
		
		//Configuracao tbItens
		this.tbItens = new JTable();
		tbItens.setAutoCreateRowSorter(true);
		tbItens.setPreferredScrollableViewportSize(new Dimension(500, 300));
		tbItens.setFillsViewportHeight(true);
		spBaseTabela = new JScrollPane(tbItens);
		container.add(spBaseTabela, constraints);
		
		//Configuracao btManager e comboManager
		GerenciadorBotoes btManager = new GerenciadorBotoes();
		GerenciadorCombos comboManager = new GerenciadorCombos();
		
		//Configuracao btVoltar
		btVoltar = new JButton(Constantes.COMUM_BOTAO_VOLTAR);
		btVoltar.addActionListener(btManager);
		container.add(btVoltar);
		
		//Configuracao comboFiltroMotivo
		Enum[] motivos = {
			Motivo.MATRICULA_INEXISTENTE,
			Motivo.HORARIO_NAO_PERMITIDO,
			Motivo.CARGO_SEM_ACESSO,
			Motivo.ACESSO_BLOQUEADO
		};
		comboFiltroMotivo = new JComboBox(motivos);
		comboFiltroMotivo.addActionListener(comboManager);
		container.add(comboFiltroMotivo);
		
		//Configuracao comboFiltroMatricula
		this.matriculas = new ArrayList<>();
		comboFiltroMatricula = new JComboBox(matriculas.toArray());
		comboFiltroMatricula.addActionListener(comboManager);
		container.add(comboFiltroMatricula);
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		
	}
	
	public void updateData() {
		
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
		
		Collection<RegistroAcessoNegado> listaRegistros = ControladorPrincipal.getInstance().getListaRegistrosAcessosNegados();
		
		for(RegistroAcessoNegado registro : listaRegistros) {
			modelTbItens.addRow(new Object[]{
				registro.getData(),
				registro.getMatricula(),
				registro.getMotivo()
			});
		}
		tbItens.setModel(modelTbItens);
		
		//Configuracao comboFiltroMatricula
		this.matriculas = ControladorPrincipal.getInstance().getMatriculas();
		comboFiltroMatricula.removeAllItems();
		for(int matricula : this.matriculas) {
			comboFiltroMatricula.addItem(matricula);
		}
		
		this.repaint();
	}
	
    /**
     * Exibe o menu de escolha do filtro para emissao do relatorio e trata se o
     * input recebido eh realmente um inteiro.
     *
     * @return int opcao escolhida pelo usuario
     */
    public void exibeMenuRelatorio() {
		updateData();
		setVisible(true);
    }

    /**
     * Exibe o menu com as opcoes de filtro da emissao do relatorio por motivos
     * existentes e trata se o input recebido eh realmente um inteiro.
     *
     * @return int opcao escolhida pelo usuario
     */
    public int exibeFiltroPorMotivo() {
		return 0;
//        int opcao = 0;
//        boolean opcaoInvalida = true;
//        while (opcaoInvalida) {
//            try {
//                System.out.println();
//                System.out.println(Constantes.RELATORIO_FILTRO_ESCOLHA_MOTIVO);
//                System.out.println(Constantes.RELATORIO_FILTRO_MOTIVO_MATRICULA_INEXISTENTE);
//                System.out.println(Constantes.RELATORIO_FILTRO_MOTIVO_CARGO_SEM_ACESSO);
//                System.out.println(Constantes.RELATORIO_FILTRO_MOTIVO_HORARIO_NAO_PERMITIDO);
//                System.out.println(Constantes.RELATORIO_FILTRO_MOTIVO_ACESSO_BLOQUEADO);
//                System.out.println(Constantes.VOLTAR_5);
//                System.out.println();
//                System.out.println(Constantes.O_QUE_DESEJA_FAZER);
//                opcao = teclado.nextInt();
//                opcaoInvalida = false;
//            } catch (InputMismatchException e) {
//                teclado.next();
//                System.out.println();
//                System.out.println(Constantes.OPCAO_INVALIDA);
//                System.out.println();
//            }
//        }
//        return opcao;
    }

    /**
     * Exibe o input para insercao da matricula a ser utilizada para filtrar a
     * emissao do relatorio e trata se a matricula inserida eh realmente um
     * inteiro.
     *
     * @return int matricula inserida pelo usuario.
     */
    public int exibeFiltroPorMatricula() {
		return 0;
//        int matricula = 0;
//        boolean matriculaInvalida = true;
//        while (matriculaInvalida) {
//            try {
//                System.out.println();
//                System.out.println(Constantes.INSIRA_MATRICULA);
//                System.out.println();
//                matricula = teclado.nextInt();
//                matriculaInvalida = false;
//            } catch (InputMismatchException e) {
//                teclado.next();
//                System.out.println();
//                System.out.println(Constantes.MATRICULA_INVALIDA);
//                System.out.println();
//            }
//        }
//        return matricula;
    }

    /**
     * Exibe o relatorio de registros de acesso negado pelo motivo filtrado.
     *
     * @param registrosEncontrados colecao de registros encontrados pelo motivo
     * @param encontrouRegistro recebe true se a colecao registrosEncontrados
     * nao estiver vazia e false se estiver
     * @param motivo motivo pelo qual o relatorio a ser exibido foi filtrado
     * @return int opcao utilizada para voltar ao menu principal
     */
    public void exibeRelatorioPorMotivo(ArrayList<RegistroAcessoNegado> registrosEncontrados, boolean encontrouRegistro, Motivo motivo) {
        if (!encontrouRegistro) {
            System.out.println();
            System.out.println(Constantes.RELATORIO_REGISTRO_NENHUM_ENCONTRADO);
        } else {
            int numeroRegistro = 0;
            switch (motivo) {
                case ACESSO_BLOQUEADO:
                    System.out.println();
                    System.out.println(Constantes.RELATORIO_ACESSO_MOTIVO_ACESSO_BLOQUEADO);
                    System.out.println();
                    break;
                case MATRICULA_INEXISTENTE:
                    System.out.println();
                    System.out.println(Constantes.RELATORIO_ACESSO_MOTIVO_MATRICULA_INEXISTENTE);
                    System.out.println();
                    break;
                case CARGO_SEM_ACESSO:
                    System.out.println();
                    System.out.println(Constantes.RELATORIO_ACESSO_MOTIVO_CARGO_SEM_ACESSO);
                    System.out.println();
                    break;
                case HORARIO_NAO_PERMITIDO:
                    System.out.println();
                    System.out.println(Constantes.RELATORIO_ACESSO_MOTIVO_HORARIO_NAO_PERMITIDO);
                    System.out.println();
                    break;
            }
            for (RegistroAcessoNegado registro : registrosEncontrados) {
                numeroRegistro++;
                System.out.println();
                System.out.println(Constantes.RELATORIO_REGISTRO_CABECALHO + numeroRegistro);
                System.out.println(Constantes.RELATORIO_REGISTRO_DATA + registro.getData());
                System.out.println(Constantes.RELATORIO_REGISTRO_MATRICULA + registro.getMatricula());
                System.out.println();
            }
        }
    }

    /**
     * Exibe o relatorio de registros de acesso negado pela matricula filtrada.
     *
     * @param registrosEncontrados colecao de registros encontrados com a
     * matricula filtrada
     * @param matricula matricula pela qual o relatorio a ser exibido foi
     * filtrado
     * @param encontrouRegistro recebe true se a colecao registrosEncontrados
     * nao estiver vazia e false se estiver
     * @return int opcao utilizada para voltar ao menu principal
     */
    public void exibeRelatorioPorMatricula(ArrayList<RegistroAcessoNegado> registrosEncontrados, int matricula, boolean encontrouRegistro) {
        if (!encontrouRegistro) {
            System.out.println();
            System.out.println(Constantes.RELATORIO_REGISTRO_NENHUM_ENCONTRADO);
            System.out.println();
        } else {
            int numeroRegistro = 0;
            System.out.println();
            System.out.println(Constantes.RELATORIO_ACESSO_MATRICULA + matricula);
            for (RegistroAcessoNegado registro : registrosEncontrados) {
                numeroRegistro++;
                System.out.println();
                System.out.println(Constantes.RELATORIO_REGISTRO_CABECALHO + numeroRegistro);
                System.out.println(Constantes.RELATORIO_REGISTRO_DATA + registro.getData());
                System.out.println(Constantes.RELATORIO_REGISTRO_MOTIVO + registro.getMotivo());
                System.out.println();
            }
        }
    }

    /**
     * Imprime que a opcao inserida pelo usuario nao existe.
     */
    public void exibeOpcaoInexistente() {
        System.out.println();
        System.out.println(Constantes.OPCAO_INEXISTENTE);
        System.out.println();
        System.out.println(Constantes.O_QUE_DESEJA_FAZER);
        System.out.println();
    }

    /**
     * Imprime que a matricula inserida pelo usuario nao existe, oferece um menu
     * para ele tentar inserir novamente ou voltar ao menu principal e trata se
     * a opcao inserida eh realmente um inteiro.
     *
     * @return int opcao inserida pelo usuario
     */
    public int exibeMatriculaInexistente() {
		return 0;
//        int opcao = 0;
//        boolean opcaoInvalida = true;
//        System.out.println();
//        System.out.println(Constantes.MATRICULA_INEXISTENTE);
//        System.out.println();
//        while (opcaoInvalida) {
//            try {
//                System.out.println();
//                System.out.println(Constantes.INSIRA_OPCAO);
//                System.out.println();
//                System.out.println(Constantes.TENTAR_NOVAMENTE);
//                System.out.println(Constantes.VOLTAR_2);
//                System.out.println();
//                opcao = teclado.nextInt();
//                opcaoInvalida = false;
//            } catch (InputMismatchException e) {
//                teclado.next();
//                System.out.println();
//                System.out.println(Constantes.OPCAO_INVALIDA);
//                System.out.println();
//            }
//        }
//        return opcao;
    }

    /**
     * Exibe um menu para o usuario escolher a opcao desejada, para tentar
     * inserir a matricula novamente ou voltar ao menu principal.
     *
     * @return int opcao escolhida pelo usuario
     */
    public int exibeNovaTentativa() {
		return 0;
//        int opcao = 0;
//        boolean opcaoInvalida = true;
//        while (opcaoInvalida) {
//            try {
//                System.out.println();
//                System.out.println(Constantes.INSIRA_OPCAO);
//                System.out.println();
//                System.out.println(Constantes.TENTAR_NOVAMENTE);
//                System.out.println(Constantes.VOLTAR_MENU_PRINCIPAL_2);
//                System.out.println();
//                opcao = teclado.nextInt();
//                opcaoInvalida = false;
//            } catch (InputMismatchException e) {
//                System.out.println();
//                System.out.println(Constantes.OPCAO_INVALIDA);
//                System.out.println();
//            }
//        }
//        return opcao;
    }

	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btVoltar)) {
				setVisible(false);
			}
		}
		
	}

	private class GerenciadorCombos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(comboFiltroMotivo)) {
				
			} else if (e.getSource().equals(comboFiltroMatricula)) {
				
			}
		}
		
	}
	
}
