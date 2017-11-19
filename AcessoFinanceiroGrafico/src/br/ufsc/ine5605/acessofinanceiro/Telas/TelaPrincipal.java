/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorPrincipal;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author bruno
 */
public class TelaPrincipal extends JFrame {

    private JLabel lbPrincipal;
    private JButton btMenuAcesso;
    private JButton btMenuFuncionarios;
    private JButton btMenuCargos;
    private JButton btMenuData;
    private JButton btMenuRelatorio;
    private GridBagConstraints constraints;

    public TelaPrincipal() {
        //refatorar depois essa constante
        super(Constantes.ACESSO_FINANCEIRO);
        configuraTela();
    }

    private void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 10;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        //Configuracao lbPrincipal
        lbPrincipal = new JLabel(Constantes.PRINCIPAL_TITULO);
        container.add(lbPrincipal, constraints);

        //Configuracao GerenciadorBotoes
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Configuracao btMenuAcesso
        btMenuAcesso = new JButton(Constantes.PRINCIPAL_MENU_ACESSO);
        constraints.gridx = 0;
        constraints.gridy = 2;
        btMenuAcesso.addActionListener(btManager);
        container.add(btMenuAcesso, constraints);

        //Configuracao btMenuFuncionarios
        btMenuFuncionarios = new JButton(Constantes.PRINCIPAL_MENU_FUNCIONARIOS);
        constraints.gridx = 0;
        constraints.gridy = 3;
        btMenuFuncionarios.addActionListener(btManager);
        container.add(btMenuFuncionarios, constraints);

        //Configuracao btMenuAcesso
        btMenuCargos = new JButton(Constantes.PRINCIPAL_MENU_CARGOS);
        constraints.gridx = 0;
        constraints.gridy = 4;
        btMenuCargos.addActionListener(btManager);
        container.add(btMenuCargos, constraints);

        //Configuracao btMenuAcesso
        btMenuData = new JButton(Constantes.PRINCIPAL_MENU_DATA);
        constraints.gridx = 0;
        constraints.gridy = 5;
        btMenuData.addActionListener(btManager);
        container.add(btMenuData, constraints);

        //Configuracao btMenuAcesso
        btMenuRelatorio = new JButton(Constantes.PRINCIPAL_MENU_RELATORIO);
        constraints.gridx = 0;
        constraints.gridy = 6;
        btMenuRelatorio.addActionListener(btManager);
        container.add(btMenuRelatorio, constraints);

        setSize(720, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public void exibeMenuPrincipal() {
        setVisible(true);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(btMenuAcesso)) {
                ControladorPrincipal.getInstance().acessarFinanceiro();
            } else if (e.getSource().equals(btMenuFuncionarios)) {
                ControladorPrincipal.getInstance().gerenciarFuncionarios();
            } else if (e.getSource().equals(btMenuCargos)) {
                ControladorPrincipal.getInstance().gerenciarCargos();
            } else if (e.getSource().equals(btMenuData)) {
                ControladorPrincipal.getInstance().gerenciarData();
            } else if (e.getSource().equals(btMenuRelatorio)) {
                ControladorPrincipal.getInstance().emitirRelatorio();
            }
        }

    }

}
