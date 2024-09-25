package com.davivienda.capacitacion.web.dtos;

import javax.xml.bind.annotation.XmlElement;

public class ClienteDtoPK {
	
	private String nroIdentificacion;
	
	private String tipoIdentificacion;
	
	@XmlElement(required = true)
	public String getNroIdentificacion() {
		return nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	@XmlElement(required = true)
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

}
