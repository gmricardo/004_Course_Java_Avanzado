package com.davivienda.capacitacion.pruebas.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.modelmapper.ModelMapper;

import com.davivienda.capacitacion.pruebas.dtos.ProductoDto;
import com.davivienda.capacitacion.pruebas.entities.Producto;
import com.davivienda.capacitacion.pruebas.repository.IProductoRepository;

@Stateless
public class ProductoService implements IProductoService{
	
	@EJB
	private IProductoRepository prodRep;
	
	/**
	 * Establece la propiedad prodRep, solo utilizado
	 * para pruebas unitarias
	 * @param prodRep
	 */
	public void setProdRep(IProductoRepository prodRep) {
		this.prodRep = prodRep;
	}

	@Override
	public List<ProductoDto> obtenerTodos() {
		
		List<ProductoDto> lstResultado = new ArrayList<ProductoDto>();
		List<Producto> lstProductos = this.prodRep.obtenerTodos();
		ModelMapper mm = new ModelMapper();
		
		for (Producto p : lstProductos) {
			ProductoDto pDto = mm.map(p, ProductoDto.class);
			lstResultado.add(pDto);
		}
		
		return lstResultado;
	}

	@Override
	public ProductoDto obtenerPorCodigo(String codigo) {

		ProductoDto resultado;
		Producto producto = this.prodRep.obtenerPorCodigo(codigo);
		ModelMapper mm = new ModelMapper();
		
		resultado = mm.map(producto, ProductoDto.class);
		
		return resultado;
		
	}
	
	
	
	
}
