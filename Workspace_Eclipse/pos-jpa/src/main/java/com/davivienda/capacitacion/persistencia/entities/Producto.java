package com.davivienda.capacitacion.persistencia.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCTOS")
public class Producto {
	@Id
	private String codigo;
	
	private String  nombre;
	
	private Double valor;
	
	@Column(name="USUARIO_CREA")
	private String usuarioCrea;
	
	@Column(name="FECHA_CREACION")
	private LocalDate fechaCreacion;
	
	@OneToMany(mappedBy="producto")
	private List<ProductoFactura> lstProductosFactura;

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

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<ProductoFactura> getLstProductosFactura() {
		return lstProductosFactura;
	}

	public void setLstProductosFactura(List<ProductoFactura> lstProductosFactura) {
		this.lstProductosFactura = lstProductosFactura;
	}
	
}
