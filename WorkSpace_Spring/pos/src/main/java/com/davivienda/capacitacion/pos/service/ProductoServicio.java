package com.davivienda.capacitacion.pos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davivienda.capacitacion.pos.modelo.Producto;
import com.davivienda.capacitacion.pos.repository.ProductoRepository;

@Service
public class ProductoServicio {
	@Autowired
	private ProductoRepository productoRep;
	
	public List<Producto> obtenerTodos(){
		return productoRep.findAll();
	}
	
	public void guardar (Producto p) {
		p.setFechaCreacion(LocalDate.now());
		p.setUsuarioCrea("RIGARZON");
		productoRep.save(p);
	}

	public Producto findById(String codigo) {
		Optional<Producto> op = productoRep.findById(codigo);
		if(!op.isEmpty()) {
			return op.get();
		}
		return null;
	}
	
	public void editar (Producto p) {
		productoRep.save(p);
	}
	
	public void borrar (Producto p) {
		productoRep.delete(p);
	}
}
