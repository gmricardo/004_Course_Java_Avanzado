package com.davivienda.capacitacion.pruebas.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.davivienda.capacitacion.pruebas.entities.Producto;

@Stateless
public class ProductoRepository implements IProductoRepository{
	
	@PersistenceContext(unitName = "UPCapacitacion")
	private EntityManager em;

	@Override
	public List<Producto> obtenerTodos() {
		
		Query query = this.em.createQuery("SELECT p FROM Producto p ORDER BY p.nombre", Producto.class);
		return query.getResultList();
		
	}

	@Override
	public Producto obtenerPorCodigo(String codigo) {
		Producto p = this.em.find(Producto.class, codigo);
		return p;
	}
	
	
}
