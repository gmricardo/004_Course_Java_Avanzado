package com.davivienda.capacitacion.web;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.davivienda.capacitacion.persistencia.entities.Producto;
import com.davivienda.capacitacion.persistencia.manejadores.ManejadorProducto;

@ManagedBean
@ViewScoped
public class ProductosMB {
	@EJB
	private ManejadorProducto svcProdDao;
	
	private List<Producto> lstProductos;
	
	private Producto nuevoProducto;
	private Producto productoEditar;
	
	@PostConstruct
	public void inicializarBean() {
		lstProductos = svcProdDao.obtenerTodos();
	}
	
	public void prepararCreacion() {
		this.nuevoProducto = new Producto();
	}
	
	public void prepararEdicion() {
		System.out.println(this.productoEditar.getNombre());
	}
	
	public void guardar() {
		this.nuevoProducto.setUsuarioCrea("Ricardo");
		this.nuevoProducto.setFechaCreacion(new Date());
		svcProdDao.crear(this.nuevoProducto);
		this.lstProductos = svcProdDao.obtenerTodos();
	}
	
	public List<Producto> getLstProductos() {
		return lstProductos;
	}

	public void setLstProductos(List<Producto> lstProductos) {
		this.lstProductos = lstProductos;
	}

	public Producto getNuevoProducto() {
		return nuevoProducto;
	}

	public void setNuevoProducto(Producto nuevoProducto) {
		this.nuevoProducto = nuevoProducto;
	}

	public Producto getProductoEditar() {
		return productoEditar;
	}

	public void setProductoEditar(Producto productoEditar) {
		this.productoEditar = productoEditar;
	}

	
}
