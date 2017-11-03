/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Interfaces.IControladorDataSistema;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaDataHoraSistema;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author vladimir
 */
public class ControladorDataSistema implements IControladorDataSistema {

    private TelaDataHoraSistema telaDataHora;
    private Date dataHoraSistema;

    public ControladorDataSistema() {
        this.telaDataHora = new TelaDataHoraSistema(this);
        this.dataHoraSistema = new Date();
    }

    /**
     * compreende o controle da totalidade do menu de data e hora do sistema,
     * chamando os metodos para exibir a data e hora e exibir as opcoes
     * disponiveis
     */
    public void menuDataHoraSistema() {
        exibeDataHoraSistema();
        exibeMenuDataHoraSistema();
        controlaMenuDataHoraSistema();
    }

    /**
     * exibe a data e hora atuais do sistema
     */
    public void exibeDataHoraSistema() {
        this.telaDataHora.exibeDataHoraSistema(dataHoraSistema);
    }

    /**
     * exibe o menu de opcoes disponiveis para o usuario quanto a data e a hora
     * do sistema
     */
    public void exibeMenuDataHoraSistema() {
        this.telaDataHora.exibeMenuDataHoraSistema();
    }

    /**
     * controla o que o sistema faz com base na opcao selecionada pelo usuario,
     * caso selecione 1: executa o metodo que realiza a atualização da data e
     * hora do sistema. Caso selecione 2: volta para o menu principal. caso
     * selecione outra opcao: exibe a mensagem de opcao inexistente e pede que
     * insira novamente uma opcao
     */
    public void controlaMenuDataHoraSistema() {
        int opcao = this.telaDataHora.pedeOpcao();
        switch (opcao) {
            case 1:
                executaOpcao1Menu();
                break;
            case 2:
                ControladorPrincipal.getInstance().exibeMenuPrincipal();
                break;
            default:
                this.telaDataHora.opcaoInexistente();
                controlaMenuDataHoraSistema();
                break;
        }
    }

    /**
     * Chama o metodo para alterar a data e hora do sistema, imprime na tela e
     * chama o metodo de confirmacao de alteracao de data e hora.
     */
    public void executaOpcao1Menu() {
        this.dataHoraSistema = alteraDataHoraSistema();
        exibeDataHoraSistema();
        this.telaDataHora.exibeConfirmacaoDataHoraSistema();
        controlaConfirmacaoDataHoraSistema();
    }

    /**
     * altera a data e a hora do sistema com base na nova data inserida pelo
     * usuario
     *
     * @return dataEHora atualizadas do sistema
     */
    public Date alteraDataHoraSistema() {
        this.telaDataHora.exibeMensagemPedeDataHoraSistema();
        String dataEHoraInseridos = this.telaDataHora.pedeDataHoraSistema();
        try {
            Date data = new SimpleDateFormat("dd-MM-yyyy HH:mm")
                    .parse(dataEHoraInseridos);
            return data;
        } catch (ParseException ex) {
            this.telaDataHora.mensagemDataInvalida();
            alteraDataHoraSistema();
        }
        return null;
    }

    @Override
    public Date getDataSistema() {
        return this.dataHoraSistema;
    }

    /**
     * Controla o menu de confirmacao da alteracao de data e hora do sistema,
     * onde caso o usuario selecione 1: exibe a mensagem de que a data e a hora
     * foram atualizadas com sucesso. caso selecione 2: exibe a mensagem de data
     * e hora nao atualizados, chama o metodo para que o usuario atualize a data
     * e a hora. caso selecione outra tecla: exibe a mensagem de opcao
     * inexistente e pede que o usuario insira outra opcao
     */
    public void controlaConfirmacaoDataHoraSistema() {
        int opcao = this.telaDataHora.pedeOpcao();
        switch (opcao) {
            case 1:
                this.telaDataHora.mensagemDataHoraAtualizadosSucesso();
                menuDataHoraSistema();
                break;
            case 2:
                this.telaDataHora.mensagemDataHoraNaoAtualizados();
                executaOpcao1Menu();
                break;
            default:
                this.telaDataHora.opcaoInexistente();
                controlaConfirmacaoDataHoraSistema();
                break;
        }
    }
}
