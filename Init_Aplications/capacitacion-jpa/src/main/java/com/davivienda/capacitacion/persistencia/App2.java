package com.davivienda.capacitacion.persistencia;

import com.davivienda.capacitacion.persistencia.eao.ProductoEAO;
import com.davivienda.capacitacion.persistencia.entities.Producto;

public class App2 {
	public static void main( String[] args )
    {
		ProductoEAO svcProductos = new ProductoEAO();
		
		Producto p = svcProductos.consultar("P01");
		
		System.out.println("_____________________");
	    System.out.println(p.getCodigo());
	    System.out.println(p.getNombre());
	    System.out.println(p.getValor());
	    System.out.println(p.getUsuarioCrea());
	    System.out.println(p.getFechaCreacion());
    }

}
