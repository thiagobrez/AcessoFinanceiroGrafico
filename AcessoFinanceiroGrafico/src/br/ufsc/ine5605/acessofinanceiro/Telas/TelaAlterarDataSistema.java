/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorDataSistema;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import java.awt.Container;
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
 * @author João Grasel
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

    /**
     * Monta todos os componentes da tela.
     */
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

// ================== CONFIGURAÇÃO DE TELA ==================
    /**
     * Atualiza a tela e faz com que seja exibida.
     */
    public void exibeAlterarDataSistema() {
        updateData();
        setVisible(true);
    }

    /**
     * Fecha a tela.
     */
    public void fechar() {
        setVisible(false);
    }

    /**
     * Atualiza a tela de maneira que o campo para o usuário inserir a data e
     * horario desejado esteja limpo.
     */
    public void updateData() {
        tfPrincipal.setText("");
        this.repaint();
    }

// ================== EXIBIÇÃO DE MENSAGENS ==================
    /**
     * Exibe a mensagem de que a data e a hora do sistema foram alteradas com
     * sucesso.
     */
    public void exibeDataHoraAlteradoSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.DATA_SISTEMA_SUCESSO,
                Constantes.DATA_SISTEMA_TITULO,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    /**
     * Exibe a mensagem de que a data e hora do sistema não foram atualizadas
     * com sucesso, formato de texto inserido pelo usuário não confere com o
     * desejado.
     */
    public void exibeErroFormatoAlteracaoDataHora() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.DATA_SISTEMA_FORMATO_INCORRETO,
                Constantes.DATA_SISTEMA_TITULO,
                JOptionPane.PLAIN_MESSAGE
        );
    }

// ================== GERENCIADOR DE BOTÕES ==================
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
