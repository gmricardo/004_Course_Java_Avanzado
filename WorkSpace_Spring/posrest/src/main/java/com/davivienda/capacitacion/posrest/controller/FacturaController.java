package com.davivienda.capacitacion.posrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davivienda.capacitacion.posrest.dto.FacturaDto;
import com.davivienda.capacitacion.posrest.service.FacturaService;

@RestController
@RequestMapping("/invoice")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaSvc;
	
	@GetMapping("/find/{numero}")
	public FacturaDto findByPrimaryKey(@PathVariable("numero") Integer numero) {
		
		FacturaDto f = facturaSvc.findByPrimaryKey(numero);
		
		return f;
		
	}
	
	@PostMapping("/update")
	public void update(@RequestBody FacturaDto fact) {
		System.out.println("En el update de factura con POST................");
		this.facturaSvc.modifyFactura(fact);
	}
	
}
