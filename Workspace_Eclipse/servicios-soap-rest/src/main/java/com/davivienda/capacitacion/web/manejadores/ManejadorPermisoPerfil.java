package com.davivienda.capacitacion.web.manejadores;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.davivienda.capacitacion.web.entities.PermisoPerfil;

@Stateless
public class ManejadorPermisoPerfil {
	
	@PersistenceContext(unitName = "UPCapacitacion")
	private EntityManager em;
	
	public PermisoPerfil consultarPorPerfilUrl(String codigoPerfil, String url) {
		try {
			Query  query = this.em.createQuery("SELECT pp FROM PermisoPerfil pp "
												+ " WHERE pp.codigoPerfil = :cp"
												+ " AND pp.url = :urlPar");
			query.setParameter("cp", codigoPerfil);
			query.setParameter("urlPar", url);
			
			return (PermisoPerfil) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	     
	}
}
