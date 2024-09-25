package com.davivienda.capacitacion.posrest.modelo;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;

@Embeddable
public class ClientePK implements Serializable{
	
	private static final long serialVersionUID = -1824697732283694150L;

	@Column(name="NRO_IDENTIFICACION")
	private String nroIdentificacion;
	
	@Column(name="TIPO_IDENTIFICACION")
	private String tipoIdentificacion;
	
	@Transient
	private String txtId;

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

	public String getTxtId() {
		if(this.txtId == null) {
			StringBuilder sb = new StringBuilder();
			sb.append(this.tipoIdentificacion);
			sb.append(";");
			sb.append(this.nroIdentificacion);
			this.txtId = sb.toString();
		}
		return txtId;
	}

	public void setTxtId(String txtId) {
		this.txtId = txtId;
	}

}
