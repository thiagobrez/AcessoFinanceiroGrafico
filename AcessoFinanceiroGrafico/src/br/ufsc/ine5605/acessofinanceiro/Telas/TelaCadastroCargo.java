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
import java.awt.GridBagConstraints;
import javax.swing.JFrame;

/**
 *
 * @author bruno.bertozzo
 */
public class TelaCadastroCargo extends JFrame {
    
    private TelaCadastroFuncionario.GerenciadorBotoes btManager;
    private ControladorCargo controlador;
    private GridBagConstraints constraints;
    
    public TelaCadastroCargo(ControladorCargo owner) {
        super(Constantes.CARGO_CADASTRAR);
        this.controlador = owner;
        this.btManager = new TelaCadastroCargo.GerenciadorBotoes();

        configuraTela();
    }
    private void configuraTela() {
        
    }
    
    public void updateData() {

        //Configuracao comboCargos
        this.cargos = ControladorPrincipal.getInstance().getListaCargos();
        comboCargos.removeAllItems();
        for (Cargo cargo : cargos) {
            comboCargos.addItem(cargo);

        }

        //limpa os textsFields
        limpaTextFields();

        this.repaint();
    }
    
    public void exibeMenuCadastroCargo() {
        updateData();
        setVisible(true);

    }
    
    public void limpaTextFields() {
        tfMatricula.setText("");
        tfNome.setText("");
        tfDataNascimento.setText("");
        tfTelefone.setText("");
        tfSalario.setText("");
    }
    

}
