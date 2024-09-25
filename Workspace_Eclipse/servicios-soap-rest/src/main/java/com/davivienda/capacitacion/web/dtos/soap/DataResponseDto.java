package com.davivienda.capacitacion.web.dtos.soap;

import com.davivienda.capacitacion.web.dtos.ProductoDto;

public class DataResponseDto {
	
	private ProductoDto producto;

	public ProductoDto getProducto() {
		return producto;
	}

	public void setProducto(ProductoDto producto) {
		this.producto = producto;
	}

}
