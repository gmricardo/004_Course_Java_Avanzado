package com.davivienda.capacitacion.web.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.davivienda.capacitacion.web.dtos.ProductoDto;
import com.davivienda.capacitacion.web.services.ProductoSvc;

@Stateless
@Path("/producto")
public class ProductoRest {

	@EJB
	private ProductoSvc prodSvc;
	
	@GET
	@Path("/consultar")
	@Produces({ MediaType.APPLICATION_JSON})
	public ProductoDto consulta(@QueryParam("codigo") String codigo) {
		
		ProductoDto resultado = prodSvc.consultar(codigo);
		
		return resultado;
	}
	
	@GET
	@Path("/consultar2")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response consulta2(@QueryParam("codigo") String codigo) {
		
		ProductoDto resultado = prodSvc.consultar(codigo);
		
		return Response.status(Response.Status.ACCEPTED).entity(resultado).build();
	}
	
	@GET
	@Path("/consultarTodos")
	@Produces({ MediaType.APPLICATION_JSON})
	public List<ProductoDto> consultaTodos() {
		
		List<ProductoDto> resultado = prodSvc.consultarTodos();
		
		return resultado;
	}
	
	@GET
	@Path("/consultarTodos2")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response consultaTodos2() {
		
		List<ProductoDto> resultado = prodSvc.consultarTodos();
		
		return Response.status(Response.Status.ACCEPTED).entity(resultado).build();
	}
	
	@POST
	@Path("/crear")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response crear(ProductoDto producto) {
		
		this.prodSvc.crear(producto);
		
		return Response.status(Response.Status.ACCEPTED).build();
	}
	
}
