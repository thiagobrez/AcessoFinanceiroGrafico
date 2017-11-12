/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Modelos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vladimir
 */
public class DataSistemaDAO {

    private Date dataSistema;
    private final String nomeArquivo = "dataSistema.dat";

    public DataSistemaDAO() {
        this.dataSistema = new Date();
        load();
    }

    public void set(Date data) {
        this.dataSistema = data;
        persist();
    }

    public Date get() {
        return this.dataSistema;
    }

    public void persist() {

        try {
            FileOutputStream fOS = new FileOutputStream(nomeArquivo);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);

            oOS.writeObject(this.dataSistema);

            oOS.flush();
            fOS.flush();

            oOS.close();
            fOS.close();

        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void load() {

        try {
            FileInputStream fIS = new FileInputStream(nomeArquivo);
            ObjectInputStream oIS = new ObjectInputStream(fIS);

            dataSistema = (Date) oIS.readObject();

            fIS.close();
            oIS.close();

        } catch (FileNotFoundException ex) {
            persist();
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
