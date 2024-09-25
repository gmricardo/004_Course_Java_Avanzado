package com.davivienda.capacitacion.web.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.davivienda.capacitacion.web.dtos.LoginDto;
import com.davivienda.capacitacion.web.dtos.ProductoDto;
import com.davivienda.capacitacion.web.services.UsuarioSvc;

@Stateless
@Path("/login")
public class LoginRest {
	
	@EJB
	private UsuarioSvc usrSvc;
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	public Response crear(LoginDto login) {
		
		
		this.usrSvc.validarUsuario(login);
		return Response.status(Response.Status.ACCEPTED).build();
	}

}
