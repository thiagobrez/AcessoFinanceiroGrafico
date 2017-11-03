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
import java.util.Date;

/**
 *
 * @author bruno
 */
public class ControladorPrincipal {
	
    private static ControladorPrincipal controladorPrincipal;
	
    public TelaPrincipal telaPrincipal;
    public ControladorCargo controladorCargo;
    public ControladorDataSistema controladorData;
    public ControladorFuncionario controladorFuncionario;
    public ControladorAcesso controladorAcesso;
    public ControladorRegistroAcessoNegado controladorRegistroAcessoNegado;

    
    public ControladorPrincipal() {
        this.telaPrincipal = new TelaPrincipal(this);
        this.controladorCargo = new ControladorCargo();
        this.controladorData = new ControladorDataSistema();
        this.controladorFuncionario = new ControladorFuncionario();
        this.controladorAcesso = new ControladorAcesso();
        this.controladorRegistroAcessoNegado = new ControladorRegistroAcessoNegado();
    }
    
    public static ControladorPrincipal getInstance() {
        if(controladorPrincipal == null) controladorPrincipal = new ControladorPrincipal();
        return controladorPrincipal;
    }
    
    /**
     * Exibe o menu principal do sistema.
     */
    public void exibeMenuPrincipal() {
        telaPrincipal.exibeMenuPrincipal();
        controlaMenuPrincipal();
    }   
    
    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu principal. Caso aperte 1: avança ao menu de acesso ao financeiro.
     * Caso aperte 2: gerencia um funcionario. Caso aperte 3: gerencia um cargo.
     * Caso aperte 4: gerencia a data do sistema. Caso aperte 5: emite relatorio
     * de acessos. Caso aperte outra tecla: apresenta uma mensagem de opcao 
     * inexistente e pede que o usuario digite outra vez a opcao que deseja 
     * selecionar.
     */
    public void controlaMenuPrincipal() {
        int opcao = telaPrincipal.pedeOpcao();
        
        switch(opcao) {
            case 1:
                acessarFinanceiro();
                break;
            case 2: 
                gerenciarFuncionarios();
                break;
            case 3: 
                gerenciarCargos();
                break;
            case 4:
                gerenciarData();
                break;
            case 5:
                emitirRelatorio();
                break;
            default:
                this.telaPrincipal.exibeOpcaoInexistente();
                exibeMenuPrincipal();
        }
    }
    
    /**
     * Chama classe que controla o acesso ao financeiro
     */
    public void acessarFinanceiro(){
        this.controladorAcesso.acessaFinanceiro();
    }
    
    /**
     * Chama classe que controla o gerenciamento de funcionarios
     */
    public void gerenciarFuncionarios(){
        this.controladorFuncionario.exibeMenuFuncionario();
    }
    
    /**
     * Chama classe que controla o gerenciamento de cargos
     */
    public void gerenciarCargos(){
        this.controladorCargo.exibeMenuCargo();
    }
    
    /**
     * Chama classe que controla o gerenciamento da data do sistema
     */
    public void gerenciarData(){
        this.controladorData.menuDataHoraSistema();
    }
    
    /**
     * Chama classe que controla o registro de acesso negado
     */
    public void emitirRelatorio(){
        controladorRegistroAcessoNegado.exibeRelatorio();
    }
	
    public Date getDataSistema(){
	return controladorData.getDataSistema();
    }

    /**
     * Chama função no controlador funcionario que encontra funcionario, recebe como parametro uma 
     * matricula e retorna um funcionario
     * @param matricula do funcionario
     * @return funcionario
     */
    public Funcionario encontraFuncionarioPelaMatricula(int matricula) {
	return controladorFuncionario.encontraFuncionarioPelaMatricula(matricula);
    }

    /**
     * Chama função no controlador registro de acesso negado que encontra 
     * resgistros de tentativas de acesso negadas, recebe como parametro uma 
     * matricula e retorna um registro
     * @param matricula do funcionario
     * @return registro de acesso negado
     */
    public ArrayList<RegistroAcessoNegado> encontraRegistrosHorarioNaoPermitidoPelaMatricula(int matricula) {
	return controladorRegistroAcessoNegado.encontraRegistrosHorarioNaoPermitidoPelaMatricula(matricula);
    }

    /**
     * Chama função no controlador registro de acesso negado que registra nova 
     * tentativa de acesso negada.
     * @param data da tentativa de acesso
     * @param matricula do funcionario
     * @param motivo da negação de acesso
     */
    public void novoRegistroAcessoNegado(Date data, int matricula, Motivo motivo) {
	controladorRegistroAcessoNegado.novoRegistroAcessoNegado(data, matricula, motivo);
    }
    
    /**
     * Chama função no controlador funcionario que percorre a lista de matriculas
     * cadastrada e retorna um valor boleano se a matricula desejada está ou não na lista
     * 
     * @param matricula do funcionario
     * @return true ou false em relação da matricula já estar cadastrada
     */
    public boolean matriculaExiste(int matricula) {
    	return controladorFuncionario.matriculaExiste(matricula);
    }

    /**
     * Instancia cargo indefinido (codigo = 0, sem função gerencial e sem
     * acesso ao financeiro)
     */
    public void inicia() {
        controladorCargo.criaCargoPadrao();
        exibeMenuPrincipal();
    }
    
    /**
     * Chama função no controlador funcionario que percorre a lista de funcionarios
     * e sobrescreve o cargo do funcionario do paramentro para cargo indefinido
     * 
     * @param cargoDeletado a ser subistituido por indefinido
     * @param cargoIndefinido 
     */
    public void deletaCargosFuncionarios(Cargo cargoDeletado, Cargo cargoIndefinido) {
        controladorFuncionario.deixaFuncionariosSemCargo(cargoDeletado, cargoIndefinido);
    }
    
}
