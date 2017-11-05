/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Interfaces;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;

/**
 *
 * @author bruno
 */
public interface IControladorCargo {
    
    /**
     * Verifica se existe um cargo criado com o código passado como parametro,
     * caso exista retorna o cargo. Caso não exista retorna null.
     *
     * @return cargo
     */
    public Cargo encontraCargoPorCodigo(int codigo);
    
    /**
     * Lista na tela todos os cargos cadastrados, com todos os seus
     * atributos
     * 
     * @return
     */
    public void listaCargos();

}
