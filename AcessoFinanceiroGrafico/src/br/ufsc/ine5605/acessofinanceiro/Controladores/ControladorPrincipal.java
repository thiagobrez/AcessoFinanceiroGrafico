/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Telas.TelaPrincipal;

/**
 *
 * @author bruno
 */
public class ControladorPrincipal {

    private static ControladorPrincipal controladorPrincipal;
    public boolean primeiraVez;

    public TelaPrincipal telaPrincipal;

    private ControladorPrincipal() {
        this.telaPrincipal = new TelaPrincipal();
        this.primeiraVez = false;
    }

    public static ControladorPrincipal getInstance() {
        if (controladorPrincipal == null) {
            controladorPrincipal = new ControladorPrincipal();
        }
        return controladorPrincipal;
    }

    /**
     * Instancia cargo indefinido (codigo = 0, sem função gerencial e sem acesso
     * ao financeiro) e solicita exibicao do menu principal.
     */
    public void inicia() {
        ControladorCargo.getInstance().criaCargoPadrao();
        exibeMenuPrincipal();
    }

    /**
     * Exibe o menu principal do sistema.
     */
    public void exibeMenuPrincipal() {
        telaPrincipal.exibeMenuPrincipal();
        if (!primeiraVez) {
            telaPrincipal.exibeBemVindo();
            primeiraVez = true;
        }
    }

    /**
     * Chama classe que controla o acesso ao financeiro.
     */
    public void acessarFinanceiro() {
        ControladorAcesso.getInstance().exibePainelAcesso();
    }

    /**
     * Chama classe que controla o gerenciamento de funcionarios.
     */
    public void gerenciarFuncionarios() {
        ControladorFuncionario.getInstance().exibeMenuFuncionario();
    }

    /**
     * Chama classe que controla o gerenciamento de cargos.
     */
    public void gerenciarCargos() {
        ControladorCargo.getInstance().exibeMenuCargo();
    }

    /**
     * Chama classe que controla o gerenciamento da data do sistema.
     */
    public void gerenciarData() {
        ControladorDataSistema.getInstance().exibeMenuDataSistema();
    }

    /**
     * Chama classe que controla o registro de acesso negado.
     */
    public void emitirRelatorio() {
        ControladorRegistroAcessoNegado.getInstance().exibeRelatorio();
    }

}
