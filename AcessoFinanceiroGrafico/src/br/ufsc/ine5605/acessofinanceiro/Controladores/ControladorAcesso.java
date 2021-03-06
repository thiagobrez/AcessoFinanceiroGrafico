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
import excecoes.MatriculaInexistenteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author thiagobrezinski
 */
public class ControladorAcesso {
	
	private static ControladorAcesso controladorAcesso;
	
    private TelaAcesso telaAcesso;
    
    private ControladorAcesso() {
        this.telaAcesso = new TelaAcesso();
    }
    
	public static ControladorAcesso getInstance() {
		if(controladorAcesso == null) controladorAcesso = new ControladorAcesso();
		return controladorAcesso;
	}
	
	/**
	 * Exibe a tela para acesso do financeiro.
	 */
    public void exibePainelAcesso() {
		this.telaAcesso.setVisible(true);
    }
    
	/**
	 * Se validado o acesso, mostra modal indicando acesso permitido.
	 * 
	 * @param matricula matricula inserida para o acesso
	 */
	public void acessaFinanceiro(int matricula) {
		if(validaAcessoFinanceiro(matricula)) {
			telaAcesso.exibeAcessoPermitido();
			telaAcesso.setVisible(false);
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
		Date dataAtual = ControladorDataSistema.getInstance().getDataSistema();
		Funcionario funcionario = null;
		ArrayList<RegistroAcessoNegado> registrosHorarioNaoPermitido = new ArrayList<>();
        try {
            funcionario = ControladorFuncionario.getInstance().encontraFuncionarioPelaMatricula(matricula);
			registrosHorarioNaoPermitido = ControladorRegistroAcessoNegado.getInstance()
					.encontraRegistrosHorarioNaoPermitidoPelaMatricula(matricula);
			if(registrosHorarioNaoPermitido.size() >= 3) {
				ControladorRegistroAcessoNegado.getInstance().novoRegistroAcessoNegado(dataAtual, matricula, Motivo.ACESSO_BLOQUEADO);
				telaAcesso.exibeAcessoNegadoAcessoBloqueado();
				return false;
			}
			Acesso acesso = new Acesso(dataAtual, matricula);
			return acesso.validaAcesso(acesso, funcionario, dataAtual);
		} catch (MatriculaInexistenteException e) {
			ControladorRegistroAcessoNegado.getInstance().novoRegistroAcessoNegado(dataAtual, matricula, Motivo.MATRICULA_INEXISTENTE);
			telaAcesso.exibeAcessoNegadoMatriculaInexistente();
        } catch (NullPointerException | ParseException e) {
			System.out.println(e.getMessage());
        }
        return false;
    }

	/**
	 * Verifica se a matricula inserida contem somente numeros.
	 * 
	 * @param matricula matricula verificada
	 * @return true se contiver somente numeros
	 */
	public boolean verificaMatricula(String matricula) {
		for (int i = 0; i < matricula.length(); i++) {
			if(Character.isLetter(matricula.charAt(i))) return false;
		}
        return true;
	}
	
	/**
	 * Solicita a criacao de um novo registro de acesso negado.
	 * 
	 * @param data data da tentativa negada de acesso
	 * @param matricula matricula da tentativa negada de acesso
	 * @param motivo motivo pelo qual o acesso foi negado
	 */
	public void novoRegistroAcessoNegado(Date data, int matricula, Motivo motivo) {
		ControladorRegistroAcessoNegado.getInstance().novoRegistroAcessoNegado(data, matricula, motivo);
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
