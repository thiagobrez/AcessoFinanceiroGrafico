/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Interfaces.IControladorDataSistema;
import br.ufsc.ine5605.acessofinanceiro.Modelos.DataSistemaDAO;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaAlterarDataSistema;
import br.ufsc.ine5605.acessofinanceiro.Telas.TelaDataHoraSistema;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author João Grasel
 */
public class ControladorDataSistema implements IControladorDataSistema {

    private static ControladorDataSistema controladorDataSistema;

    private TelaDataHoraSistema telaDataHora;
    private TelaAlterarDataSistema telaAlterarDataSistema;
    private DataSistemaDAO dataSistema;

    public ControladorDataSistema() {
        this.telaDataHora = new TelaDataHoraSistema(this);
        this.telaAlterarDataSistema = new TelaAlterarDataSistema(this);
        this.dataSistema = new DataSistemaDAO();
    }

    public static ControladorDataSistema getInstance() {

        if (controladorDataSistema == null) {
            controladorDataSistema = new ControladorDataSistema();

        }
        return controladorDataSistema;
    }

// ================== EXIBIÇÃO DE TELAS ==================
    /**
     * Exibe a tela principal referente a data e hora do sistema.
     */
    public void exibeMenuDataSistema() {
        this.telaDataHora.exibeMenuDataSistema();
    }

    /**
     * Exibe a tela de alteração da data e hora do sistema.
     */
    public void exibeAlterarDataSistema() {
        this.telaAlterarDataSistema.exibeAlterarDataSistema();
    }

    /**
     * Volta ao menu principal (tela principal).
     */
    public void voltarMenuPrincipal() {
        ControladorPrincipal.getInstance().exibeMenuPrincipal();
    }

// ================== ALTERAÇÃO DA DATA E HORA DO SISTEMA ==================
    /**
     * Atualiza a data e hora do sistema utilizando a data e hora inseridos pelo
     * usuario. Caso o formato inserido esteja correto o metodo atualiza a data
     * e hora do sistema, imprime uma mensagem de sucesso e atualiza a tela.
     * Caso o formato esteja incorreto o metodo imprime uma mensagem de erro e
     * nao atualiza a data e hora do sistema.
     *
     *
     * @param dataEHoraInseridos pelo usuário, deve estar no formato dd-MM-yyyy
     * HH:mm
     */
    public void alterarDataSistema(String dataEHoraInseridos) {
        try {
            Date data = new SimpleDateFormat("dd-MM-yyyy HH:mm")
                    .parse(dataEHoraInseridos);
            this.dataSistema.set(data);
            this.telaAlterarDataSistema.exibeDataHoraAlteradoSucesso();
            this.telaDataHora.updateData();
            this.telaAlterarDataSistema.fechar();

        } catch (ParseException ex) {
            this.telaAlterarDataSistema.exibeErroFormatoAlteracaoDataHora();
        }
    }

// ================== GETTERS ==================
    @Override
    public Date getDataSistema() {
        return this.dataSistema.get();
    }

    /**
     * Getter da data do sistema no formato String.
     *
     * @return data do sistema (String)
     */
    public String getDataSistemaString() {
        Date data = getDataSistema();
        return data.toString();
    }

}
