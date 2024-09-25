package com.davivienda.capacitacion.web.dtos;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class FacturaDto {
	
	private Integer numero;
	
	private ClienteDto cliente;
	
	private Date fecha;
	
	private List<ProductoFacturaDto> lstProductosFactura;

	@XmlElement(required=true)
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@XmlElement(required=true)
	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}


	@XmlElementWrapper(name="LstProductosFactura") 
    @XmlElement(name="ProductoFactura")
	public List<ProductoFacturaDto> getLstProductosFactura() {
		return lstProductosFactura;
	}

	@XmlElement(required=true)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setLstProductosFactura(List<ProductoFacturaDto> lstProductosFactura) {
		this.lstProductosFactura = lstProductosFactura;
	}
	
	

}
