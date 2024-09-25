package com.davivienda.capacitacion.web.dtos.soap;

public class HeaderResponseDto {
	
	private String canal;
	
	private String usuario;
	
	private String caracterAceptacion;
	
	private String token;
	
	private String codigoError;
	
	private String descripcionError;

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCaracterAceptacion() {
		return caracterAceptacion;
	}

	public void setCaracterAceptacion(String caracterAceptacion) {
		this.caracterAceptacion = caracterAceptacion;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
	
}
