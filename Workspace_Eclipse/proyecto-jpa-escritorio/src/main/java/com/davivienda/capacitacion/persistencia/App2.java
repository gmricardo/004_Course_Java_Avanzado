package com.davivienda.capacitacion.persistencia;

import com.davivienda.capacitacion.persistencia.entities.Producto;
import com.davivienda.capacitacion.persistencia.manejadores.ManejadorProducto;
import com.davivienda.capacitacion.persistencia.manejadores.util.PersistenciaSgn;

public class App2 {

	public static void main(String[] args) {
		
		ManejadorProducto svcProductoDao = new ManejadorProducto();
		PersistenciaSgn persistencia = PersistenciaSgn.obtenerInstancia();
		/*Producto p = m.consultar("P01");
		System.out.println("Nombre: " + p.getNombre());*/
		
		Producto p = svcProductoDao.consultar("P03");
		p.setValor(18000.00);
		persistencia.iniciarTx();
		svcProductoDao.actualizar(p);
		persistencia.commitTx();
		
		persistencia.cerrarPersistencia();
		
		
	}

}
