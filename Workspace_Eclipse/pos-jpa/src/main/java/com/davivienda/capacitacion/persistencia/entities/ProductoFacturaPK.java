package com.davivienda.capacitacion.persistencia.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductoFacturaPK implements Serializable{

	private static final long serialVersionUID = 2768637004337724107L;

	@Column(name="CODIGO_PRODUCTO")
	private String codigoProducto;
	
	@Column(name="NUMERO_FACTURA")
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
