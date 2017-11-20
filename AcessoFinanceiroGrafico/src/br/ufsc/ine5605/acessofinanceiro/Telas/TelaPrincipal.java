/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorPrincipal;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author bruno
 */
public class TelaPrincipal extends JFrame {

    private JPanel painelPrincipal;
    private JLabel lbTextoPrincipal;
    private JLabel lbTextoSecundario;
    private JButton btMenuAcesso;
    private JButton btMenuFuncionarios;
    private JButton btMenuCargos;
    private JButton btMenuData;
    private JButton btMenuRelatorio;
    private JButton btEncerrar;
    private GridBagConstraints constraints;
    private boolean primeiraVez;

    public TelaPrincipal() {
        super(Constantes.ACESSO_FINANCEIRO);
        this.primeiraVez = false;
        configuraTela();
    }

    private void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;

        //Configuracao GerenciadorBotoes
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Configuracao lbTextoPrincipal
        lbTextoPrincipal = new JLabel(Constantes.PRINCIPAL_TITULO);
        container.add(lbTextoPrincipal);

        //Configuracao lbTextoSecundario
        lbTextoSecundario = new JLabel(Constantes.PRINCIPAL_SUBTITULO);
        container.add(lbTextoSecundario);

        //Configuracao btMenuAcesso
        btMenuAcesso = new JButton(Constantes.PRINCIPAL_MENU_ACESSO);
        btMenuAcesso.setPreferredSize(new Dimension(200, 28));
        btMenuAcesso.addActionListener(btManager);

        //Configuracao btMenuRelatorio
        btMenuRelatorio = new JButton(Constantes.PRINCIPAL_MENU_RELATORIO);
        btMenuRelatorio.setPreferredSize(new Dimension(200, 28));
        btMenuRelatorio.addActionListener(btManager);

        //Configuracao btMenuData
        btMenuData = new JButton(Constantes.PRINCIPAL_MENU_DATA);
        btMenuData.setPreferredSize(new Dimension(200, 28));
        btMenuData.addActionListener(btManager);

        //Configuracao btMenuFuncionarios
        btMenuFuncionarios = new JButton(Constantes.PRINCIPAL_MENU_FUNCIONARIOS);
        btMenuFuncionarios.setPreferredSize(new Dimension(200, 28));
        btMenuFuncionarios.addActionListener(btManager);

        //Configuracao btMenuCargos
        btMenuCargos = new JButton(Constantes.PRINCIPAL_MENU_CARGOS);
        btMenuCargos.setPreferredSize(new Dimension(200, 28));
        btMenuCargos.addActionListener(btManager);

        //Configuracao btEncerrar
        btEncerrar = new JButton(Constantes.PRINCIPAL_MENU_SAIR);
        btEncerrar.setPreferredSize(new Dimension(200, 28));
        btEncerrar.addActionListener(btManager);

        //Configuração painelPrincipal
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        this.painelPrincipal.setVisible(true);
        constraints.gridy = 1;
        painelPrincipal.add(lbTextoPrincipal, constraints);
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 15, 0);
        painelPrincipal.add(lbTextoSecundario, constraints);
        constraints.gridy = 3;
        constraints.insets = new Insets(15, 15, 15, 15);
        painelPrincipal.add(btMenuAcesso, constraints);
        constraints.gridy = 4;
        constraints.insets = new Insets(15, 15, 7, 15);
        painelPrincipal.add(btMenuRelatorio, constraints);
        constraints.gridy = 5;
        constraints.insets = new Insets(7, 7, 7, 7);
        painelPrincipal.add(btMenuFuncionarios, constraints);
        constraints.gridy = 6;
        constraints.insets = new Insets(7, 7, 7, 7);
        painelPrincipal.add(btMenuCargos, constraints);
        constraints.gridy = 7;
        constraints.insets = new Insets(7, 7, 7, 7);
        painelPrincipal.add(btMenuData, constraints);
        constraints.gridy = 8;
        constraints.insets = new Insets(7, 7, 7, 7);
        painelPrincipal.add(btEncerrar, constraints);
        container.add(painelPrincipal);

        setSize(380, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    public void exibeMenuPrincipal() {
        setVisible(true);
    }

    /**
     * Exibe a mensagem de funcionario cadastrado com sucesso.
     */
    public void exibeBemVindo() {

        JOptionPane.showMessageDialog(
                null,
                Constantes.PRINCIPAL_MENU_BEM_VINDO,
                Constantes.ACESSO_FINANCEIRO,
                JOptionPane.PLAIN_MESSAGE
        );
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
            } else if (e.getSource().equals(btEncerrar)) {
                System.exit(0);
            }
        }
    }

}
