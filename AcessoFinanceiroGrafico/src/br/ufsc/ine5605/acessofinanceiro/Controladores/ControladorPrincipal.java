/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Motivo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.RegistroAcessoNegado;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaPrincipal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author bruno
 */
public class ControladorPrincipal {

    private static ControladorPrincipal controladorPrincipal;

    public TelaPrincipal telaPrincipal;

    private ControladorPrincipal() {
        this.telaPrincipal = new TelaPrincipal();
    }

    public static ControladorPrincipal getInstance() {
        if (controladorPrincipal == null) {
            controladorPrincipal = new ControladorPrincipal();
        }
        return controladorPrincipal;
    }

	/**
     * Instancia cargo indefinido (codigo = 0, sem função gerencial e sem acesso
     * ao financeiro)
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
    }

    /**
     * Chama classe que controla o acesso ao financeiro
     */
    public void acessarFinanceiro() {
        ControladorAcesso.getInstance().exibePainelAcesso();
    }

    /**
     * Chama classe que controla o gerenciamento de funcionarios
     */
    public void gerenciarFuncionarios() {
        ControladorFuncionario.getInstance().exibeMenuFuncionario();
    }

    /**
     * Chama classe que controla o gerenciamento de cargos
     */
    public void gerenciarCargos() {
        ControladorCargo.getInstance().exibeMenuCargo();
    }

    /**
     * Chama classe que controla o gerenciamento da data do sistema
     */
    public void gerenciarData() {
        ControladorDataSistema.getInstance().exibeMenuDataSistema();
    }

    /**
     * Chama classe que controla o registro de acesso negado
     */
    public void emitirRelatorio() {
        ControladorRegistroAcessoNegado.getInstance().exibeRelatorio();
    }

	// ============= REDIRECIONAMENTOS ============= //
	
    public Date getDataSistema() {
        return ControladorDataSistema.getInstance().getDataSistema();
    }

    /**
     * Chama função no controlador funcionario que encontra funcionario, recebe
     * como parametro uma matricula e retorna um funcionario
     *
     * @param matricula do funcionario
     * @return funcionario
     */
    public Funcionario encontraFuncionarioPelaMatricula(int matricula) {
        return ControladorFuncionario.getInstance().encontraFuncionarioPelaMatricula(matricula);
    }

    /**
     * Chama função no controlador registro de acesso negado que encontra
     * resgistros de tentativas de acesso negadas, recebe como parametro uma
     * matricula e retorna um registro
     *
     * @param matricula do funcionario
     * @return registro de acesso negado
     */
    public ArrayList<RegistroAcessoNegado> encontraRegistrosHorarioNaoPermitidoPelaMatricula(int matricula) {
        return ControladorRegistroAcessoNegado.getInstance().encontraRegistrosHorarioNaoPermitidoPelaMatricula(matricula);
    }

    /**
     * Chama função no controlador registro de acesso negado que registra nova
     * tentativa de acesso negada.
     *
     * @param data da tentativa de acesso
     * @param matricula do funcionario
     * @param motivo da negação de acesso
     */
    public void novoRegistroAcessoNegado(Date data, int matricula, Motivo motivo) {
        ControladorRegistroAcessoNegado.getInstance().novoRegistroAcessoNegado(data, matricula, motivo);
    }

    /**
     * Chama função no controlador funcionario que percorre a lista de
     * matriculas cadastrada e retorna um valor boleano se a matricula desejada
     * está ou não na lista
     *
     * @param matricula do funcionario
     * @return true ou false em relação da matricula já estar cadastrada
     */
    public boolean matriculaExiste(int matricula) {
        return ControladorFuncionario.getInstance().matriculaExiste(matricula);
    }

    public ArrayList getMatriculas() {
        return ControladorFuncionario.getInstance().getMatriculas();
    }

    /**
     * Chama função no controlador funcionario que percorre a lista de
     * funcionarios e sobrescreve o cargo do funcionario do paramentro para
     * cargo indefinido
     *
     * @param cargoDeletado a ser subistituido por indefinido
     * @param cargoIndefinido
     */
    public void deletaCargosFuncionarios(Cargo cargoDeletado, Cargo cargoIndefinido) {
        ControladorFuncionario.getInstance().deixaFuncionariosSemCargo(cargoDeletado, cargoIndefinido);
    }

    public Collection<RegistroAcessoNegado> getListaRegistrosAcessosNegados() {
        return ControladorRegistroAcessoNegado.getInstance().getListaRegistrosAcessosNegados();
    }

    public Collection<Funcionario> getListaFuncionarios() {
        return ControladorFuncionario.getInstance().getListaFuncionarios();
    }

    public ArrayList<Cargo> getListaCargos() {
        return ControladorCargo.getInstance().getListaCargos();
    }
}
