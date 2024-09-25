package com.davivienda.capacitacion.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.davivienda.capacitacion.web.dtos.ClienteDto;
import com.davivienda.capacitacion.web.dtos.ClienteDtoPK;

@Stateless
@Path("/cliente")
public class ClienteRest {
	@GET
	@Path("/consultar")
	@Produces({ MediaType.APPLICATION_JSON})
	public ClienteDto consulta(@QueryParam("tipoId") String tipoId, @QueryParam("nroId") String nroId) {
		
		ClienteDto resultado = new ClienteDto();
		
		resultado.setId(new ClienteDtoPK());
		
		resultado.getId().setNroIdentificacion(nroId);
		resultado.getId().setTipoIdentificacion(tipoId);
		resultado.setNombre("Juan Gabriel");
		
		return resultado;
	}
	
	@GET
	@Path("/consultarTodos")
	@Produces({ MediaType.APPLICATION_JSON})
	public List<ClienteDto> consultaTodos() {
		
		List<ClienteDto> lstClientes = new ArrayList<ClienteDto>();
		
		ClienteDto cl = new ClienteDto();
		cl.setId(new ClienteDtoPK());
		cl.getId().setNroIdentificacion("123");
		cl.getId().setTipoIdentificacion("CC");
		cl.setNombre("Juan Gabriel");
		
		lstClientes.add(cl);
		
		cl = new ClienteDto();
		cl.setId(new ClienteDtoPK());
		cl.getId().setNroIdentificacion("456");
		cl.getId().setTipoIdentificacion("CC");
		cl.setNombre("Ojo de Halcón");
		
		lstClientes.add(cl);
		
		cl = new ClienteDto();
		cl.setId(new ClienteDtoPK());
		cl.getId().setNroIdentificacion("789");
		cl.getId().setTipoIdentificacion("CC");
		cl.setNombre("Ricardo Garzón Medina");
		
		lstClientes.add(cl);
		
		return lstClientes;
	}
}
