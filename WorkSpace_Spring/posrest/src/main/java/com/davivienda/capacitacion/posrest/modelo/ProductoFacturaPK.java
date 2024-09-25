package com.davivienda.capacitacion.posrest.modelo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

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

	@Override
	public int hashCode() {
		return Objects.hash(codigoProducto, numeroFactura);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoFacturaPK other = (ProductoFacturaPK) obj;
		return Objects.equals(codigoProducto, other.codigoProducto)
				&& Objects.equals(numeroFactura, other.numeroFactura);
	}

	
	
	
	
	/*@Override
	public boolean equals(Object other) {
		if(other == null || !(other instanceof ProductoFacturaPK) ) {
			return false;
		}
		
		ProductoFacturaPK otro = (ProductoFacturaPK) other;
		
		return this.codigoProducto.equals(otro.codigoProducto) && this.numeroFactura.equals(otro.getNumeroFactura());
	}*/
}
