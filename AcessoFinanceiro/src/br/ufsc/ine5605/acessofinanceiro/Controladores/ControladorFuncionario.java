/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import br.ufsc.ine5605.acessofinanceiro.Interfaces.IControladorFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaFuncionario;
import java.util.ArrayList;
import java.util.Date;
import java.lang.Character;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author vladimir
 */
public class ControladorFuncionario implements IControladorFuncionario {

    private TelaFuncionario telaFuncionario;
    private ArrayList<Funcionario> funcionarios;

    public ControladorFuncionario() {
        this.telaFuncionario = new TelaFuncionario(this);
        this.funcionarios = new ArrayList<>();
    }
//
// +-+-+-+-+-+-+-+-+-+- MENU PRINCIPAL FUNCIONARIO +-+-+-+-+-+-+-+-+-+-
//

    /**
     * Exibe o menu principal do CRUD do funcionário.
     */
    public void exibeMenuFuncionario() {
        telaFuncionario.exibeMenuFuncionario();
        controlaMenuFuncionario();
    }

    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu principal do funcionario. Caso aperte 1: cadastra um funcionario.
     * Caso aperte 2: edita um funcionario. Caso aperte 3: lista todos os
     * funcionarios. Caso aperte 4: deleta um funcionario. Caso aperte 5: volta
     * ao menu principal do sistema. Caso aperte outra tecla: apresenta uma
     * mensagem de opcao inexistente e pede que o usuario digite outra vez a
     * opcao que deseja selecionar.
     */
    public void controlaMenuFuncionario() {
        int opcao = this.telaFuncionario.pedeOpcao();

        switch (opcao) {
            case 1:
                incluiFuncionario();
                break;
            case 2:
                editaFuncionario();
                break;
            case 3:
                listaFuncionarios();
                break;
            case 4:
                menuDeletaFuncionario();
                break;
            case 5:
                ControladorPrincipal.getInstance().exibeMenuPrincipal();
                break;
            default:
                this.telaFuncionario.opcaoInexistente();
                exibeMenuFuncionario();
                break;
        }

    }

//
//+-+-+-+-+-+-+-+-+-+- CADASTRAR FUNCIONARIO +-+-+-+-+-+-+-+-+-+-
//
    /**
     * Pede ao usuario todos os atributos para cadastrar um funcionario e caso o
     * funcionario não esteja cadastrado (matricula não foi cadastrada ainda),
     * cadastra o funcionario.
     */
    public void incluiFuncionario() {

        this.telaFuncionario.mensagemNovoFuncionario();

        String nome = pedeNome();
        int matricula = verificaMatriculaInserida();
        String dataNascimento = cadastraDataNascimento();
        int telefone = this.telaFuncionario.pedeTelefone();
        int salario = this.telaFuncionario.pedeSalario();
        this.telaFuncionario.exibeOpcaoCargoFuncionario();
        Cargo cargo = atribuiCargoAoFuncionario();

        if (!this.funcionarios.contains(encontraFuncionarioPelaMatricula(matricula))) {
            Funcionario funcionario = new Funcionario(matricula, nome, dataNascimento, telefone, salario, cargo);
            funcionarios.add(funcionario);
            this.telaFuncionario.mensagemFuncionarioCadastrado();
        }
        exibeMenuFuncionario();
    }

    /**
     * formata a data de nascimento inserida pelo usuario para o formato date
     * (dd/MM/yyyy)
     *
     * @param dataNascimentoInserida String inserida pelo usuario anteriormente
     * @return dataNascimento no formato date
     */
    public String formataDataNascimento(String dataNascimentoInserida) {
        String dataNascimentoString;
        SimpleDateFormat formatadorDataNascimento = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = new Date();
        try {
            dataNascimento = formatadorDataNascimento.parse((dataNascimentoInserida));
        } catch (ParseException ex) {
            this.telaFuncionario.mensagemErroDataNascimento();
            dataNascimentoString = cadastraDataNascimento();
            return dataNascimentoString;
        }
        dataNascimentoString = formatadorDataNascimento.format(dataNascimento);
        dataNascimentoString = controlaConfirmacaoCadastroDataNascimento(dataNascimentoString);
        return dataNascimentoString;
    }

