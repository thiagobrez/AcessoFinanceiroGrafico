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
import javax.swing.JTextField;

/**
 *
 * @author thiagobrezinski
 */
public class TelaAcesso extends JFrame {

    private ControladorAcesso owner;
	private JLabel lbPrincipal;
	private JTextField tfMatricula;
	private JButton btAcesso;
	private JButton btVoltar;

    public TelaAcesso(ControladorAcesso owner) {
		super(Constantes.ACESSO_FINANCEIRO);
        this.owner = owner;
		configuraTela();
    }

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
	
		setSize(360, 150);
		setLocationRelativeTo(null);
		
//		Para testes
//		setVisible(true);
	}
	
    /**
     * Exibe o menu para insercao da matricula para tentativa de acesso ao
     * financeiro e trata se a matricula inserida eh realmente um inteiro.
     *
     * @return int matricula usada para a tentativa de acesso
     */
    public void exibeAcessoFinanceiro() {
        setVisible(true);
    }

    /**
     * Imprime na tela que o acesso ao financeiro foi permitido.
     */
    public void exibeAcessoPermitido() {
        
    }

    /**
     * Imprime na tela que o acesso ao financeiro foi negado pois a matricula
     * nao existe.
     */
    public void exibeAcessoNegadoMatriculaInexistente() {

    }

    /**
     * Imprime na tela que o acesso ao financeiro foi negado pois o cargo nao
     * tem acesso.
     */
    public void exibeAcessoNegadoCargoSemAcesso() {

    }

    /**
     * Imprime na tela que o acesso ao financeiro foi negado pois o horario nao
     * eh permitido para o cargo do funcionario tentando o acesso.
     */
    public void exibeAcessoNegadoHorarioNaoPermitido() {

    }

    /**
     * Imprime na tela que o acesso ao financeiro foi negado pois o acesso do
     * funcionario foi bloqueado.
     */
    public void exibeAcessoNegadoAcessoBloqueado() {
        
    }

    /**
     * Exibe um menu para o usuario escolher a opcao desejada, para tentar
     * inserir a matricula novamente ou voltar ao menu principal.
     *
     * @return int opcao escolhida pelo usuario
     */
    public int exibeNovaTentativa() {
        
    }

	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// usar equals?
			if(e.getSource() == btAcesso) {
//				Nos slides: ctrl.realizaAcao?
//				Assim a tela estaria mandando no ctrl.
//				Verificar com prof em aula.
			} else if(e.getSource() == btVoltar) {
				
			}
		}
		
	}
	
}
