package com.davivienda.capacitacion.pos.modelo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	
	@DateTimeFormat(iso = ISO.DATE)
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
