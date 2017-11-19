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
import java.lang.Character;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author João Grasel
 */
public class ControladorFuncionario implements IControladorFuncionario {

    private static ControladorFuncionario controladorFuncionario;

    private TelaFuncionario telaFuncionario;
    private TelaCadastroFuncionario telaCadastroFuncionario;
    private TelaEditarFuncionario telaEditarFuncionario;
    private FuncionarioDAO funcionarioDAO;

    public ControladorFuncionario() {
        this.telaFuncionario = new TelaFuncionario();
        this.telaCadastroFuncionario = new TelaCadastroFuncionario();
        this.telaEditarFuncionario = new TelaEditarFuncionario();
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public static ControladorFuncionario getInstance() {
        if (controladorFuncionario == null) {
            controladorFuncionario = new ControladorFuncionario();
        }
        return controladorFuncionario;
    }

    // ================== EXIBIÇÃO DE TELAS ==================
    /**
     * Exibe a tela principal do CRUD do funcionário.
     */
    public void exibeMenuFuncionario() {
        this.telaFuncionario.exibeMenuFuncionario();
    }

    /**
     * Exibe a tela para cadastrar funcionario.
     */
    public void exibeCadastraFuncionario() {
        this.telaCadastroFuncionario.exibeMenuCadastroFuncionario();
    }

    /**
     * Exibe a tela para editar o funcionario selecionado na tabela.
     *
     * @param indexSelecionado do funcionario selecionado na tabela da tela
     * principal do crud do funcionario.
     */
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

    /**
     * Volta a tela principal do sistema
     */
    public void voltarMenuPrincipal() {
        ControladorPrincipal.getInstance().exibeMenuPrincipal();
    }

    // ================== CONTROLE DE FUNCIONÁRIOS ==================
    /**
     * Cadastra um funcionário. O nome de conter somente 3 letras e no minimo 3
     * caracteres e a matricula não deve estar sendo utilizada.
     *
     * @param matricula
     * @param nome
     * @param dataNascimento
     * @param telefone
     * @param salario
     * @param cargo
     */
    public void cadastraFuncionario(int matricula, String nome, String dataNascimento, int telefone, int salario, Cargo cargo) {

        boolean nomeValido = verificaNomeInserido(nome);
        boolean matriculaValida = verificaMatricula(matricula);
        boolean dataNascimentoValida = verificaDataNascimento(dataNascimento);

        if (dataNascimentoValida) {
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
        } else {
            this.telaCadastroFuncionario.exibeDataNascimentoInvalida();
        }

    }

    /**
     * Deleta o funcionario selecionado na tabela da tela na tela principal do
     * crud do funcionario.
     *
     * @param indexSelecionado do funcionario selecionado
     */
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

    /**
     * Edita um funcionário. O nome de conter somente 3 letras e no minimo 3
     * caracteres e a matricula não deve estar sendo utilizada.
     *
     * @param matriculaAntiga
     * @param matricula
     * @param nome
     * @param dataNascimento
     * @param telefone
     * @param salario
     * @param cargo
     */
    public void editaFuncionario(int matriculaAntiga, int matricula, String nome, String dataNascimento, int telefone, int salario, Cargo cargo) {
        boolean nomeValido = verificaNomeInserido(nome);
        boolean matriculaValida = verificaMatricula(matricula);

        if (nomeValido) {
            if (matriculaAntiga != matricula) {
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
                Funcionario funcionarioNaoEditado = this.funcionarioDAO.get(matriculaAntiga);
                this.funcionarioDAO.remove(funcionarioNaoEditado);
                funcionarioNaoEditado = null;
                Funcionario funcionarioEditado = new Funcionario(matriculaAntiga, nome, dataNascimento, telefone, salario, cargo);
                funcionarioDAO.put(funcionarioEditado);
                this.telaEditarFuncionario.exibeFuncionarioEditadoComSucesso();
                this.telaFuncionario.updateData();
            }
        } else {
            this.telaEditarFuncionario.exibeErroNome();
        }
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
    public Funcionario encontraFuncionarioPelaMatricula(int matricula) {
        Funcionario funcionario = funcionarioDAO.get(matricula);
        if (funcionario != null) {
            return funcionario;
        }
        return null;
    }

    // ================== VALIDAÇÕES ==================
    /**
     * Verifica se algum funcionario esta utilizando a matricula no momento.
     *
     * @param matricula
     * @return true caso a matricula exista
     */
    public boolean verificaMatricula(int matricula) {
        for (Funcionario funcionarioCadastrado : this.funcionarioDAO.getList()) {
            if (funcionarioCadastrado.getMatricula() == matricula) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica o nome inserido
     *
     * @param nome
     * @return true caso o nome possua mais de 3 caracteres e somente letras.
     */
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

    public boolean verificaDataNascimento(String dataNascimentoInserida) {
        try {
            Date data = new SimpleDateFormat("dd-MM-yyyy")
                    .parse(dataNascimentoInserida);
            return true;

        } catch (ParseException ex) {
            return false;
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

// ================== GETTERS ==================
    public ArrayList getMatriculas() {
        return this.funcionarioDAO.getMatriculas();
    }

    public Collection<Funcionario> getListaFuncionarios() {
        return this.funcionarioDAO.getList();
    }

}
