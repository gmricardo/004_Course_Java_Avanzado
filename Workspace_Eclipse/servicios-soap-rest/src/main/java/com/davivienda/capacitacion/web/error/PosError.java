package com.davivienda.capacitacion.web.error;

public class PosError extends RuntimeException{
	
	private static final long serialVersionUID = 5366825641757204155L;
	
	
	private String codigo;
	
	public PosError (String codigo, String mensaje) {
		super(mensaje);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
