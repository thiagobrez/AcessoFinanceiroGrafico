/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorPrincipal;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author vladimir
 */
public class TelaEditarFuncionario extends JFrame {

    private GerenciadorBotoes btManager;
    private ControladorFuncionario controlador;
    private GridBagConstraints constraints;
    private JPanel painelBotoes;
    private JComboBox comboCargos;
    private List<Cargo> cargos;
    private List<String> cargosNomes;
    private int matriculaAntiga;
    private JLabel lbNome;
    private JTextField tfNome;
    private JLabel lbMatricula;
    private JTextField tfMatricula;
    private JLabel lbDataNascimento;
    private JTextField tfDataNascimento;
    private JLabel lbSalario;
    private JTextField tfSalario;
    private JLabel lbTelefone;
    private JTextField tfTelefone;
    private JButton btCancelar;
    private JButton btConfirmar;

    public TelaEditarFuncionario(ControladorFuncionario owner) {
        super(Constantes.GERENCIAR_FUNCIONARIO_EDITAR);
        this.controlador = owner;
        this.btManager = new GerenciadorBotoes();

        configuraTela();
    }

    private void configuraTela() {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        GerenciadorCombos comboManager = new GerenciadorCombos();

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 0;

        //Configuracao lbNome
        lbNome = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_NOME);
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(lbNome, constraints);

        //Configuracao tfNome
        tfNome = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(tfNome, constraints);

        //Configuracao lbMatricula
        lbMatricula = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_MATRICULA);
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(lbMatricula);

        //Configuracao tfMatricula
        tfMatricula = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(tfMatricula);

        //Configuracao lbDataNascimento
        lbDataNascimento = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_DATA_NASCIMENTO);
        constraints.gridx = 0;
        constraints.gridy = 6;
        container.add(lbDataNascimento);

        //Configuracao tfNascimento
        tfDataNascimento = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 6;
        container.add(tfDataNascimento);

        //Configuracao lbTelefone
        lbTelefone = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_TELEFONE);
        constraints.gridx = 0;
        constraints.gridy = 9;
        container.add(lbTelefone);

        //Configuracao tfTelefone
        tfTelefone = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 9;
        container.add(tfTelefone);

        // Configuracao lbSalario
        lbSalario = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_SALARIO);
        constraints.gridx = 0;
        constraints.gridy = 12;
        container.add(lbSalario);

        //Configuracao tfSalario
        tfSalario = new JTextField(10);
        constraints.gridx = 0;
        constraints.gridy = 12;
        container.add(tfSalario);

        //Configuracao comboCargos
        this.cargos = new ArrayList<>();
        this.cargosNomes = new ArrayList<>();
        comboCargos = new JComboBox(cargosNomes.toArray());
        comboCargos.addActionListener(comboManager);
        constraints.gridx = 0;
        constraints.gridy = 15;
        container.add(comboCargos, constraints);

        //Configura btCancelar
        btCancelar = new JButton();
        btCancelar.setText(Constantes.COMUM_BOTAO_CANCELAR);
        btCancelar.addActionListener(btManager);
        container.add(btCancelar);

        //Configura btConfirmar
        btConfirmar = new JButton();
        btConfirmar.setText(Constantes.COMUM_BOTAO_EDITAR);
        btConfirmar.addActionListener(btManager);
        container.add(btConfirmar);

        //Configuracao paibelBotoes
        this.painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(false);

        setSize(1000, 700);
        setLocationRelativeTo(null);
    }

    public void updateData() {

        //Configuracao comboCargos
        this.cargos = ControladorPrincipal.getInstance().getListaCargos();
        comboCargos.removeAllItems();
        for (Cargo cargo : cargos) {
            comboCargos.addItem(cargo);

        }

        this.repaint();
    }

    public void exibeMenuEditaFuncionario(Funcionario funcionario) {
        updateData();
        setVisible(true);
        matriculaAntiga = funcionario.getMatricula();
        tfMatricula.setText(Integer.toString(funcionario.getMatricula()));
        tfNome.setText(funcionario.getNome());
        tfDataNascimento.setText(funcionario.getDataNascimento());
        tfTelefone.setText(Integer.toString(funcionario.getTelefone()));
        tfSalario.setText(Integer.toString(funcionario.getSalario()));

    }

    public void exibeFuncionarioEditadoComSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_EDITADO_SUCESSO,
                Constantes.GERENCIAR_FUNCIONARIO_EDITAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    public void exibeMatriculaJaExiste() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_MATRICULA_JA_EXISTENTE,
                Constantes.GERENCIAR_FUNCIONARIO_EDITAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(btConfirmar)) {
                ControladorFuncionario.getInstance().editaFuncionario(matriculaAntiga, Integer.parseInt(tfMatricula.getText()),
                        tfNome.getText(), tfDataNascimento.getText(), Integer.parseInt(tfTelefone.getText()),
                        Integer.parseInt(tfSalario.getText()), (Cargo) comboCargos.getSelectedItem());
                setVisible(false);
            }

            if (e.getSource().equals(btCancelar)) {
                setVisible(false);
            }

        }

    }

    private class GerenciadorCombos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

}
