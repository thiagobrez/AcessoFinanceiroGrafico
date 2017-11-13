/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorPrincipal;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author bruno
 */
public class TelaPrincipal extends JFrame {

    private ControladorPrincipal owner;
    private JLabel lbPrincipal;
    private JButton btMenuAcesso;
    private JButton btMenuFuncionarios;
    private JButton btMenuCargos;
    private JButton btMenuData;
    private JButton btMenuRelatorio;

    public TelaPrincipal(ControladorPrincipal owner) {
        //refatorar depois essa constante
        super(Constantes.ACESSO_FINANCEIRO);
        this.owner = owner;
        configuraTela();
    }

    private void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        //Configuracao lbPrincipal
        lbPrincipal = new JLabel(Constantes.PRINCIPAL_TITULO);
        container.add(lbPrincipal);

        //Configuracao GerenciadorBotoes
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Configuracao btMenuAcesso
        btMenuAcesso = new JButton(Constantes.PRINCIPAL_MENU_ACESSO);
        //btMenuAcesso.addActionListener(btManager);
        container.add(btMenuAcesso);

        //Configuracao btMenuFuncionarios
        btMenuFuncionarios = new JButton(Constantes.PRINCIPAL_MENU_FUNCIONARIOS);
        btMenuFuncionarios.addActionListener(btManager);
        container.add(btMenuFuncionarios);

        //Configuracao btMenuAcesso
        btMenuCargos = new JButton(Constantes.PRINCIPAL_MENU_CARGOS);
        //btMenuCargos.addActionListener(btManager);
        container.add(btMenuCargos);

        //Configuracao btMenuAcesso
        btMenuData = new JButton(Constantes.PRINCIPAL_MENU_DATA);
        //btMenuData.addActionListener(btManager);
        container.add(btMenuData);

        //Configuracao btMenuAcesso
        btMenuRelatorio = new JButton(Constantes.PRINCIPAL_MENU_RELATORIO);
        //btMenuRelatorio.addActionListener(btManager);
        container.add(btMenuRelatorio);

        setSize(720, 480);
        setLocationRelativeTo(null);

        setVisible(true);

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // usar equals?
            if (e.getSource().equals(btMenuAcesso)) {
                owner.acessarFinanceiro();
            } else if (e.getSource().equals(btMenuFuncionarios)) {
                setVisible(false);
                owner.gerenciarFuncionarios();
            } else if (e.getSource().equals(btMenuCargos)) {
                owner.gerenciarCargos();
            } else if (e.getSource().equals(btMenuData)) {
                owner.gerenciarData();
            } else if (e.getSource().equals(btMenuRelatorio)) {
                owner.emitirRelatorio();
            }
        }

    }

}
