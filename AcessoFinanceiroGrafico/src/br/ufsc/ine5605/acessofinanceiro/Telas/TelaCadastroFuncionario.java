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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Collection;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vladimir
 */
public class TelaCadastroFuncionario extends JFrame {

    private GerenciadorBotoes btManager;
    private ControladorFuncionario controlador;
    private GridBagConstraints constraints;
    private JTable tbItens;
    private JScrollPane spBaseTabela;
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
    private JButton btCadastrar;

    public TelaCadastroFuncionario(ControladorFuncionario owner) {
        super(Constantes.GERENCIAR_FUNCIONARIO_CADASTRAR);
        this.controlador = owner;
        this.btManager = new GerenciadorBotoes();

        configuraTela();
    }

    private void configuraTela() {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 10;
        constraints.gridheight = 8;
        constraints.gridx = 0;
        constraints.gridy = 8;

        //Configuracao tbItens
        this.tbItens = new JTable();
        tbItens.setPreferredScrollableViewportSize(new Dimension(800, 200));
        tbItens.setFillsViewportHeight(true);
        spBaseTabela = new JScrollPane(tbItens);
        container.add(spBaseTabela, constraints);

        //Configuracao lbNome
        lbNome = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_NOME);
        container.add(lbNome);

        //Configuracao tfNome
        tfNome = new JTextField(20);
        container.add(tfNome);

        //Configuracao lbMatricula
        lbMatricula = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_MATRICULA);
        container.add(lbMatricula);

        //Configuracao tfMatricula
        tfMatricula = new JTextField(10);
        container.add(tfMatricula);

        //Configuracao lbDataNascimento
        lbDataNascimento = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_DATA_NASCIMENTO);
        container.add(lbDataNascimento);

        //Configuracao tfNascimento
        tfDataNascimento = new JTextField(10);
        container.add(tfDataNascimento);

        //Configuracao lbTelefone
        lbTelefone = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_TELEFONE);
        container.add(lbTelefone);

        //Configuracao tfTelefone
        tfTelefone = new JTextField(10);
        container.add(tfTelefone);

        // Configuracao lbSalario
        lbSalario = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_SALARIO);
        container.add(lbSalario);

        //Configuracao tfSalario
        tfSalario = new JTextField(10);
        container.add(tfSalario);

        //Configura btCancelar
        btCancelar = new JButton();
        btCancelar.setText(Constantes.COMUM_BOTAO_CANCELAR);
        btCancelar.addActionListener(btManager);
        container.add(btCancelar);

        //Configura btCadastrar
        btCadastrar = new JButton();
        btCadastrar.setText(Constantes.COMUM_BOTAO_CADASTRAR);
        btCadastrar.addActionListener(btManager);
        container.add(btCadastrar);

        setSize(1200, 500);
        setLocationRelativeTo(null);
    }

    public void updateData() {

        //Configuracao modelTbItens
        DefaultTableModel modelTbItens = new DefaultTableModel();
        modelTbItens.addColumn(Constantes.COMUM_NOME);
        modelTbItens.addColumn(Constantes.COMUM_CODIGO);

        Collection<Cargo> listaCargos = ControladorPrincipal.getInstance().getListaCargos();

        for (Cargo cargo : listaCargos) {
            modelTbItens.addRow(new Object[]{
                cargo.getNome(),
                cargo.getCodigo(),});
        }
        tbItens.setModel(modelTbItens);

        this.repaint();
    }

    public void exibeMenuCadastroFuncionario() {
        updateData();
        setVisible(true);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCadastrar)) {
                controlador.cadastraFuncionario(Integer.parseInt(tfMatricula.getText()),
                        tfNome.getText(), tfDataNascimento.getText(), Integer.parseInt(tfTelefone.getText()),
                        Integer.parseInt(tfSalario.getText()), cargo);
                setVisible(false);
            }

            if (e.getSource().equals(btCancelar)) {
                setVisible(false);
            }

        }

    }

}
