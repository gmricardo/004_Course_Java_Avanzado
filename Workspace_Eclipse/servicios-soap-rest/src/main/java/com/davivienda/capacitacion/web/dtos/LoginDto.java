package com.davivienda.capacitacion.web.dtos;

public class LoginDto {
	
	private String usuario;
	
	private String clave;
	
	private String fraseAES;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getFraseAES() {
		return fraseAES;
	}

	public void setFraseAES(String fraseAES) {
		this.fraseAES = fraseAES;
	}
	
	

}
