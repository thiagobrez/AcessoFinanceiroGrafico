/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Modelos;

import java.util.Date;

/**
 *
 * @author thiagobrezinski
 */
public class RegistroAcessoNegado {
	
	private Date data;
	private int matricula;
	private Motivo motivo;
	
	public RegistroAcessoNegado(Date data, int matricula, Motivo motivo) {
		this.data = data;
		this.matricula = matricula;
		this.motivo = motivo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	
}
