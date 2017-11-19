/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorCargo;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorPrincipal;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bruno
 */
public class TelaCargo extends JFrame {
    
    private ControladorCargo owner;
    private GerenciadorBotoes btManager;
    private GerenciadorCombos cbManager;
    private GridBagConstraints constraints;
    private JTable tbItens;
    private JScrollPane spBaseTabela;
    private JButton btCadastraCargo;
    private JButton btEditaCargo;
    private JButton btDeletaCargo;
    private JButton btVoltarMenuPrincipal;
    
    public TelaCargo(ControladorCargo owner) {
        super(Constantes.CARGO_TITULO);
        this.owner = owner;
        this.btManager = new GerenciadorBotoes();
        this.cbManager = new GerenciadorCombos();
        configuraTela();
    }
    
    public void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 10;
        constraints.gridheight = 10;
        constraints.gridx = 0; 
        constraints.gridy = 6;
        
        //Configuracao tbItens
        this.tbItens = new JTable();
        tbItens.setPreferredScrollableViewportSize(new Dimension(500, 300));
        tbItens.setFillsViewportHeight(true);
        spBaseTabela = new JScrollPane(tbItens);
        container.add(spBaseTabela, constraints);

        //Configura btCadastraFuncionario
        btCadastraCargo = new JButton();
        btCadastraCargo.setText(Constantes.CARGO_CADASTRAR);
        btCadastraCargo.addActionListener(btManager);
        container.add(btCadastraCargo);

        //Configura btEditaFuncionario
        btEditaCargo = new JButton();
        btEditaCargo.setText(Constantes.CARGO_EDITAR);
        btEditaCargo.addActionListener(btManager);
        container.add(btEditaCargo);

        //Configura btDeletaFuncionario
        btDeletaCargo = new JButton();
        btDeletaCargo.setText(Constantes.CARGO_DELETAR);
        btDeletaCargo.addActionListener(btManager);
        container.add(btDeletaCargo);

        //Configura btVoltarMenuPrincipal
        btVoltarMenuPrincipal = new JButton();
        btVoltarMenuPrincipal.setText(Constantes.COMUM_BOTAO_VOLTAR_MENU_PRINCIPAL);
        btVoltarMenuPrincipal.addActionListener(btManager);
        constraints.gridx = 12;
        constraints.gridy = 12;
        container.add(btVoltarMenuPrincipal, constraints);

        setSize(900, 500);
        setLocationRelativeTo(null);
    }
    
    public void updateData() {

        //Configuracao modelTbItens
        DefaultTableModel modelTbItens = new DefaultTableModel();
        modelTbItens.addColumn(Constantes.COMUM_NOME);
        modelTbItens.addColumn(Constantes.COMUM_CODIGO);
        modelTbItens.addColumn(Constantes.CARGO_EH_GERENCIAL);
        modelTbItens.addColumn(Constantes.CARGO_INICIO_MANHA);
        modelTbItens.addColumn(Constantes.CARGO_FIM_MANHA);
        modelTbItens.addColumn(Constantes.CARGO_INICIO_TARDE);
        modelTbItens.addColumn(Constantes.CARGO_FIM_TARDE);

        Collection<Cargo> listaCargos = ControladorPrincipal.getInstance().getListaCargos();
        tbItens.removeAll();
        for (Cargo cargo : listaCargos) {
            modelTbItens.addRow(new Object[] {
                cargo.getNome(),
                cargo.getCodigo(),
                cargo.ehGerencial(),
                cargo.temAcessoAoFinanceiro(),
                cargo.getHoraInicioManha(),
                cargo.getHoraFimManha(),
                cargo.getHoraInicioTarde(),
                cargo.getHoraFimTarde(),
                
            });
        }
        tbItens.setModel(modelTbItens);

        this.repaint();
    }
    
    public void exibeMenuCargo() {
        updateData();
        setVisible(true);
    }
	
    public void exibeCargoDeletadoComSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_DELETADO_SUCESSO,
                Constantes.CARGO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    public void exibeCargoNaoSelecionado() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_NADA_SELECIONADO,
                Constantes.CARGO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }
    
    public void mensagemCargoCadastrado() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_CADASTRADO_SUCESSO,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }
    
	public void exibeErroConstantesFormatador() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.FORMATADOR_ERRO_CONSTANTE,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }
	
	public void mensagemCargoDeletadoSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_DELETADO_SUCESSO,
                Constantes.CARGO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }
	
    public void mensagemErroCodigoJaCadastrado() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_DELETADO_SUCESSO,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }      
	
	public void mensagemNomeInvalidoLetras() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_NOME_INVALIDO_LETRAS,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }
	
	public void mensagemNomeInvalidoTamanho() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_NOME_INVALIDO_TAMANHO,
                Constantes.CARGO_CADASTRAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }
	
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCadastraCargo)) {
                ControladorCargo.getInstance().exibeCadastraCargo();
            }
            if (e.getSource().equals(btEditaCargo)) {
                ControladorCargo.getInstance().exibeEditarCargoSelecionado(tbItens.getSelectedRow());
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
    
    private class GerenciadorCombos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

}