    /**
     * Cadastra uma data de nascimento para o funcionário. Pede que o usuario
     * insira a data que deseja cadastrar, chama um metodo que transforma a
     * string em date, formata para o padrao desejado e chama o metodo que pede
     * que o usuario confirme a data ou não
     *
     * @return dataNascimento inserida pelo usuario no formato desejado
     */
    public String cadastraDataNascimento() {
        String dataNascimentoInserida = this.telaFuncionario.pedeDataNascimento();
        String dataNascimento = formataDataNascimento(dataNascimentoInserida);
        return dataNascimento;
    }

    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * onde caso ele selecione 1: o sistema confirma a data de nascimento
     * inserida; selecione 2: cadastra uma nova data de nascimento; selecione
     * outra tecla: apresenta uma mensagem de opcao invalida e pede que o
     * usuario digite outra opcao
     *
     * @param dataNascimento inserida anteriormente pelo usuario
     * @return dataNascimento inserida anteriormente pelo usuario
     */
    public String controlaConfirmacaoCadastroDataNascimento(String dataNascimento) {
        this.telaFuncionario.exibeMenuConfirmacaoCadastroDataNascimento(dataNascimento);
        int opcao = this.telaFuncionario.pedeOpcao();
        switch (opcao) {
            case 1:
                return dataNascimento;
            case 2:
                dataNascimento = cadastraDataNascimento();
                break;
            default:
                this.telaFuncionario.opcaoInexistente();
                controlaConfirmacaoCadastroDataNascimento(dataNascimento);
                break;
        }
        return dataNascimento;
    }

    /**
     * Retorna um cargo que deseja se atribuir ao funcionario por meio do que o
     * usuario selecionar. Caso aperte 1: o usuario visualiza os cargos
     * existentes, com seu codigo, e insere o codigo do cargo que deseja
     * atribuir. Caso aperte 2: o usuario cadastra um cargo para atribuir ao
     * funcionario. Caso aperte qualquer outra tecla: o usuario visualiza uma
     * mensagem de opcao invalida e insere novamente uma opcao de o que deseja
     * fazer.
     *
     * @return cargo a ser atribuido ao funcionario
     */
    public Cargo atribuiCargoAoFuncionario() {
        int opcao = this.telaFuncionario.pedeOpcao();
        Cargo cargo = null;
        switch (opcao) {
            case 1:
                ControladorPrincipal.getInstance().controladorCargo.listaCargos();
                cargo = comparaCodigoComCargo();
                break;
            case 2:
                cargo = ControladorPrincipal.getInstance().controladorCargo.incluiCargo();
                break;
            default:
                this.telaFuncionario.opcaoInexistente();
                atribuiCargoAoFuncionario();
                break;
        }
        return cargo;
    }

//
// +-+-+-+-+-+-+-+-+-+- EDITAR FUNCIONÁRIO +-+-+-+-+-+-+-+-+-+-
//
    /**
     * Pede qual funcionario o usuario deseja editar. Exibe o menu de editar
     * funcionario e chama quem controla o menu.
     */
    public void editaFuncionario() {
        this.telaFuncionario.mensagemEditaFuncionario();
        Funcionario funcionario = pedeFuncionario();
        this.telaFuncionario.exibeMensagemFuncionarioSelecionado();
        this.telaFuncionario.exibeFuncionario(funcionario.getMatricula(), funcionario.getNome(), funcionario.getDataNascimento(), funcionario.getTelefone(), funcionario.getSalario(), funcionario.getCargo());
        menuEditaFuncionario(funcionario);

    }

