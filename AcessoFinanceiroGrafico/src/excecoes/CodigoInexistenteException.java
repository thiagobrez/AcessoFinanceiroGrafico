/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excecoes;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;

/**
 *
 * @author thiagobrezinski
 */
public class CodigoInexistenteException extends Exception {
	
	public CodigoInexistenteException() {
		super(Constantes.EXCEPTION_CODIGO_INEXISTENTE);
	}
	
}
