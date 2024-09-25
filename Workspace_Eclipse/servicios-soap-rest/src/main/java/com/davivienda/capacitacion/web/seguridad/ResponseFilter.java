package com.davivienda.capacitacion.web.seguridad;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		
		String nuevoToken = (String)requestContext.getProperty("tokenNuevo");
		
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		
		/*responseContext.getHeaders().add("X-Frame-Options", "SAMEORIGIN" );
		responseContext.getHeaders().add("Content-Security-Policy", "frame-ancestors 'self'");
		responseContext.getHeaders().add("X-XSS-Protection", "1; mode=block");
		responseContext.getHeaders().add("X-Content-Type-Options", "nosniff");*/
		
		responseContext.getHeaders().add("jws", nuevoToken);
		
	}

}
