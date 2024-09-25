package com.davivienda.capacitacion.posrest.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ManejadorError extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = {PosRestError.class})
	protected ResponseEntity<Object> manejarExcepcion (PosRestError ex, WebRequest wr){
		System.out.println("En el manejador..." + ex.getMessage());
		HttpHeaders hd= new HttpHeaders();
		hd.add("Encab", "valor encab");
		return handleExceptionInternal (ex, ex.getMessage(), hd, ex.getHttpStatus(), wr);
	}

}
