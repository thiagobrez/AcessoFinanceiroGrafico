/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorDataSistema;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author vladimir
 */
public class TelaAlterarDataSistema extends JFrame {

    private ControladorDataSistema controlador;

    public TelaAlterarDataSistema(ControladorDataSistema owner) {
        super(Constantes.DATA_SISTEMA_TITULO);
        this.controlador = owner;
        try {
            configuraTela();
        } catch (ParseException e) {
        }
    }

    private void configuraTela() throws ParseException {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        setSize(500, 250);
        setLocationRelativeTo(null);
    }

    public void alterarDataSistema() {
        setVisible(true);
    }

}
