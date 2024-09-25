package com.davivienda.capacitacion.posrest.dto;

public class ProductoFacturaDtoPK {
	private String codigoProducto;
	
	private Integer numeroFactura;

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Integer numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
}
