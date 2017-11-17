/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorFuncionario;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorPrincipal;
import br.ufsc.ine5605.acessofinanceiro.Modelos.Funcionario;
import br.ufsc.ine5605.acessofinanceiro.Modelos.RegistroAcessoNegado;
import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vladimir
 */
public class TelaFuncionario extends JFrame {

    private ControladorFuncionario controlador;
    private GerenciadorBotoes btManager;
    private GerenciadorCombos comboManager;
    private GridBagConstraints constraints;
    private JPanel painelBotoes;
    private JTable tbItens;
    private JScrollPane spBaseTabela;
    private JButton btCadastraFuncionario;
    private JButton btEditaFuncionario;
    private JButton btDeletaFuncionario;
    private JButton btVoltarMenuPrincipal;

    public TelaFuncionario(ControladorFuncionario owner) {
        super(Constantes.GERENCIAR_FUNCIONARIO_TITULO);
        this.controlador = owner;
        this.btManager = new GerenciadorBotoes();
        this.comboManager = new GerenciadorCombos();

        try {
            configuraTela();
        } catch (ParseException e) {
        }
    }

    private void configuraTela() throws ParseException {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //Configuracao constraints
        this.constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.PAGE_START;
        constraints.gridwidth = 15;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;

        //Configuracao tbItens
        this.tbItens = new JTable();
        tbItens.setPreferredScrollableViewportSize(new Dimension(603, 300));
        tbItens.setFillsViewportHeight(true);
        spBaseTabela = new JScrollPane(tbItens);
        container.add(spBaseTabela, constraints);

        //Configura btCadastraFuncionario
        btCadastraFuncionario = new JButton();
        btCadastraFuncionario.setText(Constantes.GERENCIAR_FUNCIONARIO_CADASTRAR);
        btCadastraFuncionario.addActionListener(btManager);

        //Configura btEditaFuncionario
        btEditaFuncionario = new JButton();
        btEditaFuncionario.setText(Constantes.GERENCIAR_FUNCIONARIO_EDITAR);
        btEditaFuncionario.addActionListener(btManager);

        //Configura btDeletaFuncionario
        btDeletaFuncionario = new JButton();
        btDeletaFuncionario.setText(Constantes.GERENCIAR_FUNCIONARIO_DELETAR);
        btDeletaFuncionario.addActionListener(btManager);

        //Configura btVoltarMenuPrincipal
        btVoltarMenuPrincipal = new JButton();
        btVoltarMenuPrincipal.setText(Constantes.COMUM_BOTAO_VOLTAR_MENU_PRINCIPAL);
        btVoltarMenuPrincipal.addActionListener(btManager);

        //Configuracao painelBotoes
        this.painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));
        this.painelBotoes.setVisible(true);
        painelBotoes.add(btCadastraFuncionario);
        painelBotoes.add(btEditaFuncionario);
        painelBotoes.add(btDeletaFuncionario);
        painelBotoes.add(btVoltarMenuPrincipal);
        constraints.gridy = 11;
        container.add(painelBotoes, constraints);

        setSize(900, 500);
        setLocationRelativeTo(null);

    }

    public void updateData() {

        //Configuracao modelTbItens
        DefaultTableModel modelTbItens = new DefaultTableModel();
        modelTbItens.addColumn(Constantes.COMUM_NOME);
        modelTbItens.addColumn(Constantes.COMUM_MATRICULA);
        modelTbItens.addColumn(Constantes.COMUM_DATA_NASCIMENTO);
        modelTbItens.addColumn(Constantes.COMUM_TELEFONE);
        modelTbItens.addColumn(Constantes.COMUM_SALARIO);
        modelTbItens.addColumn(Constantes.COMUM_CARGO);

        Collection<Funcionario> listaFuncionarios = ControladorPrincipal.getInstance().getListaFuncionarios();
        tbItens.removeAll();
        for (Funcionario funcionario : listaFuncionarios) {
            modelTbItens.addRow(new Object[]{
                funcionario.getNome(),
                funcionario.getMatricula(),
                funcionario.getDataNascimento(),
                funcionario.getTelefone(),
                funcionario.getSalario(),
                funcionario.getCargo().getNome()
            });
        }
        tbItens.setModel(modelTbItens);

        this.repaint();
    }

    public void exibeMenuFuncionario() {
        updateData();
        setVisible(true);
    }

    public void exibeFuncionarioDeletadoComSucesso() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_DELETADO_SUCESSO,
                Constantes.GERENCIAR_FUNCIONARIO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    public void exibeFuncionarioNaoSelecionado() {
        JOptionPane.showMessageDialog(
                null,
                Constantes.GERENCIAR_FUNCIONARIO_NADA_SELECIONADO,
                Constantes.GERENCIAR_FUNCIONARIO_DELETAR,
                JOptionPane.PLAIN_MESSAGE
        );

    }

