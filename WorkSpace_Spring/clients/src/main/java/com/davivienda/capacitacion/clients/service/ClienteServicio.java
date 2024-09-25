package com.davivienda.capacitacion.clients.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davivienda.capacitacion.clients.modelo.Cliente;
import com.davivienda.capacitacion.clients.repository.ClienteRepository;


@Service
public class ClienteServicio {
	@Autowired
	private ClienteRepository clienteRep;
	
	public List<Cliente> obtenerTodos(){
		return clienteRep.findAll();
	}
}
