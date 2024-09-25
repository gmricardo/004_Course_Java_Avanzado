package com.davivienda.capacitacion.pos.controller.dto;

import java.util.ArrayList;

import com.davivienda.capacitacion.pos.modelo.Cliente;
import com.davivienda.capacitacion.pos.modelo.ClientePK;
import com.davivienda.capacitacion.pos.modelo.Factura;
import com.davivienda.capacitacion.pos.modelo.ProductoFactura;
import com.davivienda.capacitacion.pos.modelo.ProductoFacturaPK;

public class DatosFacturaDto {
	
	private Factura factura;
	
	private ProductoFactura productoFactura;
	
	private String codigoProductoEliminar;
	
	private ProductoFactura productoFacturaEditar;
	
	public DatosFacturaDto() {
		this.factura = new Factura();
		this.factura.setCliente(new Cliente());
		this.factura.getCliente().setId(new ClientePK());
		this.factura.setLstProductosFactura(new ArrayList<ProductoFactura>());
		
		this.productoFactura = new ProductoFactura();
		this.productoFactura.setId(new ProductoFacturaPK());
		
		this.productoFacturaEditar = new ProductoFactura();
		this.productoFacturaEditar.setId(new ProductoFacturaPK());
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public ProductoFactura getProductoFactura() {
		return productoFactura;
	}

	public void setProductoFactura(ProductoFactura productoFactura) {
		this.productoFactura = productoFactura;
	}

	public String getCodigoProductoEliminar() {
		return codigoProductoEliminar;
	}

	public void setCodigoProductoEliminar(String codigoProductoEliminar) {
		this.codigoProductoEliminar = codigoProductoEliminar;
	}

	public ProductoFactura getProductoFacturaEditar() {
		return productoFacturaEditar;
	}

	public void setProductoFacturaEditar(ProductoFactura productoFacturaEditar) {
		this.productoFacturaEditar = productoFacturaEditar;
	}

	
	
	

}
