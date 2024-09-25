package com.davivienda.capacitacion.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClientePK implements Serializable{
	
	private static final long serialVersionUID = -1824697732283694150L;

	@Column(name="NRO_IDENTIFICACION")
	private String nroIdentificacion;
	
	@Column(name="TIPO_IDENTIFICACION")
	private String tipoIdentificacion;

	public String getNroIdentificacion() {
		return nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	

}
