package com.davivienda.capacitacion.web.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<Throwable> {
	
	@Override
	public Response toResponse(Throwable exception) {
		
		if (exception instanceof PosError) {
			
			PosError posError = (PosError)exception;
			
			PosErrorDto posErrorDto = new PosErrorDto();
			
			posErrorDto.setCodigo(posError.getCodigo());
			posErrorDto.setMensaje(posError.getMessage());
			
			return Response.status(Response.Status.BAD_REQUEST).entity(posErrorDto).build();
			
		}
		
		return Response.status(Response.Status.NOT_ACCEPTABLE).build();
	}

}
