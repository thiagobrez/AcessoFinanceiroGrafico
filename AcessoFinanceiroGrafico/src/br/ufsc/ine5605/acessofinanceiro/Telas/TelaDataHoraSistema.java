/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorDataSistema;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author vladimir
 */
public class TelaDataHoraSistema extends JFrame {

    private Scanner teclado;
    private ControladorDataSistema controlador;
    private GerenciadorBotoes btManager;
    private JLabel lbDataSistemaTitulo;
    private JLabel lbDataSistema;
    private JButton btVoltarMenuPrincipal;
    private JButton btAlterarDataSistema;

    public TelaDataHoraSistema(ControladorDataSistema owner) {
        super(Constantes.DATA_SISTEMA_TITULO);
        this.teclado = new Scanner(System.in);
        this.controlador = owner;
        try {
            configuraTela();
        } catch (ParseException e) {
        }
    }

    private void configuraTela() throws ParseException {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //Configuracao lbDataSistemaTitulo
        lbDataSistemaTitulo = new JLabel(Constantes.DATA_SISTEMA_ATUAL);
        container.add(lbDataSistemaTitulo);

        //Configuracao lbDataSistema
        lbDataSistema = new JLabel(controlador.getDataSistema().toString());
        container.add(lbDataSistema);

        //Configura btVoltarMenuPrincipal
        btVoltarMenuPrincipal = new JButton();
        btVoltarMenuPrincipal.setText(Constantes.COMUM_BOTAO_VOLTAR_MENU_PRINCIPAL);
        btVoltarMenuPrincipal.addActionListener(btManager);
        container.add(btVoltarMenuPrincipal);

        //Configura btAlterarDataSistema
        btAlterarDataSistema = new JButton();
        btAlterarDataSistema.setText(Constantes.DATA_SISTEMA_BOTAO_ALTERAR);
        btAlterarDataSistema.addActionListener(btManager);
        container.add(btAlterarDataSistema);

        setSize(500, 250);
        setLocationRelativeTo(null);
    }

    public void exibeMenuDataSistema() {
        setVisible(true);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btAlterarDataSistema)) {
                controlador.alterarDataSistema();
            }
            if (e.getSource().equals(btVoltarMenuPrincipal)) {
                setVisible(false);
                controlador.voltarMenuPrincipal();
            }

        }

    }

    //
    //----------------- ANTIGO -------------------
    /**
     * exibe na tela a data e a hora atuais do sistema
     *
     * @param dataHoraSistema atual do sistema
     */
    public void exibeDataHoraSistema(Date dataHoraSistema) {
        System.out.println();
        System.out.println(Constantes.HORA_ATUAL_DO_SISTEMA + dataHoraSistema);
        System.out.println();
    }

    /**
     * exibe as opcoes que o usuario pode selecioanr quanto a data e a hora do
     * sistema
     */
    public void exibeMenuDataHoraSistema() {
        System.out.println(Constantes.TITULO_MENU_DATA_HORA_SISTEMA);
        System.out.println();
        System.out.println(Constantes.ALTERAR_DATA_E_HORA);
        System.out.println(Constantes.DATA_VOLTAR_MENU_PRINCIPAL);
        System.out.println();
        System.out.println(Constantes.O_QUE_DESEJA_FAZER);
        System.out.println();
    }

    /**
     * pede que o usuario insira a nova data e hora do sistema
     *
     * @return dataEHora inseridas pelo usuario
     */
    public String pedeDataHoraSistema() {
        return teclado.nextLine();
    }

    /**
     * pede que o usuario digite um numero correspondente a opcao que ele deseja
     * selecionar
     *
     * @return opcao int inserido pelo usuario
     */
    public int pedeOpcao() {
        int opcao = 0;
        boolean opcaoValida = true;
        while (opcaoValida) {
            try {
                System.out.println();
                opcao = teclado.nextInt();
                teclado.nextLine();
                System.out.println();
                opcaoValida = false;

            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.OPCAO_INVALIDA);
                teclado.nextLine();
            }
        }
        return opcao;
    }

    /**
     * exibe a mensagem de erro de opcao inexistente
     */
    public void opcaoInexistente() {
        System.out.println();
        System.out.println(Constantes.OPCAO_INEXISTENTE);
        System.out.println();
    }

    /**
     * exibe a mensagem de erro de data e hora invalidas
     */
    public void mensagemDataInvalida() {
        System.out.println();
        System.out.println(Constantes.DATA_HORA_SISTEMA_INVALIDOS);
        System.out.println();
    }

    /**
     * exibe o menu de confirmacao de alteracao de data e hora do usuario, onde
     * o usuario tem as opcoes de sim ou nao
     */
    public void exibeConfirmacaoDataHoraSistema() {
        System.out.println();
        System.out.println(Constantes.CONFIRMA_ALTERACAO_DATA_HORA);
        System.out.println();
        System.out.println(Constantes.SIM);
        System.out.println(Constantes.NAO);
        System.out.println();
    }

    /**
     * exibe a mensagem de data e horas atualizadas com sucesso
     */
    public void mensagemDataHoraAtualizadosSucesso() {
        System.out.println();
        System.out.println(Constantes.DATA_HORA_ATUALIZADOS_SUCESSO);
        System.out.println();
    }

    /**
     * exibe a mensagem de data e hora não atualizadas
     */
    public void mensagemDataHoraNaoAtualizados() {
        System.out.println();
        System.out.println(Constantes.DATA_HORA_NAO_ATUALIZADOS);
        System.out.println();
    }

    /**
     * exibe na tela uma mensagem pedindo para que o usuário insira uma data no
     * sistema
     */
    public void exibeMensagemPedeDataHoraSistema() {
        System.out.println();
        System.out.println(Constantes.INSIRA_DATA_SISTEMA);
        System.out.println();
    }

}
