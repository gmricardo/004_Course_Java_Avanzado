package com.davivienda.capacitacion.web;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.davivienda.capacitacion.persistencia.entities.Producto;
import com.davivienda.capacitacion.persistencia.manejadores.ManejadorProducto;

@ManagedBean
@ViewScoped
public class IndexMB {
	
	@EJB
	private ManejadorProducto svcProductoDao;
	
	private Producto producto;
	
	public IndexMB() {
		producto = new Producto();
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void guardar() {
		this.producto.setUsuarioCrea("Ricardo");
		this.producto.setFechaCreacion(new Date());
		svcProductoDao.crear(this.producto);
	}
	
}
