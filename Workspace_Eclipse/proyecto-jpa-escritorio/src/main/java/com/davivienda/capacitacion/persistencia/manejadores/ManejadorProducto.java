package com.davivienda.capacitacion.persistencia.manejadores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.davivienda.capacitacion.persistencia.entities.Producto;
import com.davivienda.capacitacion.persistencia.manejadores.util.PersistenciaSgn;

public class ManejadorProducto {
	
	private EntityManager em = PersistenciaSgn.obtenerInstancia().getEm();
	
	public ManejadorProducto() {
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
    	Query q = em.createNamedQuery("Producto.findAll", Producto.class);
        return em.createNamedQuery("Producto.findAll").getResultList();
    }
}
