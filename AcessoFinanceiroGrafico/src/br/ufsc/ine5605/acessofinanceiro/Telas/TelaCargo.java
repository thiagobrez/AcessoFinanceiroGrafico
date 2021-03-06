/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorCargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author bruno
 */
public class TelaCargo extends JFrame {

    private GerenciadorBotoes btManager;
    private GridBagConstraints constraints;
    private JPanel painelBotoes;
    private JTable tbItens;
    private JScrollPane spBaseTabela;
    private JButton btCadastraCargo;
    private JButton btEditaCargo;
    private JButton btDeletaCargo;
    private JButton btVoltarMenuPrincipal;

    public TelaCargo() {
        super(Constantes.CARGO_TITULO);
        this.btManager = new GerenciadorBotoes();
        configuraTela();
    }

	/**
	 * Configura elementos que aparecerao na interface grafica.
	 */
    public void configuraTela() {
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
        btCadastraCargo = new JButton();
        btCadastraCargo.setText(Constantes.CARGO_CADASTRAR);
        btCadastraCargo.addActionListener(btManager);

        //Configura btEditaFuncionario
        btEditaCargo = new JButton();
        btEditaCargo.setText(Constantes.CARGO_EDITAR);
        btEditaCargo.addActionListener(btManager);

        //Configura btDeletaFuncionario
        btDeletaCargo = new JButton();
        btDeletaCargo.setText(Constantes.CARGO_DELETAR);
        btDeletaCargo.addActionListener(btManager);

        //Configura btVoltarMenuPrincipal
        btVoltarMenuPrincipal = new JButton();
        btVoltarMenuPrincipal.setText(Constantes.COMUM_BOTAO_VOLTAR_MENU_PRINCIPAL);
        btVoltarMenuPrincipal.addActionListener(btManager);
        container.add(btVoltarMenuPrincipal, constraints);

        //Configuracao painelBotoes
        this.painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(true);
        painelBotoes.add(btCadastraCargo);
        painelBotoes.add(btEditaCargo);
        painelBotoes.add(btDeletaCargo);
        painelBotoes.add(btVoltarMenuPrincipal);
        constraints.gridy = 11;
        container.add(painelBotoes, constraints);

        setSize(700, 320);
        setLocationRelativeTo(null);
    }

	/**
	 * Atualiza dados da tabela de cargos.
	 */
    public void updateData() {
        DefaultTableModel modelTbItens = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //Configuracao modelTbItens
        modelTbItens.addColumn(Constantes.COMUM_NOME);
        modelTbItens.addColumn(Constantes.COMUM_CODIGO);
        modelTbItens.addColumn(Constantes.CARGO_EH_GERENCIAL);
        modelTbItens.addColumn(Constantes.CARGO_TEM_ACESSO);
        modelTbItens.addColumn(Constantes.CARGO_INICIO_MANHA);
        modelTbItens.addColumn(Constantes.CARGO_FIM_MANHA);
        modelTbItens.addColumn(Constantes.CARGO_INICIO_TARDE);
        modelTbItens.addColumn(Constantes.CARGO_FIM_TARDE);

        Collection<Cargo> listaCargos = ControladorCargo.getInstance().getListaCargos();
        tbItens.removeAll();
        for (Cargo cargo : listaCargos) {
            modelTbItens.addRow(new Object[]{
                cargo.getNome(),
                cargo.getCodigo(),
                cargo.ehGerencial(),
                cargo.temAcessoAoFinanceiro(),
                cargo.getHoraInicioManha(),
                cargo.getHoraFimManha(),
                cargo.getHoraInicioTarde(),
                cargo.getHoraFimTarde(),});
        }
        tbItens.setModel(modelTbItens);

        this.repaint();
    }

	/**
	 * Exibe menu de cargos.
	 */
    public void exibeMenuCargo() {
        updateData();
        setVisible(true);
    }

	/**
	 * Exibe mensagem indicando que o cargo foi deletado com sucesso.
	 */
    public void exibeCargoDeletadoComSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_DELETADO_SUCESSO,
                Constantes.CARGO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

	/**
	 * Exibe mensagem indicando que nenhum cargo foi selecionado.
	 */
    public void exibeCargoNaoSelecionado() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_NADA_SELECIONADO,
                Constantes.CARGO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

	/**
	 * Exibe mensagem indicando que o cargo foi cadastrado com sucesso.
	 */
    public void mensagemCargoCadastrado() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_CADASTRADO_SUCESSO,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

	/**
	 * Exibe mensagem indicando que houve um erro interno nas constantes do
	 * formatador.
	 */
    public void exibeErroConstantesFormatador() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.FORMATADOR_ERRO_CONSTANTE,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

	/**
	 * Exibe mensagem indicando que o codigo ja foi cadastrado.
	 */
    public void mensagemErroCodigoJaCadastrado() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_CODIGO_JA_CADASTRADO,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

	/**
	 * Exibe mensagem indicando que o nome eh invalido pois nao contem somente
	 * letras.
	 */
    public void mensagemNomeInvalidoLetras() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_NOME_INVALIDO_LETRAS,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

	/**
	 * Exibe mensagem indicando que o nome eh invalido pois nao contem o tamanho
	 * minimo de 3 caracteres.
	 */
    public void mensagemNomeInvalidoTamanho() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_NOME_INVALIDO_TAMANHO,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

	/**
	 * Exibe mensagem indicando que o codigo eh invalido pois nao contem somente
	 * numeros.
	 */
	public void mensagemCodigoInvalidoLetras() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_CODIGO_INVALIDO_LETRAS,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }
	
	/**
	 * Exibe mensagem indicando que o codigo eh invalido pois excedeu o tamanho
	 * maximo de 9 caracteres.
	 */
	public void mensagemCodigoInvalidoTamanho() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_CODIGO_INVALIDO_TAMANHO,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }
	
	/**
	 * Gerencia as acoes nos botoes.
	 */
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCadastraCargo)) {
                ControladorCargo.getInstance().exibeCadastraCargo();
            }
            if (e.getSource().equals(btEditaCargo)) {
                ControladorCargo.getInstance().exibeEditarCargoSelecionado(tbItens.getSelectedRow());
                updateData();
            }
            if (e.getSource().equals(btDeletaCargo)) {
                ControladorCargo.getInstance().deletaCargoSelecionado(tbItens.getSelectedRow());
                updateData();
            }
            if (e.getSource().equals(btVoltarMenuPrincipal)) {
                setVisible(false);
                ControladorCargo.getInstance().voltarMenuPrincipal();
            }
        }
    }

}
