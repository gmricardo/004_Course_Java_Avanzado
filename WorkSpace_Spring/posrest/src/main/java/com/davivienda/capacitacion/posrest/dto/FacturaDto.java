package com.davivienda.capacitacion.posrest.dto;

import java.time.LocalDate;
import java.util.List;

public class FacturaDto {
	
	private Integer numero;
	
	private ClienteDto cliente;
	
	private LocalDate fecha;
	
	private List<ProductoFacturaDto> lstProductosFactura;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<ProductoFacturaDto> getLstProductosFactura() {
		return lstProductosFactura;
	}

	public void setLstProductosFactura(List<ProductoFacturaDto> lstProductosFactura) {
		this.lstProductosFactura = lstProductosFactura;
	}

	
}