// -----------------------GERENCIADOR DE BOTÕES---------------------------------
//
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCadastraFuncionario)) {
                ControladorFuncionario.getInstance().exibeCadastraFuncionario();
            }
            if (e.getSource().equals(btEditaFuncionario)) {
                ControladorFuncionario.getInstance().exibeEditarFuncionarioSelecionado(tbItens.getSelectedRow());
            }
            if (e.getSource().equals(btDeletaFuncionario)) {
                ControladorFuncionario.getInstance().deletaFuncionarioSelecionado(tbItens.getSelectedRow());
                updateData();
            }
            if (e.getSource().equals(btVoltarMenuPrincipal)) {
                setVisible(false);
                ControladorFuncionario.getInstance().voltarMenuPrincipal();
            }

        }

    }

    private class GerenciadorCombos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

// +-+-+-+-+-+-+-+-+-+- Antigo +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
//
// +-+-+-+-+-+-+-+-+-+- MENUS +-+-+-+-+-+-+-+-+-+-
//
    /**
     * Exibe na tela o menu com as opcoes do CRUD do funcionario (cadastrar,
     * editar, listar e deletar)
     */
    /**
     * Exibe na tela o titulo do cadastro de funcionaio: Novo Usuario
     */
    public void mensagemNovoFuncionario() {
        System.out.println();
        System.out.println(Constantes.MENSAGEM_NOVO_USUARIO);
        System.out.println();
    }

    /**
     * Exibe na tela o titulo da seção de edição de funcionario: Editar
     * Funcionario
     */
    public void mensagemEditaFuncionario() {
        System.out.println();
        System.out.println(Constantes.TITULO_EDITAR_FUNCIONARIO);
        System.out.println();
    }

    /**
     * Exibe as opcoes que o usuario pode selecionar para editar o cargo de um
     * funcionario: trocar o cargo atual por um existe ou então criar um novo
     */
    public void exibeOpcaoCargoFuncionario() {
        System.out.println();
        System.out.println(Constantes.O_QUE_DESEJA_FAZER);
        System.out.println();
        System.out.println(Constantes.USAR_CARGO_EXISTENTE);
        System.out.println(Constantes.CRIAR_CARGO_PARA_FUNCIONARIO);
        System.out.println();

    }

    /**
     * Exibe um menu com as opcoes que o usuario pode selecionar com relação a
     * editar um funcionario. (Alterar o nome, alterar a matricula, alterar a
     * data de nascimento, alterar o telefone, alterar o salario, alterar o
     * cargo, voltar ao menu de gerenciar funcionario)
     */
    public void exibeMenuEditaFuncionario() {
        System.out.println();
        System.out.println(Constantes.O_QUE_DESEJA_FAZER);
        System.out.println();
        System.out.println(Constantes.ALTERAR_NOME);
        System.out.println(Constantes.ALTERAR_MATRICULA);
        System.out.println(Constantes.ALTERAR_DATA_NASCIMENTO);
        System.out.println(Constantes.ALTERAR_TELEFONE);
        System.out.println(Constantes.ALTERAR_SALARIO);
        System.out.println(Constantes.ALTERAR_CARGO);
        System.out.println(Constantes.OPCAO_VOLTAR);
        System.out.println();

    }

    /**
     * Exibe a mensagem "Funcionario selecionado: "
     */
    public void exibeMensagemFuncionarioSelecionado() {
        System.out.println();
        System.out.println(Constantes.FUNCIONARIO_SELECIONADO);
    }

    /**
     * Exibe um menu pedindo ao usuário se ele confirma que quer deletar o
     * funcionário selecionado ou se ele não quer deletar e quer voltar ao menu
     * anterior
     */
    public void exibeMenuConfirmacaoDeletarFuncionario() {
        System.out.println();
        System.out.println(Constantes.TEM_CERTEZA_EXCLUIR);
        System.out.println(Constantes.SIM);
        System.out.println(Constantes.NAO);
        System.out.println();

    }

    /**
     * exibe a data inserida e apresenta um menu para que o usuario confirme a
     * data cadastrada ou não, caso não confirme ele deve inserir uma nova data
     *
     * @param dataInserida pelo usuario
     */
    public void exibeMenuConfirmacaoCadastroDataNascimento(String dataInserida) {
        System.out.println();
        System.out.println(Constantes.EXIBE_DATA_CADASTRADA + dataInserida);
        System.out.println(Constantes.CONFIRMA_DATA_CADASTRADA);
        System.out.println(Constantes.SIM);
        System.out.println(Constantes.NAO);
        System.out.println();
    }

