/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Modelos;

import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorAcesso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thiagobrezinski
 */
public class Acesso {
	
    private Date data;
    private int matricula;
	private ControladorAcesso controladorAcesso;
    
    public Acesso(Date data, int matricula) {
        this.data = data;
        this.matricula = matricula;
        this.controladorAcesso = new ControladorAcesso();
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

	/**
	 * Verifica se o cargo do funcionario recebido eh gerencial ou se permite
	 * acesso ao financeiro. Se for gerencial ou se o horario do cargo com acesso
	 * estiver valido permite o acesso.
	 * 
	 * @param acesso Acesso tentado pelo funcionario
	 * @param funcionario Funcionario que tentou o acesso
	 * @param data Data da tentativa de acesso
	 * @return True se o acesso for validado
	 */
	public boolean validaAcesso(Acesso acesso, Funcionario funcionario, Date data) throws ParseException {
		if(funcionario.getCargo().ehGerencial()) return true;
		if(funcionario.getCargo().temAcessoAoFinanceiro())
			return validaHorarioAcesso(acesso, funcionario.getCargo(), data);
		controladorAcesso.novoRegistroAcessoNegado(data, acesso.getMatricula(), Motivo.CARGO_SEM_ACESSO);
		controladorAcesso.exibeAcessoNegadoCargoSemAcesso();
		return false;
	}

	/**
	 * Verifica se o horario que o funcionario tentou o acesso confere com o
	 * horario em que ele eh permitido acessar.
	 * 
	 * @param acesso Acesso tentado pelo funcionario
	 * @param cargo Cargo do funcionario
	 * @param data Data da tentativa de acesso
	 * @return True se o horario da tentativa de acesso estiver dentro do
	 * permitido pelo cargo
	 */
	public boolean validaHorarioAcesso(Acesso acesso, Cargo cargo, Date data) throws ParseException {
		SimpleDateFormat formatador = new SimpleDateFormat(Constantes.FORMATADOR_HORA);
		Date horaAtual = formatador.parse(formatador.format(acesso.getData()));
		Date meiaNoite = formatador.parse(Constantes.FORMATADOR_MEIA_NOITE);
		if(cargo instanceof CargoHorarioEspecial) {
			if(((CargoHorarioEspecial) cargo).getHoraInicioTarde().after(((CargoHorarioEspecial) cargo).getHoraFimTarde()) ||
			   ((CargoHorarioEspecial) cargo).getHoraInicioEspecial().after(((CargoHorarioEspecial) cargo).getHoraFimEspecial())) {
				if(((!horaAtual.before(((CargoHorarioEspecial) cargo).getHoraInicioTarde())) && (!horaAtual.after(meiaNoite))) ||
				   ((!horaAtual.before(meiaNoite)) && (!horaAtual.after(((CargoHorarioEspecial) cargo).getHoraFimTarde()))) ||
				   ((!horaAtual.before(((CargoHorarioEspecial) cargo).getHoraInicioEspecial())) && (!horaAtual.after(meiaNoite))) ||
				   ((!horaAtual.before(meiaNoite)) && (!horaAtual.after(((CargoHorarioEspecial) cargo).getHoraFimEspecial())))) {
					return true;
				}
			} else {
				if(((!horaAtual.before(((CargoHorarioEspecial) cargo).getHoraInicioManha())) &&
					(!horaAtual.after(((CargoHorarioEspecial) cargo).getHoraFimManha()))) ||
				  ((!horaAtual.before(((CargoHorarioEspecial) cargo).getHoraInicioTarde())) &&
					(!horaAtual.after(((CargoHorarioEspecial) cargo).getHoraFimTarde()))) ||
				  ((!horaAtual.before(((CargoHorarioEspecial) cargo).getHoraInicioEspecial())) &&
					(!horaAtual.after(((CargoHorarioEspecial) cargo).getHoraFimEspecial())))) {
					return true;
				}
			}
		} else {
			if(!cargo.getHoraInicioTarde().after(cargo.getHoraFimTarde())) {
				if(((!horaAtual.before(cargo.getHoraInicioManha())) &&
						(!horaAtual.after(cargo.getHoraFimManha()))) ||
				   ((!horaAtual.before(cargo.getHoraInicioTarde())) &&
						(!horaAtual.after(cargo.getHoraFimTarde())))) {
					return true;
				}
			} else {
				if(((!horaAtual.before(cargo.getHoraInicioTarde())) && (!horaAtual.after(meiaNoite))) ||
					(!horaAtual.before(meiaNoite)) && (!horaAtual.after(cargo.getHoraFimTarde()))) {
					return true;
				}
			}
		}
		controladorAcesso.novoRegistroAcessoNegado(data, acesso.getMatricula(), Motivo.HORARIO_NAO_PERMITIDO);
		controladorAcesso.exibeAcessoNegadoHorarioNaoPermitido();
		return false;
	}
    
}
