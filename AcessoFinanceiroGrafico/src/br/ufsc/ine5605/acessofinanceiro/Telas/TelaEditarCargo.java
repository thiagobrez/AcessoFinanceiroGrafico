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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author bruno.bertozzo
 */
public class TelaEditarCargo extends JFrame {
	
    private ControladorCargo controlador;
    private GerenciadorBotoes btManager;
    
    public TelaEditarCargo(ControladorCargo owner) {
        super(Constantes.GERENCIAR_FUNCIONARIO_EDITAR);
        this.controlador = owner;
        this.btManager = new TelaEditarFuncionario.GerenciadorBotoes();

        configuraTela();
    }
	
	public void exibeMenuEditaCargo(Cargo cargo) {
//        updateData();
//        setVisible(true);
//        codigoAntigo = cargo.getMatricula();
		
		// Setar textFields para ja virem com os dados do cargo
//        tfMatricula.setText(Integer.toString(funcionario.getMatricula()));
//        tfNome.setText(funcionario.getNome());
//        tfDataNascimento.setText(funcionario.getDataNascimento());
//        tfTelefone.setText(Integer.toString(funcionario.getTelefone()));
//        tfSalario.setText(Integer.toString(funcionario.getSalario()));

    }

    private void configuraTela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btConfirmar)) {
                ControladorFuncionario.getInstance().editaFuncionario(codigoAntigo, Integer.parseInt(tfCodigo.getText()),
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
