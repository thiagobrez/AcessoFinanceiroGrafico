/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Motivo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.RegistroAcessoNegado;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaRegistroAcessoNegado;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author thiagobrezinski
 */
public class ControladorRegistroAcessoNegado {
    
	private static ControladorRegistroAcessoNegado controladorRegistroAcessoNegado;
	
    private TelaRegistroAcessoNegado telaRegistroAcessoNegado;
	private RegistroAcessoNegadoDAO registroDAO;
    
    private ControladorRegistroAcessoNegado() {
		this.registroDAO = new RegistroAcessoNegadoDAO();
        this.telaRegistroAcessoNegado = new TelaRegistroAcessoNegado();
    }
    
	public static ControladorRegistroAcessoNegado getInstance() {
        if (controladorRegistroAcessoNegado == null)
			controladorRegistroAcessoNegado = new ControladorRegistroAcessoNegado();
        return controladorRegistroAcessoNegado;
    }
	
	/**
	 * Solicita a tela que exiba o menu do relatorio.
	 */
    public void exibeRelatorio() {
		telaRegistroAcessoNegado.exibeMenuRelatorio();
    }

	/**
	 * Filtra os registros que serao mostrados para o usuario a partir do motivo
	 * e matricula escolhidos.
	 * 
	 * @param filtroMotivo motivo escolhido pelo usuario
	 * @param filtroMatricula matricula inserida pelo usuario
	 * @return 
	 */
	public ArrayList<RegistroAcessoNegado> filtraRegistros(String filtroMotivo, String filtroMatricula) {
		ArrayList<RegistroAcessoNegado> registros = new ArrayList<>();
		ArrayList<RegistroAcessoNegado> registrosMotivo = encontraRegistrosPorMotivo(filtroMotivo);
		if(!filtroMatricula.equals(Constantes.REGISTRO_FILTRO_VAZIO)) {
			for(RegistroAcessoNegado registro : registrosMotivo) {
				if(registro.getMatricula() == Integer.parseInt(filtroMatricula)) registros.add(registro);
			}
			return registros;
		}
		return registrosMotivo;
	}

	/**
	 * Verifica se a matricula inserida para o filtro de registros eh valida, se
	 * contem somente numeros.
	 * 
	 * @param matricula matricula inserida pelo usuario
	 * @return true se a matricula contiver somente numeros
	 */
	public boolean verificaFiltroMatricula(String matricula) {
		for (int i = 0; i < matricula.length(); i++) {
			if(Character.isLetter(matricula.charAt(i))) return false;
		}
        return true;
	}
	
	/**
	 * Encontra na colecao de registros de acesso negado todos que possuirem
	 * como motivo o recebido. Se receber "Todos" retorna a lista completa de
	 * registros.
	 * 
	 * @param motivo desejado para encontrar os registros
	 * @return ArrayList de registros encontrados
	 */
	public ArrayList<RegistroAcessoNegado> encontraRegistrosPorMotivo(String motivo) {
		if(motivo.equals(Constantes.REGISTRO_FILTRO_TODOS)) return getListaRegistrosAcessosNegados();
		ArrayList<RegistroAcessoNegado> registrosEncontrados = new ArrayList<>();
		for(RegistroAcessoNegado registro : this.registroDAO.getList()) {
			if(registro.getMotivo().toString().equals(motivo)) {
				registrosEncontrados.add(registro);
			}
		}
		return registrosEncontrados;
	}
	
	/**
	 * Encontra e retorna os registros de horario nao permitido de propriedade
	 * da matricula recebida.
	 * 
	 * @param matricula matricula utilizada para encontrar os registros
	 * @return 
	 */
	public ArrayList<RegistroAcessoNegado> encontraRegistrosHorarioNaoPermitidoPelaMatricula(int matricula) {
		ArrayList<RegistroAcessoNegado> registrosHorarioNaoPermitido = new ArrayList<>();
		for(RegistroAcessoNegado registro : this.registroDAO.getList()) {
			if(registro.getMatricula() == matricula && registro.getMotivo() == Motivo.HORARIO_NAO_PERMITIDO)
				registrosHorarioNaoPermitido.add(registro);
		}
		return registrosHorarioNaoPermitido;
	}
	
	/**
	 * Instancia um novo RegistroAcessoNegado e inclui na colecao de registros.
	 * 
	 * @param data data do acesso negado
	 * @param matricula matricula do funcionario tentando o acesso
	 * @param motivo motivo pelo qual o funcionario teve o acesso negado
	 */
	public void novoRegistroAcessoNegado(Date data, int matricula, Motivo motivo) {
		RegistroAcessoNegado registro = new RegistroAcessoNegado(data, matricula, motivo);
		this.registroDAO.put(registro);
	}

	/**
	 * Solicita ao registroDAO a lista de registros cadastrados e formata para
	 * ArrayList.
	 * 
	 * @return ArrayList de registros cadastrados.
	 */
	public ArrayList<RegistroAcessoNegado> getListaRegistrosAcessosNegados() {
		ArrayList<RegistroAcessoNegado> registros = new ArrayList<>();
		for(RegistroAcessoNegado registro : this.registroDAO.getList()) {
			registros.add(registro);
		}
		return registros;
	}
	
}
