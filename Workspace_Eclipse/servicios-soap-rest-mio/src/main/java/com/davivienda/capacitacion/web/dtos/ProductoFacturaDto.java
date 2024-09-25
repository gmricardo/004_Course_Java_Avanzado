package com.davivienda.capacitacion.web.dtos;

import javax.xml.bind.annotation.XmlElement;

public class ProductoFacturaDto {

	
	private ProductoFacturaDtoPK id;
	
	private Integer cantidadProducto;
	
	private Double valorUnitario;
	
	private ProductoDto producto;

	public ProductoFacturaDtoPK getId() {
		return id;
	}

	public void setId(ProductoFacturaDtoPK id) {
		this.id = id;
	}

	@XmlElement(required = true)
	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public ProductoDto getProducto() {
		return producto;
	}

	public void setProducto(ProductoDto producto) {
		this.producto = producto;
	}
	
	
	
}
