package com.davivienda.capacitacion.pruebas.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.davivienda.capacitacion.pruebas.dtos.ProductoDto;
import com.davivienda.capacitacion.pruebas.services.IProductoService;

@Stateless
@Path("/servicios/producto")
public class ProductoController {
	
	@EJB
	private IProductoService prodSvc;
	
	@GET
	@Path("/consultarTodos")
	@Produces({MediaType.APPLICATION_JSON})
	public List<ProductoDto> obtenerTodos() {
		return prodSvc.obtenerTodos();
	}
	
	@GET
	@Path("/consultarPorCodigo")
	@Produces({MediaType.APPLICATION_JSON})
	public ProductoDto obtenerPorCodigo(@QueryParam("codigo") String codigo) {
		
		return prodSvc.obtenerPorCodigo(codigo);
	}

}
