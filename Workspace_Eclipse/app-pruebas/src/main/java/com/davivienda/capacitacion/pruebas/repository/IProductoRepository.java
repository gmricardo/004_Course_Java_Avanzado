package com.davivienda.capacitacion.pruebas.repository;

import java.util.List;

import javax.ejb.Local;

import com.davivienda.capacitacion.pruebas.entities.Producto;

/**
 * 
 */
@Local
public interface IProductoRepository {

	/**
	 * 
	 * @return
	 */
	public List<Producto> obtenerTodos();
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public Producto obtenerPorCodigo(String codigo);
	
}
