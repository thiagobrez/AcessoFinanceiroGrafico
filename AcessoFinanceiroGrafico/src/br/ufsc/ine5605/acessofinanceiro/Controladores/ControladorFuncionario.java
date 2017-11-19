/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import br.ufsc.ine5605.acessofinanceiro.Interfaces.IControladorFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Modelos.FuncionarioDAO;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaCadastroFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaEditarFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaFuncionario;
import java.util.Date;
import java.lang.Character;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author vladimir
 */
public class ControladorFuncionario implements IControladorFuncionario {

    private static ControladorFuncionario controladorFuncionario;

    private TelaFuncionario telaFuncionario;
    private TelaCadastroFuncionario telaCadastroFuncionario;
    private TelaEditarFuncionario telaEditarFuncionario;
    private FuncionarioDAO funcionarioDAO;

    public ControladorFuncionario() {
        this.telaFuncionario = new TelaFuncionario(this);
        this.telaCadastroFuncionario = new TelaCadastroFuncionario(this);
        this.telaEditarFuncionario = new TelaEditarFuncionario(this);
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public static ControladorFuncionario getInstance() {
        if (controladorFuncionario == null) {
            controladorFuncionario = new ControladorFuncionario();
        }
        return controladorFuncionario;
    }

    /**
     * Exibe o menu principal do CRUD do funcionário.
     */
    public void exibeMenuFuncionario() {
        this.telaFuncionario.exibeMenuFuncionario();
    }

    public void exibeCadastraFuncionario() {
        this.telaCadastroFuncionario.exibeMenuCadastroFuncionario();
    }

    public void exibeEditarFuncionarioSelecionado(int indexSelecionado) {
        if (indexSelecionado != -1) {
            ArrayList<Integer> matriculas = this.funcionarioDAO.getMatriculas();
            int matricula = matriculas.get(indexSelecionado);
            Funcionario funcionario = this.funcionarioDAO.get(matricula);
            this.telaEditarFuncionario.exibeMenuEditaFuncionario(funcionario);
        } else {
            this.telaFuncionario.exibeFuncionarioNaoSelecionado();
        }
    }

    public void voltarMenuPrincipal() {
        ControladorPrincipal.getInstance().exibeMenuPrincipal();
    }

    public void cadastraFuncionario(int matricula, String nome, String dataNascimento, int telefone, int salario, Cargo cargo) {

        boolean nomeValido = verificaNomeInserido(nome);
        boolean matriculaValida = verificaMatricula(matricula);

        if (nomeValido) {
            if (matriculaValida) {
                Funcionario funcionario = new Funcionario(matricula, nome, dataNascimento, telefone, salario, cargo);
                funcionarioDAO.put(funcionario);
                this.telaCadastroFuncionario.exibeFuncionarioCadastradoComSucesso();
                this.telaFuncionario.updateData();
            } else {
                this.telaCadastroFuncionario.exibeMatriculaJaExiste();
            }
        } else {
            this.telaCadastroFuncionario.exibeErroNome();
        }

    }

    public void deletaFuncionarioSelecionado(int indexSelecionado) {
        if (indexSelecionado != -1) {
            ArrayList<Integer> matriculas = this.funcionarioDAO.getMatriculas();
            int matricula = matriculas.get(indexSelecionado);
            Funcionario funcionario = this.funcionarioDAO.get(matricula);
            this.funcionarioDAO.remove(funcionario);
            funcionario = null;
            this.telaFuncionario.exibeFuncionarioDeletadoComSucesso();
        } else {
            this.telaFuncionario.exibeFuncionarioNaoSelecionado();
        }

    }

    public void editaFuncionario(int matriculaAntiga, int matricula, String nome, String dataNascimento, int telefone, int salario, Cargo cargo) {
        boolean nomeValido = verificaNomeInserido(nome);
        boolean matriculaValida = verificaMatricula(matricula);

        if (nomeValido) {
            if (matriculaValida) {
                Funcionario funcionarioNaoEditado = this.funcionarioDAO.get(matriculaAntiga);
                this.funcionarioDAO.remove(funcionarioNaoEditado);
                funcionarioNaoEditado = null;
                Funcionario funcionarioEditado = new Funcionario(matricula, nome, dataNascimento, telefone, salario, cargo);
                funcionarioDAO.put(funcionarioEditado);
                this.telaEditarFuncionario.exibeFuncionarioEditadoComSucesso();
                this.telaFuncionario.updateData();
            } else {
                this.telaEditarFuncionario.exibeMatriculaJaExiste();
            }
        } else {
            this.telaEditarFuncionario.exibeErroNome();
        }
    }

    public boolean verificaMatricula(int matricula) {
        for (Funcionario funcionarioCadastrado : this.funcionarioDAO.getList()) {
            if (funcionarioCadastrado.getMatricula() == matricula) {
                return false;
            }
        }
        return true;
    }

    public boolean verificaNomeInserido(String nome) {
        if (nome.length() > 2) {
            for (int i = 0; i < nome.length(); i++) {
                char letraAnalisada = nome.charAt(i);
                if (!Character.isLetter(letraAnalisada)) {
                    if (!Character.isSpace(letraAnalisada)) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public ArrayList getMatriculas() {
        return this.funcionarioDAO.getMatriculas();
    }

    public Collection<Funcionario> getListaFuncionarios() {
        return this.funcionarioDAO.getList();
    }

    @Override
    public void deixaFuncionariosSemCargo(Cargo cargoDeletado, Cargo semCargo) {
        if (cargoDeletado != null) {
            for (Funcionario funcionarioCadastrado : this.funcionarioDAO.getList()) {
                if (funcionarioCadastrado.getCargo().getCodigo() == cargoDeletado.getCodigo()) {
                    funcionarioCadastrado.setCargo(semCargo);
                }
            }
        }
    }

    @Override
    public boolean matriculaExiste(int matricula) {
        for (Funcionario funcionarioCadastrado : this.funcionarioDAO.getList()) {
            if (funcionarioCadastrado.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Funcionario encontraFuncionarioPelaMatricula(int matricula) {
        Funcionario funcionario = funcionarioDAO.get(matricula);
        if (funcionario != null) {
            return funcionario;
        }
        return null;
    }

}
