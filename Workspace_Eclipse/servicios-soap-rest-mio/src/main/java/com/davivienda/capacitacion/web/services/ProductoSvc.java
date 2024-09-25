package com.davivienda.capacitacion.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.davivienda.capacitacion.web.dtos.ProductoDto;
import com.davivienda.capacitacion.web.error.PosError;

@Stateless
public class ProductoSvc {
	
	public ProductoDto consultar(String codigo) {
		
		ProductoDto p = new ProductoDto();
		
		p.setCodigo(codigo);
		p.setNombre("Llavero");
		p.setValor(25000d);
		
		return p;
		
	}
	
	public List<ProductoDto> consultarTodos(){
		List<ProductoDto> lstProductos = new ArrayList<ProductoDto>();
		
		ProductoDto p = new ProductoDto();
		
		p.setCodigo("P01");
		p.setNombre("Llavero");
		p.setValor(25000d);
		
		lstProductos.add(p);
		
		p = new ProductoDto();
		
		p.setCodigo("P02");
		p.setNombre("Computador");
		p.setValor(3000000d);
		
		lstProductos.add(p);
		p = new ProductoDto();
		
		p.setCodigo("P03");
		p.setNombre("Teclado");
		p.setValor(280000d);
		lstProductos.add(p);
		
		return lstProductos;
	}
	
	public void crear(ProductoDto producto) {
		System.out.println("_______________ CREAR PRODUCTO _______________________");
		System.out.println("Codigo:  " + producto.getCodigo());
		System.out.println("Nombre:  " + producto.getNombre());
		System.out.println("Valor:  " + producto.getValor());
		
		if(producto.getCodigo().equals("PXX")) {
			throw new PosError("ERR-001", "El código de producto no existe o no es invalido");
		}
	}
}