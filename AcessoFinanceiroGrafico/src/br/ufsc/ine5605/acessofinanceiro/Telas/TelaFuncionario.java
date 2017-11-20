/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Collection;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author João Grasel
 */
public class TelaFuncionario extends JFrame {

    private GerenciadorBotoes btManager;
    private GridBagConstraints constraints;
    private JPanel painelBotoes;
    private JTable tbItens;
    private JScrollPane spBaseTabela;
    private JButton btCadastraFuncionario;
    private JButton btEditaFuncionario;
    private JButton btDeletaFuncionario;
    private JButton btVoltarMenuPrincipal;

    public TelaFuncionario() {
        super(Constantes.GERENCIAR_FUNCIONARIO_TITULO);
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
        constraints.gridwidth = 15;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;

        //Configuracao tbItens
        this.tbItens = new JTable();
        tbItens.setPreferredScrollableViewportSize(new Dimension(603, 200));
        tbItens.setFillsViewportHeight(true);
        spBaseTabela = new JScrollPane(tbItens);
        container.add(spBaseTabela, constraints);

        //Configura btCadastraFuncionario
        btCadastraFuncionario = new JButton();
        btCadastraFuncionario.setText(Constantes.GERENCIAR_FUNCIONARIO_CADASTRAR);
        btCadastraFuncionario.addActionListener(btManager);

        //Configura btEditaFuncionario
        btEditaFuncionario = new JButton();
        btEditaFuncionario.setText(Constantes.GERENCIAR_FUNCIONARIO_EDITAR);
        btEditaFuncionario.addActionListener(btManager);

        //Configura btDeletaFuncionario
        btDeletaFuncionario = new JButton();
        btDeletaFuncionario.setText(Constantes.GERENCIAR_FUNCIONARIO_DELETAR);
        btDeletaFuncionario.addActionListener(btManager);

        //Configura btVoltarMenuPrincipal
        btVoltarMenuPrincipal = new JButton();
        btVoltarMenuPrincipal.setText(Constantes.COMUM_BOTAO_VOLTAR_MENU_PRINCIPAL);
        btVoltarMenuPrincipal.addActionListener(btManager);

        //Configuracao painelBotoes
        this.painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(true);
        painelBotoes.add(btCadastraFuncionario);
        painelBotoes.add(btEditaFuncionario);
        painelBotoes.add(btDeletaFuncionario);
        painelBotoes.add(btVoltarMenuPrincipal);
        constraints.gridy = 11;
        container.add(painelBotoes, constraints);

        setSize(700, 320);
        setLocationRelativeTo(null);

    }

    // ================== CONFIGURAÇÃO DE TELA ==================
    /**
     * Atualiza a tela de modo a mostrar todos os funcionarios cadastrados até
     * então.
     */
    public void updateData() {

        //Configuracao modelTbItens
        DefaultTableModel modelTbItens = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelTbItens.addColumn(Constantes.COMUM_NOME);
        modelTbItens.addColumn(Constantes.COMUM_MATRICULA);
        modelTbItens.addColumn(Constantes.COMUM_DATA_NASCIMENTO);
        modelTbItens.addColumn(Constantes.COMUM_TELEFONE);
        modelTbItens.addColumn(Constantes.COMUM_SALARIO);
        modelTbItens.addColumn(Constantes.COMUM_CARGO);

        Collection<Funcionario> listaFuncionarios = ControladorFuncionario.getInstance().getListaFuncionarios();
        tbItens.removeAll();
        for (Funcionario funcionario : listaFuncionarios) {
            modelTbItens.addRow(new Object[]{
                funcionario.getNome(),
                funcionario.getMatricula(),
                funcionario.getDataNascimento(),
                funcionario.getTelefone(),
                funcionario.getSalario(),
                funcionario.getCargo().getNome()
            });
        }
        tbItens.setModel(modelTbItens);

        this.repaint();
    }

    /**
     * Exibe a tela.
     */
    public void exibeMenuFuncionario() {
        updateData();
        setVisible(true);
    }

    // ================== EXIBIÇÃO DE MENSAGENS ==================
    /**
     * Exibe a mensagem de que o funcionario foi deletado com sucesso.
     */
    public void exibeFuncionarioDeletadoComSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_DELETADO_SUCESSO,
                Constantes.GERENCIAR_FUNCIONARIO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    /**
     * Exibe a mensagem de que nenhum funcionario foi selecionado.
     */
    public void exibeFuncionarioNaoSelecionado() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_NADA_SELECIONADO,
                Constantes.GERENCIAR_FUNCIONARIO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );

    }

    // ================== GERENCIADOR DE BOTÕES ==================
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCadastraFuncionario)) {
                ControladorFuncionario.getInstance().exibeCadastraFuncionario();
            }
            if (e.getSource().equals(btEditaFuncionario)) {
                ControladorFuncionario.getInstance().exibeEditarFuncionarioSelecionado(tbItens.getSelectedRow());
            }
            if (e.getSource().equals(btDeletaFuncionario)) {
                ControladorFuncionario.getInstance().deletaFuncionarioSelecionado(tbItens.getSelectedRow());
                updateData();
            }
            if (e.getSource().equals(btVoltarMenuPrincipal)) {
                setVisible(false);
                ControladorFuncionario.getInstance().voltarMenuPrincipal();
            }

        }

    }

}
