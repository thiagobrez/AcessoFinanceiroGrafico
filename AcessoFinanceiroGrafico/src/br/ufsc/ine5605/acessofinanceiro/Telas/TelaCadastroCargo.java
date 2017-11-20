/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorCargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel lbFormatoHora;
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
    private JPanel painelFormatoHora;
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
        //Configuracao container
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //Configuracao comboManager
        GerenciadorCombos comboManager = new GerenciadorCombos();

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 1;

        //Configuracao nome
        lbNome = new JLabel(Constantes.CARGO_NOME);
        tfNome = new JTextField(20);
        this.painelNome = new JPanel();
        painelNome.setLayout(new BoxLayout(painelNome, BoxLayout.LINE_AXIS));
        this.painelNome.setVisible(true);
        painelNome.add(lbNome);
        painelNome.add(tfNome);

        //Configuracao codigo
        lbCodigo = new JLabel(Constantes.CARGO_CODIGO);
        tfCodigo = new JTextField(20);
        this.painelCodigo = new JPanel();
        painelCodigo.setLayout(new BoxLayout(painelCodigo, BoxLayout.LINE_AXIS));
        this.painelCodigo.setVisible(true);
        painelCodigo.add(lbCodigo);
        painelCodigo.add(tfCodigo);

        //Configuracao tipoCargo
        lbTipoCargo = new JLabel(Constantes.CARGO_TIPO);
        String[] tipos = {
            Constantes.CARGO_TIPO_GERENCIAL,
            Constantes.CARGO_TIPO_COMERCIAL,
            Constantes.CARGO_TIPO_ESPECIAL,
            Constantes.CARGO_TIPO_COMUM,
            Constantes.CARGO_TIPO_SEM_ACESSO
        };
        comboTipos = new JComboBox(tipos);
        comboTipos.addActionListener(comboManager);
        this.painelTipo = new JPanel();
        painelTipo.setLayout(new BoxLayout(painelTipo, BoxLayout.LINE_AXIS));
        this.painelTipo.setVisible(true);
        painelTipo.add(lbTipoCargo);
        painelTipo.add(comboTipos);

        //Configuracao formatoHora
        lbFormatoHora = new JLabel(Constantes.CARGO_FORMATO_HORA);
        this.painelFormatoHora = new JPanel();
        painelFormatoHora.setLayout(new BoxLayout(painelFormatoHora, BoxLayout.LINE_AXIS));
        this.painelFormatoHora.setVisible(true);
        painelFormatoHora.add(lbFormatoHora);

        //Configuracao horaInicioManha
        lbHoraInicioManha = new JLabel(Constantes.CARGO_INICIO_MANHA + ": ");
        tfHoraInicioManha = new JTextField(5);
        this.painelHoraInicioManha = new JPanel();
        painelHoraInicioManha.setLayout(new BoxLayout(painelHoraInicioManha, BoxLayout.LINE_AXIS));
        //this.painelHoraInicioManha.setVisible(true);
        painelHoraInicioManha.add(lbHoraInicioManha);
        painelHoraInicioManha.add(tfHoraInicioManha);

        //Configuracao horaFimManha
        lbHoraFimManha = new JLabel(Constantes.CARGO_FIM_MANHA + ": ");
        tfHoraFimManha = new JTextField(5);
        this.painelHoraFimManha = new JPanel();
        painelHoraFimManha.setLayout(new BoxLayout(painelHoraFimManha, BoxLayout.LINE_AXIS));
        painelHoraFimManha.add(lbHoraFimManha);
        painelHoraFimManha.add(tfHoraFimManha);

        //Configuracao horaInicioTarde
        lbHoraInicioTarde = new JLabel(Constantes.CARGO_INICIO_TARDE + ": ");
        tfHoraInicioTarde = new JTextField(5);
        this.painelHoraInicioTarde = new JPanel();
        painelHoraInicioTarde.setLayout(new BoxLayout(painelHoraInicioTarde, BoxLayout.LINE_AXIS));
        painelHoraInicioTarde.add(lbHoraInicioTarde);
        painelHoraInicioTarde.add(tfHoraInicioTarde);

        //Configuracao horaFimTarde
        lbHoraFimTarde = new JLabel(Constantes.CARGO_FIM_TARDE + ": ");
        tfHoraFimTarde = new JTextField(5);
        this.painelHoraFimTarde = new JPanel();
        painelHoraFimTarde.setLayout(new BoxLayout(painelHoraFimTarde, BoxLayout.LINE_AXIS));
        painelHoraFimTarde.add(lbHoraFimTarde);
        painelHoraFimTarde.add(tfHoraFimTarde);

        //Configuracao horaInicioEspecial
        lbHoraInicioEspecial = new JLabel(Constantes.CARGO_INICIO_ESPECIAL + ": ");
        tfHoraInicioEspecial = new JTextField(5);
        this.painelHoraInicioEspecial = new JPanel();
        painelHoraInicioEspecial.setLayout(new BoxLayout(painelHoraInicioEspecial, BoxLayout.LINE_AXIS));
        painelHoraInicioEspecial.add(lbHoraInicioEspecial);
        painelHoraInicioEspecial.add(tfHoraInicioEspecial);

        //Configuracao horaFimEspecial
        lbHoraFimEspecial = new JLabel(Constantes.CARGO_FIM_ESPECIAL + ": ");
        tfHoraFimEspecial = new JTextField(5);
        this.painelHoraFimEspecial = new JPanel();
        painelHoraFimEspecial.setLayout(new BoxLayout(painelHoraFimEspecial, BoxLayout.LINE_AXIS));
        painelHoraFimEspecial.add(lbHoraFimEspecial);
        painelHoraFimEspecial.add(tfHoraFimEspecial);

        //Configuracao botoes
        btCadastrar = new JButton();
        btCadastrar.setText(Constantes.COMUM_BOTAO_CADASTRAR);
        btCadastrar.addActionListener(btManager);
        btCancelar = new JButton();
        btCancelar.setText(Constantes.COMUM_BOTAO_CANCELAR);
        btCancelar.addActionListener(btManager);
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(true);
        painelBotoes.add(btCadastrar);
        painelBotoes.add(btCancelar);

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
        painelPrincipal.add(painelFormatoHora, constraints);
        constraints.gridy = 5;
        painelPrincipal.add(painelHoraInicioManha, constraints);
        constraints.gridy = 6;
        painelPrincipal.add(painelHoraFimManha, constraints);
        constraints.gridy = 7;
        painelPrincipal.add(painelHoraInicioTarde, constraints);
        constraints.gridy = 8;
        painelPrincipal.add(painelHoraFimTarde, constraints);
        constraints.gridy = 9;
        painelPrincipal.add(painelHoraInicioEspecial, constraints);
        constraints.gridy = 10;
        painelPrincipal.add(painelHoraFimEspecial, constraints);
        constraints.gridy = 11;
        painelPrincipal.add(painelBotoes, constraints);
        constraints.gridy = 12;
        container.add(painelPrincipal, constraints);

        //Configuracao JFrame
        setSize(500, 480);
        setLocationRelativeTo(null);
    }

    public void updateData(String tipoCargo) {
        switch (tipoCargo) {
            case Constantes.CARGO_TIPO_COMUM:
                painelFormatoHora.setVisible(true);
                painelHoraInicioManha.setVisible(true);
                painelHoraFimManha.setVisible(true);
                painelHoraInicioTarde.setVisible(true);
                painelHoraFimTarde.setVisible(true);
                painelHoraInicioEspecial.setVisible(false);
                painelHoraFimEspecial.setVisible(false);
                break;
            case Constantes.CARGO_TIPO_ESPECIAL:
                painelFormatoHora.setVisible(true);
                painelHoraInicioManha.setVisible(true);
                painelHoraFimManha.setVisible(true);
                painelHoraInicioTarde.setVisible(true);
                painelHoraFimTarde.setVisible(true);
                painelHoraInicioEspecial.setVisible(true);
                painelHoraFimEspecial.setVisible(true);
                break;
            default:
                painelFormatoHora.setVisible(false);
                painelHoraInicioManha.setVisible(false);
                painelHoraFimManha.setVisible(false);
                painelHoraInicioTarde.setVisible(false);
                painelHoraFimTarde.setVisible(false);
                painelHoraInicioEspecial.setVisible(false);
                painelHoraFimEspecial.setVisible(false);
                break;
        }
    }

    public void exibeMenuCadastroCargo() {
        updateData(Constantes.CARGO_TIPO_GERENCIAL);
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
				if(ControladorCargo.getInstance().verificaCodigo(tfCodigo.getText()) &&
						ControladorCargo.getInstance().verificaNome(tfNome.getText())) {
					ControladorCargo.getInstance().incluiCargo(Integer.parseInt(tfCodigo.getText()),
							tfNome.getText(), comboTipos.getSelectedItem().toString(), tfHoraInicioManha.getText(),
							tfHoraFimManha.getText(), tfHoraInicioTarde.getText(), tfHoraFimTarde.getText(),
							tfHoraInicioEspecial.getText(), tfHoraFimEspecial.getText(), false);
					ControladorCargo.getInstance().updateCargos();
					setVisible(false);
				}
            }

            if (e.getSource().equals(btCancelar)) {
                setVisible(false);
            }

        }

    }

    private class GerenciadorCombos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(comboTipos)) {
                updateData(comboTipos.getSelectedItem().toString());
            }

        }

    }

}