//
// +-+-+-+-+-+-+-+-+-+- MENSAGENS +-+-+-+-+-+-+-+-+-+-
//
    /**
     * exibe na tela a mensagem de cargo editado com sucesso
     */
    public void mensagemCargoEditadoSucesso() {
        System.out.println();
        System.out.println(Constantes.CARGO_EDITADO_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de salario editado com sucesso
     */
    public void mensagemSalarioEditadoSucesso() {
        System.out.println();
        System.out.println(Constantes.SALARIO_EDITADO_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de telefone editado com sucesso
     */
    public void mensagemTelefoneEditadoSucesso() {
        System.out.println();
        System.out.println(Constantes.TELEFONE_EDITADO_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de data de nascimento editaca com sucesso
     */
    public void mensagemDataNascimentoEditadaSucesso() {
        System.out.println();
        System.out.println(Constantes.DATA_NASCIMENTO_EDITADA_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de matricula editada com sucesso
     */
    public void mensagemMatriculaEditadaSucesso() {
        System.out.println();
        System.out.println(Constantes.MATRICULA_EDITADA_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de nome editado com sucesso
     */
    public void mensagemNomeEditadoSucesso() {
        System.out.println();
        System.out.println(Constantes.NOME_EDITADO_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de funcionario não encontrado
     */
    public void mensagemFuncionarioNaoEncontrado() {
        System.out.println();
        System.out.println(Constantes.MENSAGEM_FUNCIONARIO_NAO_ENCONTRADO);
        System.out.println();
    }

    /**
     * Exibe a mensagem de erro de nome inserido invalido: deve conter no minimo
     * 3 letras
     */
    public void mensagemNomeInvalidoTamanho() {
        System.out.println();
        System.out.println(Constantes.NOME_INVALIDO_TAMANHO);
        System.out.println();
    }

    /**
     * Exibe a mensagem de erro de data de nascimento invalida: não está no
     * formato pedido
     */
    public void mensagemErroDataNascimento() {
        System.out.println();
        System.out.println(Constantes.DATA_NASCIMENTO_INVALIDA);
        System.out.println();
    }

    /**
     * Exibe a mensagem de erro de matricula ja cadastrada
     */
    public void mensagemErroMatriculaJaCadastrada() {
        System.out.println();
        System.out.println(Constantes.MATRICULA_JA_CADASTRADA);
        System.out.println();
    }

    /**
     * Exibe a mensagem de erro de opcao inexistente
     */
    public void opcaoInexistente() {
        System.out.println();
        System.out.println(Constantes.OPCAO_INEXISTENTE);
        System.out.println();
        System.out.println(Constantes.O_QUE_DESEJA_FAZER);
        System.out.println();
    }

    /**
     * exibe a mensagem de funcionario cadastrado com sucesso
     */
    public void mensagemFuncionarioCadastrado() {
        System.out.println();
        System.out.println(Constantes.FUNCIONARIO_CADASTRADO_SUCESSO);
        System.out.println();
    }

    /**
     * Exibe a mensagem de erro de cargo nao encontrado
     */
    public void mensagemCargoNaoEncontrado() {
        System.out.println();
        System.out.println(Constantes.CARGO_NAO_ENCONTRADO);
        System.out.println();
    }

    /**
     * Exibe a mensagem de funcionario deletado com sucesso
     */
    public void mensagemFuncionarioDeletadoSucesso() {
        System.out.println();
        System.out.println(Constantes.FUNCIONARIO_DELETADO_SUCESSO);
        System.out.println();
    }

    /**
     * Exibe a mensagem de erro de nome inserido invalido: deve conter somente
     * letras
     */
    public void mensagemNomeInvalidoLetras() {
        System.out.println();
        System.out.println(Constantes.NOME_INVALIDO_LETRAS);
        System.out.println();
    }

//
// +-+-+-+-+-+-+-+-+-+- COMUM +-+-+-+-+-+-+-+-+-+-
//
    /**
     * Pede que o usuario insira um numero correspondente a opcao que ele deseja
     * selecionar
     *
     * @return opcao inserida pelo usuario
     */
    public int pedeOpcao() {
        int opcao = 0;
        boolean opcaoValida = true;
        while (opcaoValida) {
            try {
                System.out.println();
//                opcao = teclado.nextInt();
//                teclado.nextLine();
                System.out.println();
                opcaoValida = false;

            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.OPCAO_INVALIDA);
//                teclado.next();
            }
        }
        return opcao;

    }

    /**
     * Pede que o usuário insira o nome do funcionário que deseja cadastrar
     *
     * @return nome do funcionário
     */
    public String pedeNome() {
        System.out.println();
        System.out.println(Constantes.DIGITE_NOME);
        String nome = "batata";//teclado.nextLine();
        System.out.println();
        return nome;
    }

    /**
     * Pede que o usuário insira a matricula do funcionário que deseja cadastrar
     *
     * @return matricula do funcionario
     */
    public int pedeMatricula() {
        int matricula = 0;
        boolean matriculaInvalida = true;
        while (matriculaInvalida) {
            try {
                System.out.println();
                System.out.println(Constantes.DIGITE_MATRICULA);
//                matricula = teclado.nextInt();
//                teclado.nextLine();
                System.out.println();
                matriculaInvalida = false;

            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.MATRICULA_INVALIDA);
//                teclado.next();
                System.out.println();

            }

        }
        return matricula;

    }

    /**
     * Pede que o usuário insira a data de nascimento do funcionário que deseja
     * cadastrar no formato dd/MM/yyyy
     *
     * @return data de nascimento do funcionário
     */
    public String pedeDataNascimento() {
        System.out.println();
        System.out.println(Constantes.DIGITE_DATA_NASCIMENTO);
        String dataNascimento = "batata";//teclado.nextLine();
        return dataNascimento;
    }

    /**
     * Pede que o usuário insira o telefone do funcionário que deseja cadastrar
     *
     * @return telefone do funcionário
     */
    public int pedeTelefone() {
        int telefone = 0;
        boolean telefoneInvalido = true;

        while (telefoneInvalido) {
            try {
                System.out.println();
                System.out.println(Constantes.DIGITE_TELEFONE);
//                telefone = teclado.nextInt();
//                teclado.nextLine();
                System.out.println();
                telefoneInvalido = false;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.TELEFONE_INVALIDO);
//                teclado.nextLine();
            }

        }
        return telefone;
    }

    /**
     * Pede que o usuário insira o salario do funcionário que deseja cadastrar
     *
     * @return salario do funcionario
     */
    public int pedeSalario() {
        int salario = 0;
        boolean salarioInvalido = true;

        while (salarioInvalido) {
            try {
                System.out.println();
                System.out.println(Constantes.DIGITE_SALARIO);
//                salario = teclado.nextInt();
//                teclado.nextLine();
                System.out.println();
                salarioInvalido = false;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.SALARIO_INVALIDO);
//                teclado.nextLine();
            }

        }
        return salario;

    }

    /**
     * Pede que o usuário insira o codigo do cargo que deseja selecionar
     *
     * @return codigo do cargo
     */
    public int pedeCodigo() {
        int codigo = 0;
        boolean codigoInvalido = true;

        while (codigoInvalido) {
            try {
                System.out.println();
                System.out.println(Constantes.DIGITE_CODIGO);
//                codigo = teclado.nextInt();
//                teclado.nextLine();
                System.out.println();
                codigoInvalido = false;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.CODIGO_INVALIDO);
//                teclado.nextLine();
            }

        }
        return codigo;
    }

    /**
     * Exibe na tela a mensagem: "Lista de funcionarios cadastrados: "
     */
    public void mensagemListaFuncionarios() {
        System.out.println();
        System.out.println(Constantes.LISTA_DE_FUNCIONARIOS);
        System.out.println();
    }

    /**
     * Exibe na tela o titulo do menu para deletar funcionario
     */
    public void mensagemDeletaFuncionario() {
        System.out.println();
        System.out.println(Constantes.TITULO_DELETAR_FUNCIONARIO);
        System.out.println();

    }

    /**
     * Exibe na tela o funcionário selecionado (matricula, nome, data de
     * nascimento, telefone, salario, cargo)
     *
     * @param matricula do funcionario selecionado
     * @param nome do funcionario selecionado
     * @param dataNascimento do funcionario selecionado
     * @param telefone do funcionario selecionado
     * @param salario do funcionario selecionado
     * @param cargo do funcionario selecionado
     */
    public void exibeFuncionario(int matricula, String nome, String dataNascimento, int telefone, int salario, Cargo cargo) {
        System.out.println();
        System.out.println(Constantes.MATRICULA + matricula);
        System.out.println(Constantes.NOME + nome);
        System.out.println(Constantes.DATA_NASCIMENTO + dataNascimento);
        System.out.println(Constantes.TELEFONE + telefone);
        System.out.println(Constantes.SALARIO + salario);
        System.out.println(Constantes.CARGO + cargo.getNome());
        System.out.println();
    }

    /**
     * Exibe na tela o nome e o codigo de um cargo
     *
     * @param codigo do cargo
     * @param nome do cargo
     */
    public void listaCargo(int codigo, String nome) {
        System.out.println();
        System.out.println(Constantes.CODIGO_DO_CARGO + codigo);
        System.out.println(Constantes.NOME_DO_CARGO + nome);
        System.out.println();
    }
}
