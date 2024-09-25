package com.davivienda.capacitacion.web.services;

import javax.ejb.Stateless;

import com.davivienda.capacitacion.web.dtos.FacturaDto;
import com.davivienda.capacitacion.web.dtos.ProductoDto;

@Stateless
public class ServicioPrueba {

	public void actualizarFactura(FacturaDto factura) {
		System.out.println("Numero recibido: "+factura.getNumero());
		System.out.println("Fecha recibida: "+factura.getFecha());
		System.out.println("Nombre cliente: "+factura.getCliente());
		
	}
	
	public ProductoDto consultarProducto (String codigo) {
		ProductoDto p = new ProductoDto();
		p.setCodigo(codigo);
		p.setNombre("Mandarinas");
		p.setValor(2500d);
		
		if(codigo.equals("PXX")){
			int x = 1/0;
		}
		return p;
	}
	
	public boolean validarUsuario (String usuario, String clave) {
		if(usuario.equals("rigarzon") && clave.equals("123")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
