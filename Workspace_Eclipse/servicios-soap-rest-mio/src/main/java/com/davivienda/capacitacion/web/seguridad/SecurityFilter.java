package com.davivienda.capacitacion.web.seguridad;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class SecurityFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException{
		System.out.println("____________________Ejecuntando Filtro________________");
		System.out.println("__________________ "+ requestContext.getUriInfo().getPath().toString());
	}
}
