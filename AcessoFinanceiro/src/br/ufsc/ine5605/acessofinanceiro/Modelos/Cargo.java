/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bruno e thiago
 */
public class Cargo {
	
    private int codigo;
    private String nome;
    private boolean ehGerencial;
    private boolean temAcessoAoFinanceiro;
    private Date horaInicioManha;
    private Date horaFimManha;
    private Date horaInicioTarde;
    private Date horaFimTarde;

    public Cargo(int codigo, String nome, boolean ehGerencial, boolean temAcessoAoFinanceiro,
                    Date horaInicioManha, Date horaFimManha, Date horaInicioTarde, Date horaFimTarde) {
        this.codigo = codigo;
        this.nome = nome;
        this.ehGerencial = ehGerencial;
        this.temAcessoAoFinanceiro = temAcessoAoFinanceiro;
        this.horaInicioManha = horaInicioManha;
        this.horaFimManha = horaFimManha;
        this.horaInicioTarde = horaInicioTarde;
        this.horaFimTarde = horaFimTarde;	
    }
    
    public boolean ehGerencial(){
        return this.ehGerencial;
    }
    
    public void setEhGerencial(boolean ehGerencial) {
        this.ehGerencial = ehGerencial;
    }
    
    public boolean temAcessoAoFinanceiro() {
        return this.temAcessoAoFinanceiro;
    }
    
    public void setTemAcessoAoFinanceiro(boolean temAcessoAoFinanceiro) {
        this.temAcessoAoFinanceiro = temAcessoAoFinanceiro;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getHoraInicioManha() {
            return horaInicioManha;
    }

    public void setHoraInicioManha(Date horaInicioManha) {
            this.horaInicioManha = horaInicioManha;
    }

    public Date getHoraFimManha() {
            return horaFimManha;
    }

    public void setHoraFimManha(Date horaFimManha) {
            this.horaFimManha = horaFimManha;
    }

    public Date getHoraInicioTarde() {
            return horaInicioTarde;
    }

    public void setHoraInicioTarde(Date horaInicioTarde) {
            this.horaInicioTarde = horaInicioTarde;
    }

    public Date getHoraFimTarde() {
            return horaFimTarde;
    }

    public void setHoraFimTarde(Date horaFimTarde) {
            this.horaFimTarde = horaFimTarde;
    }

}
