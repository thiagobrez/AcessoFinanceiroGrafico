/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Acesso;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Motivo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.RegistroAcessoNegado;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaAcesso;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author thiagobrezinski
 */
public class ControladorAcesso {
	
    private TelaAcesso telaAcesso;
    
    public ControladorAcesso() {
        this.telaAcesso = new TelaAcesso(this);
    }
    
	/**
	 * Exibe a tela para acesso do financeiro e trata o recebimento da matricula.
	 */
    public void acessaFinanceiro() {
        int matricula = 0;
        matricula = telaAcesso.exibeAcessoFinanceiro();
        if(validaAcessoFinanceiro(matricula)) {
			telaAcesso.exibeAcessoPermitido();
			ControladorPrincipal.getInstance().exibeMenuPrincipal();
		} else {
			trataNovaTentativa();
		}
    }
    
	/**
	 * Verifica se a matricula recebida existe e, se verdadeiro, verifica se o
	 * acesso do funcionario esta bloqueado. Se sim, solicita a criacao de um
	 * novo registro de acesso negado. Se nao, tenta validar o acesso.
	 * 
	 * @param matricula matricula inserida pelo usuario
	 * @return true se o acesso do funcionario for permitido
	 */
    public boolean validaAcessoFinanceiro(int matricula) {
		Date dataAtual = ControladorPrincipal.getInstance().getDataSistema();
		Funcionario funcionario = null;
		ArrayList<RegistroAcessoNegado> registrosHorarioNaoPermitido = new ArrayList<>();
        try {
            funcionario = ControladorPrincipal.getInstance().encontraFuncionarioPelaMatricula(matricula);
			registrosHorarioNaoPermitido = ControladorPrincipal.getInstance().encontraRegistrosHorarioNaoPermitidoPelaMatricula(matricula);
			if(registrosHorarioNaoPermitido.size() >= 3) {
				ControladorPrincipal.getInstance().novoRegistroAcessoNegado(dataAtual, matricula, Motivo.ACESSO_BLOQUEADO);
				telaAcesso.exibeAcessoNegadoAcessoBloqueado();
				return false;
			}
			Acesso acesso = new Acesso(dataAtual, matricula);
			return acesso.validaAcesso(acesso, funcionario, dataAtual);
        } catch (NullPointerException e) {
			if(funcionario == null) {
				ControladorPrincipal.getInstance().novoRegistroAcessoNegado(dataAtual, matricula, Motivo.MATRICULA_INEXISTENTE);
				telaAcesso.exibeAcessoNegadoMatriculaInexistente();
			}
        } catch (ParseException e) {}
        return false;
    }

	/**
	 * Solicita a criacao de um novo registro de acesso negado.
	 * 
	 * @param data data da tentativa negada de acesso
	 * @param matricula matricula da tentativa negada de acesso
	 * @param motivo motivo pelo qual o acesso foi negado
	 */
	public void novoRegistroAcessoNegado(Date data, int matricula, Motivo motivo) {
		ControladorPrincipal.getInstance().novoRegistroAcessoNegado(data, matricula, motivo);
	}
	
	/**
	 * Oferece a opcao de tentar inserir a matricula novamente para acessar o
	 * financeiro ou voltar ao menu principal.
	 */
	public void trataNovaTentativa() {
		int opcao = 0;
		opcao = telaAcesso.exibeNovaTentativa();
		if(opcao == 1) {
			acessaFinanceiro();
		} else {
			ControladorPrincipal.getInstance().exibeMenuPrincipal();
		}
	}

	/**
	 * Solicita a tela que exiba que o acesso foi negado porque o cargo nao tem
	 * permissao.
	 */
	public void exibeAcessoNegadoCargoSemAcesso() {
		telaAcesso.exibeAcessoNegadoCargoSemAcesso();
	}

	/**
	 * Solicita a tela que exiba que o acesso foi negado porque o horario nao
	 * eh permitido para o cargo do funcionario tentando acessar.
	 */
	public void exibeAcessoNegadoHorarioNaoPermitido() {
		telaAcesso.exibeAcessoNegadoHorarioNaoPermitido();
	}
    
}
