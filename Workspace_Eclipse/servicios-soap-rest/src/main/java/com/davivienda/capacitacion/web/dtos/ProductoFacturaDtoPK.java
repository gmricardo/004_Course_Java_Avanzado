package com.davivienda.capacitacion.web.dtos;

import javax.xml.bind.annotation.XmlElement;

public class ProductoFacturaDtoPK {
	
	private String codigoProducto;
	
	private Integer numeroFactura;

	@XmlElement(required=true)
	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	@XmlElement(required=true)
	public Integer getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(Integer numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	
	

}
