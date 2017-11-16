/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Modelos;

/**
 *
 * @author thiagobrezinski
 */
public enum Motivo {
	MATRICULA_INEXISTENTE,
	CARGO_SEM_ACESSO,
	HORARIO_NAO_PERMITIDO,
	ACESSO_BLOQUEADO;

	@Override
	public String toString(){
        switch(this){
			case MATRICULA_INEXISTENTE :
				return Constantes.MOTIVO_MATRICULA_INEXISTENTE;
			case CARGO_SEM_ACESSO:
				return Constantes.MOTIVO_CARGO_SEM_ACESSO;
			case HORARIO_NAO_PERMITIDO :
				return Constantes.MOTIVO_HORARIO_NAO_PERMITIDO;
			case ACESSO_BLOQUEADO:
				return Constantes.MOTIVO_ACESSO_BLOQUEADO;
		}
        return null;
    }
	
}