    /**
     * Método responsavel pelo menu de editar o funcionario, onde chama o metodo
     * da tela para exibir o menu propriamente e depois chama o método
     * responsavel pelo controle das opcoes possiveis
     *
     * @param funcionario a ser editado
     */
    public void menuEditaFuncionario(Funcionario funcionario) {
        this.telaFuncionario.exibeMenuEditaFuncionario();
        controlaMenuEditaFuncionario(funcionario);
    }

    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu para deletar o funcionario. Caso aperte 1: altera o nome do
     * funcionario. Caso aperte 2: altera a matricula do funcionario. Caso
     * aperte 3: altera a data de nascimento do funcionario. Caso aperte 4:
     * altera o telefone do funcionario. Caso aperte 5: altera o salario do
     * funcionario. Caso aperte 6: altera o cargo do funcionario. Caso aperte 7:
     * volta ao menu principal. Caso aperte qualquer outra tecla: exibe mensagem
     * de opcao invalida e pede para que insira uma nova opcao.
     *
     * @param funcionario a ser editado.
     */
    public void controlaMenuEditaFuncionario(Funcionario funcionario) {
        int opcao = this.telaFuncionario.pedeOpcao();

        switch (opcao) {
            case 1:
                String nome = this.telaFuncionario.pedeNome();
                funcionario.setNome(nome);
                this.telaFuncionario.mensagemNomeEditadoSucesso();
                menuEditaFuncionario(funcionario);
                break;
            case 2:
                int matricula = verificaMatriculaInserida();
                funcionario.setMatricula(matricula);
                this.telaFuncionario.mensagemMatriculaEditadaSucesso();
                menuEditaFuncionario(funcionario);
                break;
            case 3:
                String dataNascimento = cadastraDataNascimento();
                funcionario.setDataNascimento(dataNascimento);
                this.telaFuncionario.mensagemDataNascimentoEditadaSucesso();
                menuEditaFuncionario(funcionario);
                break;
            case 4:
                int telefone = this.telaFuncionario.pedeTelefone();
                funcionario.setTelefone(telefone);
                this.telaFuncionario.mensagemTelefoneEditadoSucesso();
                menuEditaFuncionario(funcionario);
                break;
            case 5:
                int salario = this.telaFuncionario.pedeSalario();
                funcionario.setSalario(salario);
                this.telaFuncionario.mensagemSalarioEditadoSucesso();
                menuEditaFuncionario(funcionario);
                break;

            case 6:
                this.telaFuncionario.exibeOpcaoCargoFuncionario();
                Cargo cargo = atribuiCargoAoFuncionario();
                funcionario.setCargo(cargo);
                this.telaFuncionario.mensagemCargoEditadoSucesso();
                menuEditaFuncionario(funcionario);
                break;

            case 7:
                exibeMenuFuncionario();
                break;
            default:
                this.telaFuncionario.opcaoInexistente();
                editaFuncionario();
                break;
        }
    }

//
//+-+-+-+-+-+-+-+-+-+- DELETA FUNCIONÁRIO +-+-+-+-+-+-+-+-+-+-
//
    /**
     * Pede qual funcionario o usuario deseja deletar. Exibe o menu de deletar
     * funcionario e chama quem controla o menu.
     */
    public void menuDeletaFuncionario() {
        this.telaFuncionario.mensagemDeletaFuncionario();
        Funcionario funcionario = pedeFuncionario();
        this.telaFuncionario.exibeMensagemFuncionarioSelecionado();
        this.telaFuncionario.exibeFuncionario(funcionario.getMatricula(), funcionario.getNome(), funcionario.getDataNascimento(), funcionario.getTelefone(), funcionario.getSalario(), funcionario.getCargo());
        this.telaFuncionario.exibeMenuConfirmacaoDeletarFuncionario();
        controlaMenuDeletarFuncionario(funcionario);
    }

    /**
     * Controla o que o sistema faz com base na opcao que o usuario selecionar
     * no menu para deletar o funcionario. Caso o usuario aperte 1: deleta o
     * funcionario. Caso o usuario aperte 2: volta para o menu principal do
     * funcionario. Caso o usuario aperte outra tecla qualquer: apresenta a
     * mensagem de opcao inexistente e exibe o menu de deletar um funcionario
     * novamente
     *
     * @param funcionario a ser deletado
     */
    public void controlaMenuDeletarFuncionario(Funcionario funcionario) {
        int opcao = this.telaFuncionario.pedeOpcao();
        switch (opcao) {
            case 1:
                deletaFuncionario(funcionario);
                exibeMenuFuncionario();
                break;
            case 2:
                exibeMenuFuncionario();
                break;
            default:
                this.telaFuncionario.opcaoInexistente();
                controlaMenuDeletarFuncionario(funcionario);
                break;
        }

    }

    /**
     * Caso o funcionario exista na lista de funcionarios ele é deletado da
     * mesma
     *
     * @param funcionario que vai ser deletado
     */
    public void deletaFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            if (funcionarios.contains(funcionario)) {
                funcionarios.remove(funcionario);
                funcionario = null;
                this.telaFuncionario.mensagemFuncionarioDeletadoSucesso();
            }
        }
    }

