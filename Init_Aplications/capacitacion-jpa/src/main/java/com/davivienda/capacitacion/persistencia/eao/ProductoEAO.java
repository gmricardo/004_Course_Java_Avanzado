package com.davivienda.capacitacion.persistencia.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.davivienda.capacitacion.persistencia.entities.Producto;

public class ProductoEAO {

	private EntityManagerFactory emf;
	private EntityManager em = null;
	
	public ProductoEAO() {
		emf = Persistence.createEntityManagerFactory("UPCapacitacion");
        em = emf.createEntityManager();
	}
	
	public void crear(Producto producto) {
        em.persist(producto);
    }
	public void actualizar(Producto producto) {
        em.merge(producto);
    }

    public void borrar(Producto producto) {
        em.remove(em.merge(producto));
    }

    public Producto consultar(Object id) {
        return em.find(Producto.class, id);
    }
    
    public List<Producto> obtenerTodos() {
        return em.createNamedQuery("Producto.findAll").getResultList();
    }
}
