package br.ufsc.ine5605.acessofinanceiro.Modelos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vladimir
 */
public class FuncionarioDAO {

    private HashMap<Integer, Funcionario> cacheFuncionarios = new HashMap<>();

    private final String nomeArquivo = "funcionarios.dat";

    public FuncionarioDAO() {
        load();
    }

    public Set<Integer> getMatriculas() {
        return this.cacheFuncionarios.keySet();
    }

    public void put(Funcionario funcionario) {
        this.cacheFuncionarios.put(funcionario.getMatricula(), funcionario);
        persist();
    }

    public Funcionario get(Integer matricula) {
        return this.cacheFuncionarios.get(matricula);
    }

    public Collection<Funcionario> getList() {
        return this.cacheFuncionarios.values();
    }

    public void remove(Funcionario funcionario) {
        this.cacheFuncionarios.remove(funcionario.getMatricula());
        persist();
    }

    public void persist() {

        try {
            FileOutputStream fOS = new FileOutputStream(nomeArquivo);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);

            oOS.writeObject(this.cacheFuncionarios);

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

            cacheFuncionarios = (HashMap<Integer, Funcionario>) oIS.readObject();

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
