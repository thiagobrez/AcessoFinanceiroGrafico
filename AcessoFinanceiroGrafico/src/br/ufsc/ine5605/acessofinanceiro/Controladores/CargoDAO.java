package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Modelos.Cargo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author bruno.bertozzo
 */
public class CargoDAO {

    private HashMap<Integer, Cargo> cacheCargos = new HashMap<>();

    private final String fileName = "cargos.batman";

    public CargoDAO() {
        load();
    }

    public void put(Cargo cargo) {
        cacheCargos.put(cargo.getCodigo(), cargo);
        persist();
    }

    public Cargo get(Integer codigo) {
        return cacheCargos.get(codigo);
    }

    public ArrayList<Cargo> getList() {
        return new ArrayList<Cargo>(cacheCargos.values());
    }

	public ArrayList<Integer> getCodigos() {
		return new ArrayList<>(this.cacheCargos.keySet());
	}
	
    public void remove(Integer codigo) {
        cacheCargos.remove(codigo);
        persist();
    }

    private void persist() {
        try {
            FileOutputStream fOS = new FileOutputStream(fileName);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);

            oOS.writeObject(cacheCargos);

            oOS.flush();
            fOS.flush();

            oOS.close();
            fOS.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void load() {
        try {
            FileInputStream fIS = new FileInputStream(fileName);
            ObjectInputStream oIS = new ObjectInputStream(fIS);

            cacheCargos = (HashMap<Integer, Cargo>) oIS.readObject();

            oIS.close();
            fIS.close();

        } catch (FileNotFoundException ex) {
            persist();
        } catch (IOException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
