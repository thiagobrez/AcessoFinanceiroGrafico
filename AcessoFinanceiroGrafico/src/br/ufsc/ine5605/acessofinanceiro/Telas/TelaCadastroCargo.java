/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorCargo;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorPrincipal;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
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
 * @author bruno.bertozzo
 */
public class TelaCadastroCargo extends JFrame {
    
    private GerenciadorBotoes btManager;
    private ControladorCargo controlador;
    private GridBagConstraints constraints;
    private JLabel lbNome;
    private JLabel lbCodigo;
    private JTextField tfNome;
    private JTextField tfCodigo;
    private JPanel painelPrincipal;
    private JPanel painelNome;
    private JPanel painelCodigo;
    private JPanel painelTipo;
    private JPanel painelBotoes;
    private JComboBox comboTipos;
    private List<Cargo> cargos;
    private List<String> cargosTipos;
    private JButton btCancelar;
    private JButton btCadastrar;
    
    public TelaCadastroCargo(ControladorCargo owner) {
        super(Constantes.CARGO_CADASTRAR);
        this.controlador = owner;
        this.btManager = new TelaCadastroCargo.GerenciadorBotoes();

        configuraTela();
    }
    private void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        GerenciadorCombos comboManager = new GerenciadorCombos();
        
        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 1;

        //Configuracao lbNome
        lbNome = new JLabel(Constantes.CARGO_NOME);

        //Configuracao tfNome
        tfNome = new JTextField(20);

        //Configuracao painelNome
        this.painelNome = new JPanel();
        painelNome.setLayout(new BoxLayout(painelNome, BoxLayout.LINE_AXIS));
        this.painelNome.setVisible(true);
        painelNome.add(lbNome);
        painelNome.add(tfNome);
        
        //Configuracao lbCodigo
        lbCodigo = new JLabel(Constantes.CARGO_CODIGO);

        //Configuracao tfCodigo
        tfCodigo = new JTextField(20);

        //Configuracao painelMatricula
        this.painelCodigo = new JPanel();
        painelCodigo.setLayout(new BoxLayout(painelCodigo, BoxLayout.LINE_AXIS));
        this.painelCodigo.setVisible(true);
        painelCodigo.add(lbCodigo);
        painelCodigo.add(tfCodigo);
        
        
        
//        //Configuracao comboTipos
//        this.cargos = new ArrayList<>();
//        this.cargosTipos = new ArrayList<>();
//        comboTipos = new JComboBox(cargosTipos.toArray());
//        comboTipos.addActionListener(comboManager);
//
//        //Configuracao painelTipo
//        this.painelTipo = new JPanel();
//        painelTipo.setLayout(new BoxLayout(painelTipo, BoxLayout.LINE_AXIS));
//        this.painelTipo.setVisible(true);
//        painelTipo.add(comboTipos);
        
        
//        //Configuracao lbTelefone
//        lbTelefone = new JLabel(Constantes.GERENCIAR_FUNCIONARIO_TELEFONE);
//
//        //Configuracao tfTelefone
//        tfTelefone = new JTextField(20);
//
//        //Configuracao painelTelefone
//        this.painelTelefone = new JPanel();
//        painelTelefone.setLayout(new BoxLayout(painelTelefone, BoxLayout.LINE_AXIS));
//        this.painelTelefone.setVisible(true);
//        painelTelefone.add(lbTelefone);
//        painelTelefone.add(tfTelefone);
        
        
        //Configura btCancelar
        btCancelar = new JButton();
        btCancelar.setText(Constantes.COMUM_BOTAO_CANCELAR);
        btCancelar.addActionListener(btManager);

        //Configura btCadastrar
        btCadastrar = new JButton();
        btCadastrar.setText(Constantes.COMUM_BOTAO_CADASTRAR);
        btCadastrar.addActionListener(btManager);
        container.add(btCadastrar);

        //Configuracao painelBotoes
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(true);
        painelBotoes.add(btCancelar);
        painelBotoes.add(btCadastrar);
        
        //Configuracao painelPrincipal
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setVisible(true);
        painelPrincipal.add(painelNome);
        constraints.gridy = 2;
        painelPrincipal.add(painelCodigo, constraints);
        constraints.gridy = 3;
        painelPrincipal.add(painelTipo, constraints);
        constraints.gridy = 4;
        //painelPrincipal.add(painelTelefone, constraints);
        //constraints.gridy = 5;
        //painelPrincipal.add(painelSalario, constraints);
        //constraints.gridy = 6;
        //painelPrincipal.add(painelCargo, constraints);
        //constraints.gridy = 7;
        painelPrincipal.add(painelBotoes, constraints);
        constraints.gridy = 5;
        container.add(painelPrincipal, constraints);

        setSize(720, 480);
        setLocationRelativeTo(null);
    }
    
    public void updateData() {

        //Configuracao comboCargos
        this.cargos = ControladorPrincipal.getInstance().getListaCargos();
        comboTipos.removeAllItems();
        for (Cargo cargo : cargos) {
            comboTipos.addItem(cargo);

        }

        //limpa os textsFields
        limpaTextFields();

        this.repaint();
    }
    
    public void exibeMenuCadastroCargo() {
        updateData();
        setVisible(true);

    }
    
   // ############################### Mensagens ###############################
    
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
        
    public void limpaTextFields() {
        tfCodigo.setText("");
        tfNome.setText("");
        //tfDataNascimento.setText("");
        //tfTipo.setText("");
        //tfSalario.setText("");
    }
    
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(btCadastrar)) {
                
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
