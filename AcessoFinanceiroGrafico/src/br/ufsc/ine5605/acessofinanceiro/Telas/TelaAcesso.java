/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorAcesso;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author thiagobrezinski
 */
public class TelaAcesso extends JFrame {

	private JLabel lbPrincipal;
	private JTextField tfMatricula;
	private JButton btAcesso;
	private JButton btVoltar;

    public TelaAcesso() {
		super(Constantes.ACESSO_FINANCEIRO);
		configuraTela();
    }

	/**
	 * Configura os elementos que aparecerao na interface grafica
	 */
	private void configuraTela() {
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		
		//Configuracao lbPrincipal
		lbPrincipal = new JLabel(Constantes.ACESSO_INSIRA_MATRICULA);
		container.add(lbPrincipal);
		
		//Configuracao tfMatricula
		tfMatricula = new JTextField(5);
		container.add(tfMatricula);
		
		//Configuracao GerenciadorBotoes
		GerenciadorBotoes btManager = new GerenciadorBotoes();
		
		//Configuracao btAcesso
		btAcesso = new JButton(Constantes.ACESSO_BOTAO_ACESSAR);
		btAcesso.addActionListener(btManager);
		container.add(btAcesso);
		
		//Configuracao btVoltar
		btVoltar = new JButton(Constantes.COMUM_BOTAO_VOLTAR);
		btVoltar.addActionListener(btManager);
		container.add(btVoltar);
	
		setSize(350, 100);
		setLocationRelativeTo(null);
	}

    /**
     * Exibe modal indicando que o acesso ao financeiro foi permitido.
     */
    public void exibeAcessoPermitido() {
		JOptionPane.showMessageDialog(
				null,
				Constantes.ACESSO_PERMITIDO,
				Constantes.ACESSO_PERMITIDO_TITULO,
				JOptionPane.PLAIN_MESSAGE
		);
    }

    /**
     * Exibe modal indicando que o acesso ao financeiro foi negado pois a matricula
     * nao existe.
     */
    public void exibeAcessoNegadoMatriculaInexistente() {
		JOptionPane.showMessageDialog(
				null,
				Constantes.MOTIVO_MATRICULA_INEXISTENTE,
				Constantes.ACESSO_NEGADO_TITULO,
				JOptionPane.PLAIN_MESSAGE
		);
    }

    /**
     * Exibe modal indicando que o acesso ao financeiro foi negado pois o cargo nao
     * tem acesso.
     */
    public void exibeAcessoNegadoCargoSemAcesso() {
		JOptionPane.showMessageDialog(
				null,
				Constantes.MOTIVO_CARGO_SEM_ACESSO,
				Constantes.ACESSO_NEGADO_TITULO,
				JOptionPane.PLAIN_MESSAGE
		);
    }

    /**
     * Exibe modal indicando que o acesso ao financeiro foi negado pois o horario nao
     * eh permitido para o cargo do funcionario tentando o acesso.
     */
    public void exibeAcessoNegadoHorarioNaoPermitido() {
		JOptionPane.showMessageDialog(
				null,
				Constantes.MOTIVO_HORARIO_NAO_PERMITIDO,
				Constantes.ACESSO_NEGADO_TITULO,
				JOptionPane.PLAIN_MESSAGE
		);
    }

    /**
     * Exibe modal indicando que o acesso ao financeiro foi negado pois o acesso
	 * do funcionario foi bloqueado.
     */
    public void exibeAcessoNegadoAcessoBloqueado() {
		JOptionPane.showMessageDialog(
				null,
				Constantes.MOTIVO_ACESSO_BLOQUEADO,
				Constantes.ACESSO_NEGADO_TITULO,
				JOptionPane.PLAIN_MESSAGE
		);
    }

	/**
	 * Exibe modal indicando que a matricula inserida eh invalida.
	 */
	public void exibeMatriculaInvalida() {
		JOptionPane.showMessageDialog(
				null,
				Constantes.ACESSO_MATRICULA_INVALIDA,
				Constantes.ACESSO_NEGADO_TITULO,
				JOptionPane.PLAIN_MESSAGE
		);
    }
	
	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btAcesso)) {
				if(ControladorAcesso.getInstance().verificaMatricula(tfMatricula.getText())) {
					ControladorAcesso.getInstance().acessaFinanceiro(Integer.parseInt(tfMatricula.getText()));
				} else {
					exibeMatriculaInvalida();
				}
			} else if(e.getSource().equals(btVoltar)) {
				setVisible(false);
			}
		}
		
	}
	
}
