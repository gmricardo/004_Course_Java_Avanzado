package com.davivienda.capacitacion.posrest.error;

import org.springframework.http.HttpStatus;

public class PosRestError extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7017950979808837453L;
	
	private HttpStatus httpStatus;
	
	public PosRestError(String msj, HttpStatus httpStatus) {
		super(msj);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
