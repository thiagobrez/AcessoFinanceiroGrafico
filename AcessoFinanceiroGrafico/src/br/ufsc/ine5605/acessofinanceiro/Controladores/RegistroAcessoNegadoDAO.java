/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.acessofinanceiro.Controladores;

import br.ufsc.ine5605.acessofinanceiro.Modelos.RegistroAcessoNegado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiagobrezinski
 */
public class RegistroAcessoNegadoDAO {
	
	private HashMap<Integer, RegistroAcessoNegado> cacheRegistros = new HashMap<>();
	private final String filename = "registros.batman";
	
	public RegistroAcessoNegadoDAO() {
		load();
	}
	
	public void put(RegistroAcessoNegado registro) {
		cacheRegistros.put(registro.getMatricula(), registro);
		persist();
	}
	
	public RegistroAcessoNegado get(Integer matricula) {
		return cacheRegistros.get(matricula);
	}
	
	public Collection<RegistroAcessoNegado> getList() {
		return cacheRegistros.values();
	}
	
	private void persist() {
		try {
			FileOutputStream fOS = new FileOutputStream(filename);
			ObjectOutputStream oOS = new ObjectOutputStream(fOS);
			
			oOS.writeObject(cacheRegistros);
			
			oOS.flush();
			fOS.flush();
			
			oOS.close();
			fOS.close();
		} catch(FileNotFoundException ex) {
			Logger.getLogger(RegistroAcessoNegadoDAO.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(RegistroAcessoNegadoDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void load() {
		try {
			FileInputStream fIS = new FileInputStream(filename);
			ObjectInputStream oIS = new ObjectInputStream(fIS);
			
			cacheRegistros = (HashMap<Integer, RegistroAcessoNegado>) oIS.readObject();
			
			oIS.close();
			fIS.close();
			
		} catch (FileNotFoundException ex) {
			persist();
		} catch (IOException | ClassNotFoundException ex) {
			Logger.getLogger(RegistroAcessoNegadoDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
