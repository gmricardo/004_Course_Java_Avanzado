package com.davivienda.capacitacion.web.dtos;

import java.io.Serializable;

public class ProductoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3981786614293395870L;
	
	
	private String codigo;
	private String nombre;
	private Double valor;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	

}
