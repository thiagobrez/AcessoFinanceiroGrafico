/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorDataSistema;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author vladimir
 */
public class TelaAlterarDataSistema extends JFrame {

    private ControladorDataSistema controlador;
    private JLabel lbPrincipal;
    private JTextField tfPrincipal;
    private JButton btCancelar;
    private JButton btAlterar;

    public TelaAlterarDataSistema(ControladorDataSistema owner) {
        super(Constantes.DATA_SISTEMA_TITULO);
        this.controlador = owner;
        try {
            configuraTela();
        } catch (ParseException e) {
        }
    }

    private void configuraTela() throws ParseException {

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        //Configuracao lbPrincipal
        lbPrincipal = new JLabel(Constantes.DATA_SISTEMA_NOVO);
        container.add(lbPrincipal);

        //Configuracao tfPrincipal
        tfPrincipal = new JTextField(10);
        container.add(tfPrincipal);

        //Configuracao GerenciadorBotoes
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Configuracao btAcesso
        btCancelar = new JButton(Constantes.COMUM_BOTAO_CANCELAR);
        btCancelar.addActionListener(btManager);
        container.add(btCancelar);

        //Configuracao btVoltar
        btAlterar = new JButton(Constantes.COMUM_BOTAO_VOLTAR);
        btAlterar.addActionListener(btManager);
        container.add(btAlterar);

        setSize(400, 190);
        setLocationRelativeTo(null);
    }

    public void exibeAlterarDataSistema() {
        setVisible(true);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

}
