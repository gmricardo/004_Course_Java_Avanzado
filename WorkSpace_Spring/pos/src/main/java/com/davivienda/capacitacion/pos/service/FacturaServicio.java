package com.davivienda.capacitacion.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davivienda.capacitacion.pos.modelo.Factura;
import com.davivienda.capacitacion.pos.repository.FacturaRepository;

@Service
public class FacturaServicio {
	
	@Autowired
	private FacturaRepository facturaRep;
	
	public List<Factura> obtenerTodas(){
		return facturaRep.findAll();
	}

	public void guardar(Factura f) {
		
		facturaRep.save(f);
		
	}

}