//
//+-+-+-+-+-+-+-+-+-+- COMUM +-+-+-+-+-+-+-+-+-+-
//
    @Override
    public void deixaFuncionariosSemCargo(Cargo cargoDeletado, Cargo semCargo) {
        if (cargoDeletado != null) {
            for (Funcionario funcionarioCadastrado : funcionarios) {
                if (funcionarioCadastrado.getCargo().getCodigo() == cargoDeletado.getCodigo()) {
                    funcionarioCadastrado.setCargo(semCargo);
                }
            }
        }
    }

    /**
     * Verifica se a string nome é composta de pelo menos 3 caracteres e somente
     * de letras ou espaços. Caso não seja exibe uma mensagem ao usuário e chama
     * o pedeNome novamente.
     *
     * @param nome que o usuário inseriu por meio do teclado.
     */
    public String verificaNome(String nome) {
        if (nome.length() > 2) {
            for (int i = 0; i < nome.length(); i++) {
                char letraAnalisada = nome.charAt(i);
                if (!Character.isLetter(letraAnalisada)) {
                    if (!Character.isSpace(letraAnalisada)) {
                        this.telaFuncionario.mensagemNomeInvalidoLetras();
                        nome = pedeNome();
                    }
                }
            }
        } else {
            this.telaFuncionario.mensagemNomeInvalidoTamanho();
            nome = pedeNome();
        }
        return nome;
    }

    /**
     * Verifica se a matricula inserida pelo usuario ja foi cadastrada
     * anteriormente
     *
     * @return matricula caso não tenha sido cadastrada antes, caso contrario
     * pede para que o usuario insira uma nova matricula
     */
    public int verificaMatriculaInserida() {
        int matricula = this.telaFuncionario.pedeMatricula();
        for (Funcionario funcionarioCadastrado : funcionarios) {
            if (funcionarioCadastrado.getMatricula() == matricula) {
                this.telaFuncionario.mensagemErroMatriculaJaCadastrada();
                matricula = verificaMatriculaInserida();
            }
        }
        return matricula;
    }

    @Override
    public boolean matriculaExiste(int matricula) {
        for (Funcionario funcionarioCadastrado : funcionarios) {
            if (funcionarioCadastrado.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pede para o usuário inserir um nome para o funcionário, chama o método
     * para verificar se o nome é valido e, caso seja, retorna o nome.
     *
     * @return nome do funcionario validado e inserido pelo usuário
     */
    public String pedeNome() {
        String nome = this.telaFuncionario.pedeNome();
        nome = verificaNome(nome);
        return nome;
    }

    @Override
    public Funcionario encontraFuncionarioPelaMatricula(int matricula) {
        for (Funcionario funcionarioCadastrado : this.funcionarios) {
            if (matricula == funcionarioCadastrado.getMatricula()) {
                return funcionarioCadastrado;
            }
        }
        return null;
    }

    /**
     * Pede inicialemente qual a matricula do funcionario que o usuario esta se
     * referindo. Com a matricula inserida pelo usuario, encontra o funcionario
     * em questão.
     *
     * @return funcionario a quem o usuario esta se referindo
     */
    public Funcionario pedeFuncionario() {
        int matricula = this.telaFuncionario.pedeMatricula();
        Funcionario funcionario = encontraFuncionarioPelaMatricula(matricula);
        if (funcionario == null) {
            this.telaFuncionario.mensagemFuncionarioNaoEncontrado();
            funcionario = pedeFuncionario();
        }
        return funcionario;
    }

    /**
     * Pede para o usuario digitar um codigo de cargo;
     *
     * @return codigo digitado pelo usuario
     */
    public int pedeCodigo() {
        int codigo = this.telaFuncionario.pedeCodigo();
        return codigo;
    }

    /**
     * Lista na tela todos os funcionarios cadastrados, com todos os seus
     * atributos. Retorna ao menu funcionario.
     */
    public void listaFuncionarios() {
        this.telaFuncionario.mensagemListaFuncionarios();
        for (Funcionario funcionarioCadastrado : funcionarios) {
            int matricula = funcionarioCadastrado.getMatricula();
            String nome = funcionarioCadastrado.getNome();
            String dataNascimento = funcionarioCadastrado.getDataNascimento();
            int telefone = funcionarioCadastrado.getTelefone();
            int salario = funcionarioCadastrado.getSalario();
            Cargo cargo = funcionarioCadastrado.getCargo();
            this.telaFuncionario.exibeFuncionario(matricula, nome, dataNascimento, telefone, salario, cargo);
        }
        exibeMenuFuncionario();
    }

    /**
     * Verifica qual o cargo responsavel pelo codigo digitado pelo usuario
     *
     * @return cargo com o codigo digitado pelo usuario;
     */
    public Cargo comparaCodigoComCargo() {
        boolean cargoNaoEncontrado = true;
        Cargo cargo = null;

        while (cargoNaoEncontrado) {
            int codigo = pedeCodigo();
            cargo = ControladorPrincipal.getInstance().controladorCargo.encontraCargoPorCodigo(codigo);
            if (cargo != null) {
                cargoNaoEncontrado = false;
            } else {
                this.telaFuncionario.mensagemCargoNaoEncontrado();
            }
        }
        return cargo;
    }
}
