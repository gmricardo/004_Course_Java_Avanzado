package com.davivienda.capacitacion.persistencia.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="FACTURAS")
public class Factura {
	@Id
	private Integer numero;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="NRO_IDENTIFICACION"),
		@JoinColumn(name="TIPO_IDENTIFICACION")
	})
	private Cliente cliente;
	
	private LocalDate fecha;
	
	@OneToMany(mappedBy="factura", cascade = CascadeType.ALL)
	private List<ProductoFactura> lstProductosFactura;
	
	public List<ProductoFactura> getLstProductosFactura() {
		return lstProductosFactura;
	}

	public void setLstProductosFactura(List<ProductoFactura> lstProductosFactura) {
		this.lstProductosFactura = lstProductosFactura;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
}
