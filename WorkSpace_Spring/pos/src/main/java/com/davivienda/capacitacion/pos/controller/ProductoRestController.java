package com.davivienda.capacitacion.pos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davivienda.capacitacion.pos.controller.dto.ProductoDto;
import com.davivienda.capacitacion.pos.modelo.Producto;
import com.davivienda.capacitacion.pos.service.ProductoServicio;

@RestController
public class ProductoRestController {
	
	@Autowired
	private ProductoServicio productoSvc;
	
	@GetMapping("/consultarProductos")
	public List<ProductoDto> consultarProductos () {
		List<ProductoDto> lstResultado = new ArrayList<ProductoDto>();
		List<Producto> lst = productoSvc.obtenerTodos();
		
		for (Producto p: lst) {
			ProductoDto pDto = new ProductoDto();
			pDto.setCodigo(p.getCodigo());
			pDto.setNombre(p.getNombre());
			lstResultado.add(pDto);
		}
		
		return lstResultado;
	}
}
