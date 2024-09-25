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
	
	public List<ProductoDto> consultarTodos() {
		
		List<ProductoDto> lstProductos = new ArrayList<ProductoDto>();
		
		ProductoDto p = new ProductoDto();
		
		p.setCodigo("P01");
		p.setNombre("Llavero");
		p.setValor(25000d);
		
		lstProductos.add(p);
		
		p = new ProductoDto();
		
		p.setCodigo("P02");
		p.setNombre("Alpargatas");
		p.setValor(5800d);
		
		lstProductos.add(p);
		
		p = new ProductoDto();
		
		p.setCodigo("P03");
		p.setNombre("Celular");
		p.setValor(260000d);
		
		lstProductos.add(p);
		
		return lstProductos;
	}
	
	public void crear(ProductoDto prod) {
		
		System.out.println("----------------------------------------------");
		System.out.println("Codigo: " + prod.getCodigo());
		System.out.println("Nombre: " + prod.getNombre());
		System.out.println("Valor: " + prod.getValor());
		System.out.println("----------------------------------------------");
		
		if (prod.getCodigo().equalsIgnoreCase("PXX")) {
			throw new PosError("ERR-001", "El codigo del producto no es correcto");
		}
		
	}
	
}
