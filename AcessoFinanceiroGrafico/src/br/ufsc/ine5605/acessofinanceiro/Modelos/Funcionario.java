/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Modelos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vladimir
 */
public class Funcionario implements Serializable {

    private int matricula;
    private String nome;
    private String dataNascimento;
    private int telefone;
    private int salario;
    private Cargo cargo;

    public Funcionario(int matricula, String nome, String dataNascimento, int telefone, int salario, Cargo cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.salario = salario;
        this.cargo = cargo;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public int getTelefone() {
        return this.telefone;
    }

    public int getSalario() {
        return this.salario;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public void setMatricula(int matricula) {

        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

}
