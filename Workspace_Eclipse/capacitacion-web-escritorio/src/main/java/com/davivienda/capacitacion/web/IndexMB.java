package com.davivienda.capacitacion.web;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.davivienda.capacitacion.persistencia.entities.Producto;
import com.davivienda.capacitacion.persistencia.manejadores.ManejadorProducto;
import com.davivienda.capacitacion.persistencia.manejadores.util.PersistenciaSgn;

@ManagedBean
@ViewScoped
public class IndexMB {
	/*private String datoIngresado;
	private String datoMostrado = "Hola desde el MB";
	
	public void mostrarDato() {
		System.out.println("------> Dato ingresdo desde la vista: "+datoIngresado);
	}
	
	public inicializar() {
		
	}

	public String getDatoIngresado() {
		return datoIngresado;
	}

	public void setDatoIngresado(String datoIngresado) {
		this.datoIngresado = datoIngresado;
	}

	public String getDatoMostrado() {
		return datoMostrado;
	}

	public void setDatoMostrado(String datoMostrado) {
		this.datoMostrado = datoMostrado;
	}
	 */
	
	private ManejadorProducto svcProductoDao = new ManejadorProducto();
	private PersistenciaSgn persistencia = PersistenciaSgn.obtenerInstancia();
	
	private Producto producto;
	
	public IndexMB() {
		//producto = svcProductoDao.consultar("P04");
		producto = new Producto();
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void guardar() {
		persistencia.iniciarTx();
		this.producto.setUsuarioCrea("Ricardo");
		this.producto.setFechaCreacion(new Date());
		svcProductoDao.crear(this.producto);
		persistencia.commitTx();
	}
	
}
