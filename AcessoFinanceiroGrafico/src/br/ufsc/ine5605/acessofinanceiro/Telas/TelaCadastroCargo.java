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
import br.ufsc.ine5605.acessofinanceiro.Modelos.Motivo;
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
    private TelaCargo telaCargo;
    private JLabel lbNome;
    private JLabel lbCodigo;
    private JLabel lbTipoCargo;
    private JLabel lbHoraInicioManha;
    private JLabel lbHoraFimManha;
    private JLabel lbHoraInicioTarde;
    private JLabel lbHoraFimTarde;
    private JLabel lbHoraFimEspecial;
    private JLabel lbHoraInicioEspecial;
    private JTextField tfNome;
    private JTextField tfCodigo;
    private JTextField tfHoraInicioManha;
    private JTextField tfHoraFimManha;
    private JTextField tfHoraInicioTarde;
    private JTextField tfHoraFimTarde;
    private JTextField tfHoraInicioEspecial;
    private JTextField tfHoraFimEspecial;
    private JPanel painelPrincipal;
    private JPanel painelNome;
    private JPanel painelCodigo;
    private JPanel painelTipo;
    private JPanel painelBotoes;
    private JPanel painelHoraInicioManha;
    private JPanel painelHoraFimManha;
    private JPanel painelHoraInicioTarde;
    private JPanel painelHoraFimTarde;
    private JPanel painelHoraFimEspecial;
    private JPanel painelHoraInicioEspecial;
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

        //Configuracao painelCodigo
        this.painelCodigo = new JPanel();
        painelCodigo.setLayout(new BoxLayout(painelCodigo, BoxLayout.LINE_AXIS));
        this.painelCodigo.setVisible(true);
        painelCodigo.add(lbCodigo);
        painelCodigo.add(tfCodigo);
        
       //Configuracao lbMatricula
        lbTipoCargo = new JLabel(Constantes.CARGO_TIPO);
       
        //Configuracao comboTipos
        Object[] tipos = {
			Constantes.CARGO_TIPO_GERENCIAL,
			Constantes.CARGO_TIPO_COMERCIAL,
			Constantes.CARGO_TIPO_ESPECIAL,
			Constantes.CARGO_TIPO_COMUM,
                        Constantes.CARGO_TIPO_SEM_ACESSO
		};
        comboTipos = new JComboBox(tipos);
        comboTipos.addActionListener(comboManager);
        
        //Configuracao painelTipo
        this.painelTipo = new JPanel();
        painelTipo.setLayout(new BoxLayout(painelTipo, BoxLayout.LINE_AXIS));
        this.painelTipo.setVisible(true);
        painelTipo.add(lbTipoCargo);
        painelTipo.add(comboTipos);

        //Configuracao lbHoraInicioManha
        lbHoraInicioManha = new JLabel(Constantes.CARGO_INICIO_MANHA + ": ");

        //Configuracao tfHoraInicioManha
        tfHoraInicioManha = new JTextField(5);

        //Configuracao painelHoraInicioManha
        this.painelHoraInicioManha = new JPanel();
        painelHoraInicioManha.setLayout(new BoxLayout(painelHoraInicioManha, BoxLayout.LINE_AXIS));
        //this.painelHoraInicioManha.setVisible(true);
        painelHoraInicioManha.add(lbHoraInicioManha);
        painelHoraInicioManha.add(tfHoraInicioManha);
        
        //Configuracao lbHoraFimManha
        lbHoraFimManha = new JLabel(Constantes.CARGO_FIM_MANHA + ": ");

        //Configuracao tfHoraFimManha
        tfHoraFimManha = new JTextField(5);

        //Configuracao painelHoraFimManha
        this.painelHoraFimManha = new JPanel();
        painelHoraFimManha.setLayout(new BoxLayout(painelHoraFimManha, BoxLayout.LINE_AXIS));
        //this.painelHoraFimManha.setVisible(true);
        painelHoraFimManha.add(lbHoraFimManha);
        painelHoraFimManha.add(tfHoraFimManha);
        
        //Configuracao lbHoraInicioTarde
        lbHoraInicioTarde = new JLabel(Constantes.CARGO_INICIO_TARDE + ": ");

        //Configuracao tfHoraInicioTarde
        tfHoraInicioTarde = new JTextField(5);

        //Configuracao painelHoraInicioTarde
        this.painelHoraInicioTarde = new JPanel();
        painelHoraInicioTarde.setLayout(new BoxLayout(painelHoraInicioTarde, BoxLayout.LINE_AXIS));
        //this.painelHoraInicioTarde.setVisible(true);
        painelHoraInicioTarde.add(lbHoraInicioTarde);
        painelHoraInicioTarde.add(tfHoraInicioTarde);
        
        //Configuracao lbHoraFimTarde
        lbHoraFimTarde = new JLabel(Constantes.CARGO_FIM_TARDE + ": ");

        //Configuracao tfHoraFimTarde
        tfHoraFimTarde = new JTextField(5);

        //Configuracao painelHoraFimTarde
        this.painelHoraFimTarde = new JPanel();
        painelHoraFimTarde.setLayout(new BoxLayout(painelHoraFimTarde, BoxLayout.LINE_AXIS));
        //this.painelHoraFimTarde.setVisible(true);
        painelHoraFimTarde.add(lbHoraFimTarde);
        painelHoraFimTarde.add(tfHoraFimTarde);
        
        //Configuracao lbHoraInicioEspecial
        lbHoraInicioEspecial = new JLabel(Constantes.CARGO_INICIO_ESPECIAL + ": ");

        //Configuracao tfHoraInicioEspecial
        tfHoraInicioEspecial = new JTextField(5);

        //Configuracao painelHoraInicioEspecial
        this.painelHoraInicioEspecial = new JPanel();
        painelHoraInicioEspecial.setLayout(new BoxLayout(painelHoraInicioEspecial, BoxLayout.LINE_AXIS));
        //this.painelHoraFimTarde.setVisible(true);
        painelHoraInicioEspecial.add(lbHoraInicioEspecial);
        painelHoraInicioEspecial.add(tfHoraInicioEspecial);
        
        //Configuracao lbHoraFimEspecial
        lbHoraFimEspecial = new JLabel(Constantes.CARGO_FIM_ESPECIAL + ": ");

        //Configuracao tfHoraFimEspecial
        tfHoraFimEspecial = new JTextField(5);

        //Configuracao painelHoraFimEspecial
        this.painelHoraFimEspecial = new JPanel();
        painelHoraFimEspecial.setLayout(new BoxLayout(painelHoraFimEspecial, BoxLayout.LINE_AXIS));
        //this.painelHoraFimTarde.setVisible(true);
        painelHoraFimEspecial.add(lbHoraFimEspecial);
        painelHoraFimEspecial.add(tfHoraFimEspecial);
        
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
        painelPrincipal.add(painelHoraInicioManha, constraints);
        constraints.gridy = 5;
        painelPrincipal.add(painelHoraFimManha, constraints);
        constraints.gridy = 6;
        painelPrincipal.add(painelHoraInicioTarde, constraints);
        constraints.gridy = 7;
        painelPrincipal.add(painelHoraFimTarde, constraints);
        constraints.gridy = 8;
        painelPrincipal.add(painelHoraInicioEspecial, constraints);
        constraints.gridy = 9;
        painelPrincipal.add(painelHoraFimEspecial, constraints);
        constraints.gridy = 10;
        painelPrincipal.add(painelBotoes, constraints);
        constraints.gridy = 11;
        container.add(painelPrincipal, constraints);

        setSize(720, 480);
        setLocationRelativeTo(null);
    }
    
    public void updateData() {

//        //Configuracao comboCargos
//        comboTipos.removeAllItems();
//        for (Cargo cargo : cargos) {
//            comboCargos.addItem(cargo);
//
//        }
//
//        //limpa os textsFields
//        limpaTextFields();
//
//        this.repaint();

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
        tfNome.setText("");
        tfCodigo.setText("");
        tfHoraInicioManha.setText("");
        tfHoraFimManha.setText("");
        tfHoraInicioTarde.setText("");
        tfHoraFimTarde.setText("");
        tfHoraInicioEspecial.setText("");
        tfHoraFimEspecial.setText("");
    }
    
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(btCadastrar)) {
                ControladorCargo.getInstance().incluiCargo(Integer.parseInt(tfCodigo.getText()),
                        tfNome.getText(), comboTipos.getSelectedItem().toString(), tfHoraInicioManha.getText(), tfHoraFimManha.getText(),
                        tfHoraInicioTarde.getText(), tfHoraFimTarde.getText(), tfHoraInicioEspecial.getText(),
                        tfHoraInicioEspecial.getText());
                ControladorCargo.getInstance().updateCargos();
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
//            switch(comboTipos.getSelectedIndex()) {
//                case 0:
//                    break;
//                case 1: 
//                    constraints.gridy = 4;
//                    painelPrincipal.add(painelHoraInicioManha, constraints);
//                    constraints.gridy = 5;
//                    painelPrincipal.add(painelHoraFimManha, constraints);
//                    constraints.gridy = 6;
//                    painelPrincipal.add(painelHoraInicioTarde, constraints);
//                    constraints.gridy = 7;
//                    painelPrincipal.add(painelHoraFimTarde, constraints);
//                    constraints.gridy = 8;
//                    repaint();
//                default:
//                    break;
                
//                constraints.gridy = 4;
//                painelPrincipal.add(painelHoraInicioManha, constraints);
//                constraints.gridy = 5;
//                painelPrincipal.add(painelHoraFimManha, constraints);
//                constraints.gridy = 6;
//                painelPrincipal.add(painelHoraInicioTarde, constraints);
//                constraints.gridy = 7;
//                painelPrincipal.add(painelHoraFimTarde, constraints);
//                constraints.gridy = 8;
//                painelPrincipal.add(painelHoraInicioEspecial, constraints);
//                constraints.gridy = 9;
//                painelPrincipal.add(painelHoraFimEspecial, constraints);
            }
//                
//            } if(comboTipos.getSelectedItem().toString().equals("Comercial")) {
////                painelPrincipal.add(painelHoraInicioManha, constraints);
////                constraints.gridy = 5;
////                painelPrincipal.add(painelHoraFimManha, constraints);
////                constraints.gridy = 6;
////                painelPrincipal.add(painelHoraInicioTarde, constraints);
////                constraints.gridy = 7;
////                painelPrincipal.add(painelHoraFimTarde, constraints);
////                painelHoraInicioManha.setVisible(true);
////                painelHoraFimManha.setVisible(true);
////                painelHoraInicioTarde.setVisible(true);
////                painelHoraFimTarde.setVisible(true);
//            } if(comboTipos.getSelectedItem().toString().equals("Especial")) {
//                
//            } if(comboTipos.getSelectedItem().toString().equals("Comum")) {
//                
//            } if (comboTipos.getSelectedItem().toString().equals("Comum")) {
//                
//            }
    }
    
    

}
