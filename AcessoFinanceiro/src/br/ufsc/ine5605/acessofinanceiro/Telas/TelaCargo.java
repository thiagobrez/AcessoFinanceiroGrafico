/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Telas;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Constantes;
import br.ufsc.ine5605.acessofinanceiro.Controladores.ControladorCargo;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class TelaCargo {

    private Scanner teclado;
    private ControladorCargo controlador;

    public TelaCargo(ControladorCargo owner) {
        this.controlador = owner;
        this.teclado = new Scanner(System.in);
    }

    /**
     * Exibe na tela o menu com as opcoes do CRUD do cargo (cadastrar, editar,
     * listar e deletar)
     */
    public void exibeMenuCargo() {
        System.out.println();
        System.out.println(Constantes.GERENCIAR_CARGO);
        System.out.println();
        System.out.println(Constantes.CADASTRAR_CARGO);
        System.out.println(Constantes.EDITAR_CARGO);
        System.out.println(Constantes.LISTAR_CARGOS);
        System.out.println(Constantes.DELETAR_CARGO);
        System.out.println(Constantes.VOLTAR_AO_MENU_PRINCIPAL);
        System.out.println();
        System.out.println(Constantes.O_QUE_DESEJA_FAZER);
        System.out.println();
    }

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
                opcao = teclado.nextInt();
                teclado.nextLine();
                System.out.println();
                opcaoValida = false;

            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.OPCAO_INVALIDA);
                teclado.next();
            }
        }
        return opcao;
    }

    /**
     * Pede que o usuário insira o nome do cargo que deseja cadastrar
     *
     * @return nome do cargo
     */
    public String pedeNome() {
        System.out.println();
        System.out.println(Constantes.DIGITE_NOME_CARGO);
        String nome = teclado.nextLine();
        System.out.println();
        return nome;
    }
    
    /**
     * Pede que o usuario insira um numero correspondente a opcao do tipo de cargo
     * que deseja selecionar
     *
     * @return opcao inserida pelo usuario
     */
    public int pedeTipoCargo() {
		int tipoCargo = 0;
		boolean tipoCargoInvalido = true;
        while (tipoCargoInvalido) {
            try {
				System.out.println("");
				System.out.println(Constantes.ESCOLHA_TIPO_CARGO);
				System.out.println("");
				System.out.println(Constantes.TIPO_CARGO_GERENCIAL);
				System.out.println(Constantes.TIPO_CARGO_COMERCIAL);
				System.out.println(Constantes.TIPO_CARGO_ESPECIAL);
				System.out.println(Constantes.TIPO_CARGO_COMUM);
				System.out.println(Constantes.TIPO_CARGO_SEM_ACESSO);
				System.out.println("");
				tipoCargo = teclado.nextInt();
                tipoCargoInvalido = false;
            } catch (InputMismatchException e) {
                System.out.println("");
                System.out.println(Constantes.OPCAO_INVALIDA);
                teclado.next();
            }
        }
        return tipoCargo;
    }

    /**
     * Exibe na tela o titulo do cadastro de cargo: Novo Cargo
     */
    public void mensagemNovoCargo() {
        System.out.println();
        System.out.println(Constantes.MENSAGEM_NOVO_CARGO);
        System.out.println();
    }

    /**
     * Exibe na tela o titulo da seção de edição de cargo: Editar Cargo
     */
    public void mensagemEditaCargo() {
        System.out.println();
        System.out.println(Constantes.TITULO_EDITAR_CARGO);
        System.out.println();
    }
    
    /**
     * Exibe na tela a mensagem: "Lista de cargos"
     */
    public void mensagemListaCargos() {
        System.out.println();
        System.out.println(Constantes.LISTA_DE_CARGOS);
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
     * Exibe na tela o cargo selecionado (codigo, nome, ehGerencial, temAcessoAoFinanceiro)
     *
     * @param codigo do cargo selecionado
     * @param nome do cargo selecionado
     * @param ehGerencial para o cargo selecionado 
     * @param temAcessoAoFinanceiro para o cargo selecionado
     */
    public void exibeCargo(int codigo, String nome, boolean ehGerencial, boolean temAcessoAoFinanceiro) {
		String textoEhGerencial = ehGerencial == true ? "sim" : "nao";
		String textoTemAcessoAoFinanceiro = temAcessoAoFinanceiro == true ? "sim" : "nao";
        System.out.println();
        System.out.println(Constantes.CODIGO + codigo);
        System.out.println(Constantes.NOME + nome);
        System.out.println(Constantes.EH_GERENTE + textoEhGerencial);
        System.out.println(Constantes.TEM_ACESSO_AO_FINANCEIRO + textoTemAcessoAoFinanceiro);
        System.out.println();
    }

    /**
     * Exibe a mensagem "Cargo selecionado: "
     */
    public void exibeCargoSelecionado() {
        System.out.println();
        System.out.println(Constantes.CARGO_SELECIONADO);
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
                System.out.println(Constantes.DIGITE_CODIGO_CARGO);
                codigo = teclado.nextInt();
                teclado.nextLine();
                System.out.println();
                codigoInvalido = false;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.CODIGO_INVALIDO);
                teclado.nextLine();
            }

        }
        return codigo;
    }
    
    /**
     * Exibe a mensagem de erro de codigo ja cadastrada
     */
    public void mensagemErroCodigoJaCadastrada() {
        System.out.println();
        System.out.println(Constantes.CODIGO_JA_CADASTRADO);
        System.out.println();
    }

    /**
     * Exibe na tela o titulo do menu para deletar cargo
     */
    public void mensagemDeletaCargo() {
        System.out.println();
        System.out.println(Constantes.TITULO_DELETAR_CARGO);
        System.out.println();
    }

    /**
     * exibe a mensagem de cargo cadastrado com sucesso
     */
    public void mensagemCargoCadastrado() {
        System.out.println();
        System.out.println(Constantes.CARGO_CADASTRADO_SUCESSO);
        System.out.println();
    }

    /**
     * Exibe a mensagem "Cargo selecionado: "
     */
    public void exibeMensagemCargoSelecionado() {
        System.out.println();
        System.out.println(Constantes.CARGO_SELECIONADO);
        System.out.println();
    }

    /**
     * Exibe um menu pedindo ao usuário se ele confirma que quer deletar o
     * cargo selecionado ou se ele não quer deletar e quer voltar ao menu
     * anterior
     */
    public void exibeMenuConfirmacaoDeletarCargo() {
        System.out.println();
        System.out.println(Constantes.CONFIRMACAO_EXCLUSAO_CARGO);
        System.out.println();
        System.out.println(Constantes.SIM);
        System.out.println(Constantes.NAO);
        System.out.println();
    }
    
    /**
     * exibe na tela a mensagem de cargo editado com sucesso
     */
    public void mensagemCargoDeletadoSucesso() {
        System.out.println();
        System.out.println(Constantes.CARGO_DELETADO_SUCESSO);
        System.out.println();
    }
    
    /**
     * Exibe a mensagem de erro de opcao inexistente
     */
    public void exibeOpcaoInexistente() {
        System.out.println();
        System.out.println(Constantes.OPCAO_INEXISTENTE);
        System.out.println();
        System.out.println(Constantes.O_QUE_DESEJA_FAZER);
        System.out.println();
    }
    
    /**
     * Exibe um menu com as opcoes que o usuario pode selecionar com relação a
     * editar um cargo. (Alterar o nome, alterar a codigo, alterar função gerencial, 
     * alterar permissão de acesso ao financeiro, voltar ao menu de gerenciar cargo)
     */
    public void exibeMenuEditaCargo() {
        System.out.println();
        System.out.println(Constantes.O_QUE_DESEJA_FAZER);
        System.out.println();
        System.out.println(Constantes.ALTERAR_NOME_CARGO);
        System.out.println(Constantes.ALTERAR_CODIGO_CARGO);
        System.out.println(Constantes.ALTERAR_EH_GERENCIAL);
        System.out.println(Constantes.ALTERAR_TEM_ACESSO);
        System.out.println(Constantes.ALTERAR_HORARIOS_ACESSO);
        System.out.println(Constantes.OPCAO_VOLTAR_CARGO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de cargo não encontrado
     */
    public void mensagemCargoNaoEncontrado() {
        System.out.println();
        System.out.println(Constantes.MENSAGEM_CARGO_NAO_ENCONTRADO);
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
     * exibe na tela a mensagem de codigo editado com sucesso
     */
    public void mensagemCodigoEditadoSucesso() {
        System.out.println();
        System.out.println(Constantes.CODIGO_EDITADO_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de função gerencial editada com sucesso
     */
    public void mensagemGerencialEditadoSucesso() {
        System.out.println();
        System.out.println(Constantes.EH_GERENCIAL_EDITADO_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de permissão de acesso editada com sucesso
     */
    public void mensagemAcessoEditadoSucesso() {
        System.out.println();
        System.out.println(Constantes.TEM_ACESSO_EDITADO_SUCESSO);
        System.out.println();
    }

    /**
     * Pede que o usuario insira um numero correspondente a opcao de atribuir
     * ou não a função gerencial a um cargo
     *
     * @return opcao inserida pelo usuario
     */
    public int pedeSeEhGerencial() {
        int ehGerencial = 0;
        boolean gerencialInvalido = true;

        while (gerencialInvalido) {
            try {
                System.out.println();
                System.out.println(Constantes.TORNAR_CARGO_GERENCIAL);
                System.out.println();
                System.out.println(Constantes.SIM);
                System.out.println(Constantes.NAO);
                ehGerencial = teclado.nextInt();
                teclado.nextLine();
                System.out.println();
                gerencialInvalido = false;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.OPCAO_INVALIDA);
                System.out.println();
                System.out.println(Constantes.O_QUE_DESEJA_FAZER);
                System.out.println();
                teclado.nextLine();
            }
        }
        return ehGerencial;
    }

    /**
     * Pede que o usuario insira um numero correspondente a opcao de um cargo ter 
     * acesso ou não ao financeiro
     *
     * @return opcao inserida pelo usuario
     */
    public int pedeTemAcessoAoFinanceiro() {
        int temAcessoAoFinanceiro = 0;
        boolean temAcessoInvalido = true;

        while (temAcessoInvalido) {
            try {
                System.out.println();
                System.out.println(Constantes.ESCOLHA_TEM_ACESSO);
                System.out.println();
                System.out.println(Constantes.SIM);
                System.out.println(Constantes.NAO);
                temAcessoAoFinanceiro = teclado.nextInt();
                teclado.nextLine();
                System.out.println();
                temAcessoInvalido = false;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(Constantes.OPCAO_INVALIDA);
                System.out.println();
                System.out.println(Constantes.O_QUE_DESEJA_FAZER);
                System.out.println();
                teclado.nextLine();
            }
        }
        return temAcessoAoFinanceiro;
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
     * Pede que o usuário insira a hora de inicio de expediente da manhã do cargo
     * que deseja cadastrar no formato HH:mm
     *
     * @return hora de inicio pela manhã
     */
    public String pedeHoraInicioManha() {
        System.out.println();
        System.out.println(Constantes.DIGITE_HORA_INICIO_MANHA);
        teclado.nextLine();
        String horaInicioManha = teclado.nextLine();
        System.out.println();
        return horaInicioManha;
    }
    
    /**
     * Pede que o usuário insira a hora do fim do expediente da manhã do cargo
     * que deseja cadastrar no formato HH:mm
     *
     * @return hora do fim pela manhã
     */
    public String pedeHoraFimManha() {
        System.out.println();
        System.out.println(Constantes.DIGITE_HORA_FIM_MANHA);
        String horaFimManha = teclado.nextLine();
        System.out.println();
        return horaFimManha;
    }

    /**
     * Pede que o usuário insira a hora de inicio de expediente da tarde do cargo
     * que deseja cadastrar no formato HH:mm
     *
     * @return hora de inicio pela tarde
     */
    public String pedeHoraInicioTarde() {
        System.out.println();
        System.out.println(Constantes.DIGITE_HORA_INICIO_TARDE);
        String horaInicioTarde = teclado.nextLine();
        System.out.println();
        return horaInicioTarde;
    }

    /**
     * Pede que o usuário insira a hora do fim do expediente da tarde do cargo
     * que deseja cadastrar no formato HH:mm
     *
     * @return hora do fim pela tarde
     */
    public String pedeHoraFimTarde() {
        System.out.println();
        System.out.println(Constantes.DIGITE_HORA_FIM_TARDE);
        String horaFimTarde = teclado.nextLine();
        System.out.println();
        return horaFimTarde;
    }

    /**
     * Pede que o usuário insira a hora do inicio do expediente do cargo
     * especial no formato HH:mm
     *
     * @return hora do inicio do expediente do cargo especial
     */
    public String pedeHoraInicioEspecial() {
        System.out.println();
        System.out.println(Constantes.DIGITE_HORA_INICIO_ESPECIAL);
        String horaInicioEspecial = teclado.nextLine();
        System.out.println();
        return horaInicioEspecial;
    }

    /**
     * Pede que o usuário insira a hora do fim do expediente do cargo
     * especial no formato HH:mm
     *
     * @return hora do fim do expediente do cargo especial
     */
    public String pedeHoraFimEspecial() {
        System.out.println();
        System.out.println(Constantes.DIGITE_HORA_FIM_ESPECIAL);
        String horaFimEspecial = teclado.nextLine();
        System.out.println();
        return horaFimEspecial;
    }
    
    /**
     * Exibe a mensagem de erro de formato de hora inserido é invalido: utilize o 
     * formato solicitado 
     */
    public void exibeHoraInseridaFormatoIncorreto() {
        System.out.println();
        System.out.println(Constantes.HORA_INSERIDA_FORMATO_INCORRETO);
        System.out.println();
    }

    /**
     * Exibe a mensagem de erro interno: as constantes usadas para formatação 
     * dos horários de acesso dos cargos estão inválidas
     */
    public void exibeErroConstantesFormatador() {
        System.out.println();
        System.out.println(Constantes.ERRO_INTERNO_CONSTANTES_FORMATADOR);
        System.out.println();
    }

//	public String pedeHora(String constante) {
//		System.out.println();
//        System.out.println(constante);
//        String hora = teclado.nextLine();
//        System.out.println();
//        return hora;
//	}
    
    /**
     * exibe na tela a mensagem de horarios editados com sucesso
     */
    public void mensagemHorariosEditadosSucesso() {
        System.out.println();
        System.out.println(Constantes.HORARIOS_EDITADOS_SUCESSO);
        System.out.println();
    }

    /**
     * exibe na tela a mensagem de não é possível editar os horários de acesso: 
     * o cargo não possui acesso.
     */
    public void exibeEditaHorariosCargoSemAcesso() {
        System.out.println();
        System.out.println(Constantes.EDITA_HORARIOS_CARGO_SEM_ACESSO);
        System.out.println();
    }

}
