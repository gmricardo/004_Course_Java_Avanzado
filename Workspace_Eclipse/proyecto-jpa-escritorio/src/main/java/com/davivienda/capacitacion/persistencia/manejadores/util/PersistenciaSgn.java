package com.davivienda.capacitacion.persistencia.manejadores.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenciaSgn {
	
	private static PersistenciaSgn instancia = null;
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	private PersistenciaSgn() {
		this.emf = Persistence.createEntityManagerFactory("UPCapacitacion");
		this.em = emf.createEntityManager();
	}

	public static PersistenciaSgn obtenerInstancia() {
		if (PersistenciaSgn.instancia == null ) {
			PersistenciaSgn.instancia = new PersistenciaSgn();
		}
		return PersistenciaSgn.instancia;
	}
	
	public EntityManager getEm() {
		return em;
	}
	
	public void iniciarTx() {
		this.em.getTransaction().begin();
	}
	
	public void commitTx() {
		this.em.getTransaction().commit();
	}
	
	public void deshacerTx() {
		this.em.getTransaction().rollback();
	}
	
	public void cerrarPersistencia() {
		this.em.close();
		this.emf.close();
	}

}
