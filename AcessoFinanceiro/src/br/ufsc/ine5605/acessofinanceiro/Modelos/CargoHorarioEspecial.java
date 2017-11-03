/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Modelos;

import java.util.Date;

/**
 * @author bruno e thiago
 */
public class CargoHorarioEspecial extends Cargo {
	
	private Date horaInicioEspecial;
	private Date horaFimEspecial;
	
	public CargoHorarioEspecial(int codigo, String nome, Date horaInicioManha, Date horaFimManha,
			Date horaInicioTarde, Date horaFimTarde, Date horaInicioEspecial, Date horaFimEspecial) {
        super(codigo, nome, false, true, horaInicioManha, horaFimManha, horaInicioTarde, horaFimTarde);
		this.horaInicioEspecial = horaInicioEspecial;
		this.horaFimEspecial = horaFimEspecial;
    }

	public Date getHoraInicioEspecial() {
		return horaInicioEspecial;
	}

	public void setHoraInicioEspecial(Date horaInicioEspecial) {
		this.horaInicioEspecial = horaInicioEspecial;
	}

	public Date getHoraFimEspecial() {
		return horaFimEspecial;
	}

	public void setHoraFimEspecial(Date horaFimEspecial) {
		this.horaFimEspecial = horaFimEspecial;
	}

}
