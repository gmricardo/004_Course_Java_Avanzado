package com.davivienda.capacitacion.posrest.modelo;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCTOS_FACTURA")
public class ProductoFactura {
	
	@Id
	private ProductoFacturaPK id;
	
	@Column(name="CANTIDAD_PRODUCTOS")
	private Integer cantidadProducto;
	
	@Column(name="VALOR_UNITARIO")
	private Double valorUnitario;
	
	@ManyToOne
	@JoinColumn(name="NUMERO_FACTURA", insertable = false, updatable = false)
	private Factura factura;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_PRODUCTO", insertable = false, updatable = false)
	private Producto producto;

	public ProductoFacturaPK getId() {
		return id;
	}

	public void setId(ProductoFacturaPK id) {
		this.id = id;
	}

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

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoFactura other = (ProductoFactura) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	/*@Override
	public boolean equals(Object other) {
		if(other==null || !(other instanceof ProductoFactura)) {
			return false;
		}
		
		ProductoFactura otro = (ProductoFactura) other;
		return this.id.equals(otro.id);
	}*/
}
