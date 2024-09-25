package com.davivienda.capacitacion.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davivienda.capacitacion.pos.modelo.Cliente;
import com.davivienda.capacitacion.pos.repository.ClienteRepository;

@Service
public class ClienteServicio {
	
	@Autowired
	private ClienteRepository clienteRep;
	
	public List<Cliente> obtenerTodos() {
		return clienteRep.findAll();
	}
}
