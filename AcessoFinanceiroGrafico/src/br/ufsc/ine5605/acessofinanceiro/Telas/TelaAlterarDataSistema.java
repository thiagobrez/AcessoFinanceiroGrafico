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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author vladimir
 */
public class TelaAlterarDataSistema extends JFrame {

    private ControladorDataSistema controlador;
    private GridBagConstraints constraints;
    private JPanel painelBotoes;
    private JLabel lbPrincipal;
    private JTextField tfPrincipal;
    private JButton btCancelar;
    private JButton btAlterar;
    private JLabel lbFormato;

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
        container.setLayout(new GridBagLayout());

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.PAGE_START;
        constraints.gridx = 0;
        constraints.gridy = 1;

        //Configuracao lbPrincipal
        lbPrincipal = new JLabel(Constantes.DATA_SISTEMA_NOVO);
        container.add(lbPrincipal);

        //Configuracao tfPrincipal
        tfPrincipal = new JTextField(18);
        container.add(tfPrincipal);

        //Configuracao GerenciadorBotoes
        GerenciadorBotoes btManager = new GerenciadorBotoes();

        //Configuracao btCancelar
        btCancelar = new JButton(Constantes.COMUM_BOTAO_CANCELAR);
        btCancelar.addActionListener(btManager);
        container.add(btCancelar, constraints);

        //Configuracao btAlterar
        btAlterar = new JButton(Constantes.COMUM_BOTAO_ALTERAR);
        btAlterar.addActionListener(btManager);

        //Configuracao paibelBotoes
        this.painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(true);
        painelBotoes.add(btCancelar);
        painelBotoes.add(btAlterar);
        constraints.gridx = 1;
        constraints.gridy = 2;
        container.add(painelBotoes, constraints);

        //Configuracao lbFormato
        lbFormato = new JLabel(Constantes.DATA_SISTEMA_FORMATO);
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(lbFormato, constraints);

        setSize(600, 220);
        setLocationRelativeTo(null);
    }

    public void exibeAlterarDataSistema() {
        updateData();
        setVisible(true);
    }

    public void exibeDataHoraAlteradoSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.DATA_SISTEMA_SUCESSO,
                Constantes.DATA_SISTEMA_TITULO,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    public void exibeErroFormatoAlteracaoDataHora() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.DATA_SISTEMA_FORMATO_INCORRETO,
                Constantes.DATA_SISTEMA_TITULO,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    public void fechar() {
        setVisible(false);
    }

    public void updateData() {
        tfPrincipal.setText("");
        this.repaint();
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCancelar)) {
                setVisible(false);
            }
            if (e.getSource().equals(btAlterar)) {
                ControladorDataSistema.getInstance().alterarDataSistema(tfPrincipal.getText());
            }

        }

    }

}
