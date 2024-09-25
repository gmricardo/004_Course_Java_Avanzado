package com.davivienda.capacitacion.clients.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.davivienda.capacitacion.clients.modelo.Cliente;
import com.davivienda.capacitacion.clients.service.ClienteServicio;



@Controller
public class ClienteController {
	@Autowired
	private ClienteServicio clienteSvc;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Cliente> lstClients = clienteSvc.obtenerTodos();
		model.addAttribute("clients",lstClients);
		return "index";
	}
}
