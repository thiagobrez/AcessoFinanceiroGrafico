/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorCargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.CargoHorarioEspecial;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
public class TelaEditarCargo extends JFrame {

    private GerenciadorBotoes btManager;
    private GridBagConstraints constraints;
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
    private JButton btEditar;
    private int codigoAntigo;

    public TelaEditarCargo() {
        super(Constantes.GERENCIAR_FUNCIONARIO_EDITAR);
        this.btManager = new GerenciadorBotoes();
        configuraTela();
    }

	/**
	 * Configura elementos que aparecerao na interface grafica.
	 */
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

        //Configura btEditar
        btEditar = new JButton();
        btEditar.setText(Constantes.COMUM_BOTAO_EDITAR);
        btEditar.addActionListener(btManager);

        //Configuracao painelBotoes
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(true);
        painelBotoes.add(btCancelar);
        painelBotoes.add(btEditar);

		 //Configuracao formatoHora
        lbFormatoHora = new JLabel(Constantes.CARGO_FORMATO_HORA);
        this.painelFormatoHora = new JPanel();
        painelFormatoHora.setLayout(new BoxLayout(painelFormatoHora, BoxLayout.LINE_AXIS));
        this.painelFormatoHora.setVisible(true);
        painelFormatoHora.add(lbFormatoHora);
		
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

        setSize(500, 480);
        setLocationRelativeTo(null);
    }

	/**
	 * Atualiza inputs da tabela de cargos.
	 * @param tipoCargo tipo do cargo para filtrar os inputs
	 */
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

    /**
     * Atualiza a tela e faz com que seja exibida, onde cada campo de informação
     * do cargo ja esteja preenchida com as informações do cargo previamente
     * selecionado pelo usuário.
     *
     * @param cargo
     */
    public void exibeMenuEditaCargo(Cargo cargo) {
        updateData(Constantes.CARGO_TIPO_GERENCIAL);
        setVisible(true);
        codigoAntigo = cargo.getCodigo();
        tfCodigo.setText(Integer.toString(cargo.getCodigo()));
        tfNome.setText(cargo.getNome());

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        tfHoraInicioManha.setText(df.format(cargo.getHoraInicioManha()).toString());
        tfHoraFimManha.setText(df.format(cargo.getHoraFimManha()).toString());
        tfHoraInicioTarde.setText(df.format(cargo.getHoraInicioTarde()).toString());
        tfHoraFimTarde.setText(df.format(cargo.getHoraFimTarde()).toString());
		if(cargo instanceof CargoHorarioEspecial) {
			tfHoraInicioEspecial.setText(df.format(((CargoHorarioEspecial) cargo).getHoraInicioEspecial()).toString());
			tfHoraFimEspecial.setText(df.format(((CargoHorarioEspecial) cargo).getHoraFimEspecial()).toString());	
		}

    }

    // ================== EXIBIÇÃO DE MENSAGENS ==================
	
    /**
     * Exibe a mensagem de que o cargo foi editado com sucesso.
     */
    public void exibeCargoEditadoComSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.CARGO_EDITADO_SUCESSO,
                Constantes.CARGO_EDITAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

	/**
	 * Exibe mensagem indicando que o nome eh invalido pois nao atende ao tamanho
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
	 * Gerencia as acoes nos botoes.
	 */
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(btEditar)) {
                ControladorCargo.getInstance().editaCargo(codigoAntigo, Integer.parseInt(tfCodigo.getText()),
                        tfNome.getText(), comboTipos.getSelectedItem().toString(), tfHoraInicioManha.getText(), tfHoraFimManha.getText(),
                        tfHoraInicioTarde.getText(), tfHoraFimTarde.getText(), tfHoraInicioEspecial.getText(),
                        tfHoraInicioEspecial.getText());
                setVisible(false);
            }

            if (e.getSource().equals(btCancelar)) {
                setVisible(false);
            }
        }

    }

	/**
	 * Gerencia as acoes nos comboBox.
	 */
    private class GerenciadorCombos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(comboTipos)) {
                updateData(comboTipos.getSelectedItem().toString());
            }
        }

    }

}
