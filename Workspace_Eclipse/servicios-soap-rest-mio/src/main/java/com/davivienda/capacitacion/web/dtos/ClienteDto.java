package com.davivienda.capacitacion.web.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;



public class ClienteDto implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1444249652519760720L;
	
	private ClienteDtoPK id;
	
	private String celular;

	private String correoElectronico;


	private LocalDate fechaNacimiento;

	private String nombre;

	private Integer numeroHijos;


	@XmlElement(required = true)
	public ClienteDtoPK getId() {
		return id;
	}


	public void setId(ClienteDtoPK id) {
		this.id = id;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@XmlElement(required = true)
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getNumeroHijos() {
		return numeroHijos;
	}


	public void setNumeroHijos(Integer numeroHijos) {
		this.numeroHijos = numeroHijos;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
