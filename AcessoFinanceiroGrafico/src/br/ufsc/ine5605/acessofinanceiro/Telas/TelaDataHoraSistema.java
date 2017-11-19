/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorDataSistema;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author João Grasel
 */
public class TelaDataHoraSistema extends JFrame {

    private ControladorDataSistema controlador;
    private GridBagConstraints constraints;
    private GerenciadorBotoes btManager;
    private JLabel lbDataSistemaTitulo;
    private JLabel lbDataSistema;
    private JButton btVoltarMenuPrincipal;
    private JButton btAlterarDataSistema;

    public TelaDataHoraSistema(ControladorDataSistema owner) {
        super(Constantes.DATA_SISTEMA_TITULO);
        this.controlador = owner;
        this.btManager = new GerenciadorBotoes();
        try {
            configuraTela();
        } catch (ParseException e) {
        }
    }

    /**
     * Monta todos os componentes da tela.
     */
    private void configuraTela() throws ParseException {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.PAGE_START;

        //Configuracao lbDataSistemaTitulo
        lbDataSistemaTitulo = new JLabel(Constantes.DATA_SISTEMA_ATUAL);
        container.add(lbDataSistemaTitulo);

        //Configuracao lbDataSistema
        lbDataSistema = new JLabel("");
        container.add(lbDataSistema);

        //Configura btVoltarMenuPrincipal
        btVoltarMenuPrincipal = new JButton();
        btVoltarMenuPrincipal.setText(Constantes.COMUM_BOTAO_VOLTAR_MENU_PRINCIPAL);
        btVoltarMenuPrincipal.addActionListener(btManager);
        constraints.gridx = 0;
        constraints.gridy = 4;
        container.add(btVoltarMenuPrincipal, constraints);

        //Configura btAlterarDataSistema
        btAlterarDataSistema = new JButton();
        btAlterarDataSistema.setText(Constantes.DATA_SISTEMA_BOTAO_ALTERAR);
        btAlterarDataSistema.addActionListener(btManager);
        constraints.gridx = 1;
        constraints.gridy = 4;
        container.add(btAlterarDataSistema, constraints);

        setSize(500, 200);
        setLocationRelativeTo(null);
    }

// ================== CONFIGURAÇÃO DA TELA ==================
    /**
     * Atualiza a tela.
     */
    public void updateData() {
        exibeDataSistema();
        this.repaint();
    }

    /**
     * Atualiza a tela e faz com que seja exibida.
     */
    public void exibeMenuDataSistema() {
        setVisible(true);
        exibeDataSistema();
    }

    /**
     * Exibe na tela a data e hora do sistema.
     */
    public void exibeDataSistema() {
        String dataSistema = pegaDataSistema();
        lbDataSistema.setText(dataSistema);
    }

    /**
     * Pede ao controlador qual a data e hora atuais do sistema.
     *
     * @return Data e hora do sistema no formato String.
     */
    public String pegaDataSistema() {
        return ControladorDataSistema.getInstance().getDataSistemaString();
    }

// ================== GERENCIADOR DE BOTÕES ==================
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btAlterarDataSistema)) {
                ControladorDataSistema.getInstance().exibeAlterarDataSistema();
            }
            if (e.getSource().equals(btVoltarMenuPrincipal)) {
                setVisible(false);
                ControladorDataSistema.getInstance().voltarMenuPrincipal();
            }

        }

    }

}
