/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorCargo;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author João Grasel
 */
public class TelaEditarFuncionario extends JFrame {

    private GerenciadorBotoes btManager;
    private GridBagConstraints constraints;
    private JPanel painelPrincipal;
    private JPanel painelNome;
    private JPanel painelMatricula;
    private JPanel painelDataNascimento;
    private JPanel painelFormatoDataNascimento;
    private JPanel painelSalario;
    private JPanel painelTelefone;
    private JPanel painelCargo;
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
    private JLabel lbFormatoDataNascimento;
    private JLabel lbSalario;
    private JTextField tfSalario;
    private JLabel lbTelefone;
    private JTextField tfTelefone;
    private JButton btCancelar;
    private JButton btConfirmar;

    public TelaEditarFuncionario() {
        super(Constantes.GERENCIAR_FUNCIONARIO_EDITAR);
        this.btManager = new GerenciadorBotoes();

        configuraTela();
    }

    /**
     * Monta todos os componentes da tela.
     */
    private void configuraTela() {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 1;

        //Configuracao lbNome
        lbNome = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_NOME);

        //Configuracao tfNome
        tfNome = new JTextField(25);

        //Configuracao painelNome
        this.painelNome = new JPanel();
        painelNome.setLayout(new BoxLayout(painelNome, BoxLayout.LINE_AXIS));
        this.painelNome.setVisible(true);
        painelNome.add(lbNome);
        painelNome.add(tfNome);

        //Configuracao lbMatricula
        lbMatricula = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_MATRICULA);

        //Configuracao tfMatricula
        tfMatricula = new JTextField(23);

        //Configuracao painelMatricula
        this.painelMatricula = new JPanel();
        painelMatricula.setLayout(new BoxLayout(painelMatricula, BoxLayout.LINE_AXIS));
        this.painelMatricula.setVisible(true);
        painelMatricula.add(lbMatricula);
        painelMatricula.add(tfMatricula);

        //Configuracao lbDataNascimento
        lbDataNascimento = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_DATA_NASCIMENTO);

        //Configuracao tfDataNascimento
        tfDataNascimento = new JTextField(18);

        //Configuracao painelDataNascimento
        this.painelDataNascimento = new JPanel();
        painelDataNascimento.setLayout(new BoxLayout(painelDataNascimento, BoxLayout.LINE_AXIS));
        this.painelDataNascimento.setVisible(true);
        painelDataNascimento.add(lbDataNascimento);
        painelDataNascimento.add(tfDataNascimento);

        //Configuracao lbFormatoDataNascimento
        lbFormatoDataNascimento = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_FORMATO_DATA_NASCIMENTO);

        //Configuracao painelFormatoDataNascimento
        this.painelFormatoDataNascimento = new JPanel();
        painelFormatoDataNascimento.setLayout(new BoxLayout(painelFormatoDataNascimento, BoxLayout.LINE_AXIS));
        this.painelFormatoDataNascimento.setVisible(true);
        painelFormatoDataNascimento.add(lbFormatoDataNascimento);

        //Configuracao lbTelefone
        lbTelefone = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_TELEFONE);

        //Configuracao tfTelefone
        tfTelefone = new JTextField(24);

        //Configuracao painelTelefone
        this.painelTelefone = new JPanel();
        painelTelefone.setLayout(new BoxLayout(painelTelefone, BoxLayout.LINE_AXIS));
        this.painelTelefone.setVisible(true);
        painelTelefone.add(lbTelefone);
        painelTelefone.add(tfTelefone);

        // Configuracao lbSalario
        lbSalario = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_SALARIO);

        //Configuracao tfSalario
        tfSalario = new JTextField(25);

        //Configuracao painelSalario
        this.painelSalario = new JPanel();
        painelSalario.setLayout(new BoxLayout(painelSalario, BoxLayout.LINE_AXIS));
        this.painelSalario.setVisible(true);
        painelSalario.add(lbSalario);
        painelSalario.add(tfSalario);

        //Configuracao comboCargos
        this.cargos = new ArrayList<>();
        this.cargosNomes = new ArrayList<>();
        comboCargos = new JComboBox(cargosNomes.toArray());

        //Configuracao painelCargo
        this.painelCargo = new JPanel();
        painelCargo.setLayout(new BoxLayout(painelCargo, BoxLayout.LINE_AXIS));
        this.painelCargo.setVisible(true);
        painelCargo.add(comboCargos);

        //Configura btCancelar
        btCancelar = new JButton();
        btCancelar.setText(Constantes.COMUM_BOTAO_CANCELAR);
        btCancelar.addActionListener(btManager);

        //Configura btConfirmar
        btConfirmar = new JButton();
        btConfirmar.setText(Constantes.COMUM_BOTAO_EDITAR);
        btConfirmar.addActionListener(btManager);

        //Configuracao painelBotoes
        this.painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(true);
        painelBotoes.add(btCancelar);
        painelBotoes.add(btConfirmar);

        //Configuracao painelPrincipal
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setVisible(true);
        painelPrincipal.add(painelNome);
        constraints.gridy = 2;
        painelPrincipal.add(painelMatricula, constraints);
        constraints.gridy = 3;
        painelPrincipal.add(painelDataNascimento, constraints);
        constraints.gridy = 4;
        painelPrincipal.add(painelFormatoDataNascimento, constraints);
        constraints.gridy = 5;
        painelPrincipal.add(painelTelefone, constraints);
        constraints.gridy = 6;
        painelPrincipal.add(painelSalario, constraints);
        constraints.gridy = 7;
        painelPrincipal.add(painelCargo, constraints);
        constraints.gridy = 8;
        painelPrincipal.add(painelBotoes, constraints);
        constraints.gridy = 9;
        container.add(painelPrincipal, constraints);

        setSize(550, 350);
        setLocationRelativeTo(null);
    }

    // ================== CONFIGURAÇÃO DE TELA ==================
    /**
     * Atualiza a tela de maneira que os cargos disponiveis para seleção sempre
     * sejam todos os cargos atuais do sistema.
     */
    public void updateData() {

        //Configuracao comboCargos
        this.cargos = ControladorCargo.getInstance().getListaCargos();
        comboCargos.removeAllItems();
        for (Cargo cargo : cargos) {
            comboCargos.addItem(cargo);

        }

        this.repaint();
    }

    /**
     * Atualiza a tela e faz com que seja exibida, onde cada campo de informação
     * do funcionario ja esteja preenchida com as informações do funcionario
     * previamente selecionado pelo usuário.
     *
     * @param funcionario
     */
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

    // ================== EXIBIÇÃO DE MENSAGENS ==================
    /**
     * Exibe a mensagem de que o funcionário foi editado com sucesso.
     */
    public void exibeFuncionarioEditadoComSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_EDITADO_SUCESSO,
                Constantes.GERENCIAR_FUNCIONARIO_EDITAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    /**
     * Exibe a mensagem de que o funcionario não foi editado pois a matricula
     * inserida ja esta sendo utilizada.
     */
    public void exibeMatriculaJaExiste() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_MATRICULA_JA_EXISTENTE,
                Constantes.GERENCIAR_FUNCIONARIO_EDITAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    /**
     * Exibe a mensagem de erro com relação ao nome inserido não estar no
     * formato desejado (somente letras e no minimo 3 caracteres).
     */
    public void exibeErroNome() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_ERRO_NOME,
                Constantes.GERENCIAR_FUNCIONARIO_TITULO,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    // ================== GERENCIADOR DE BOTÕES ==================
	
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

}
