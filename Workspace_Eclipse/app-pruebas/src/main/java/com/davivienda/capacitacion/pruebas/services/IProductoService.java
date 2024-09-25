package com.davivienda.capacitacion.pruebas.services;

import java.util.List;

import javax.ejb.Local;

import com.davivienda.capacitacion.pruebas.dtos.ProductoDto;

@Local
public interface IProductoService {
	
	public List<ProductoDto> obtenerTodos();
	
	public ProductoDto obtenerPorCodigo(String codigo);
	
}
