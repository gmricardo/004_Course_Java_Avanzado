package com.davivienda.capacitacion.pos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.davivienda.capacitacion.pos.modelo.Cliente;
import com.davivienda.capacitacion.pos.modelo.Producto;
import com.davivienda.capacitacion.pos.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRep;
	
	@GetMapping("/clientes/administrar")
	public String administrarClientes(Model model) {
		List<Cliente> lstClientes = clienteRep.findAll();
		model.addAttribute("clientes",lstClientes);
		return "/client/adm";
	}
	
	@GetMapping("/{cedula}/clientes/irEliminar")
	public String irEliminar(@PathVariable String cedula, Model model) {
		Optional<Cliente> op = clienteRep.findById(cedula);
		Cliente c = null;
		if(!op.isEmpty()) {
			c = op.get();
		}
		model.addAttribute("cliente",c);
		return "/client/eliminar";
	}
	
	@PostMapping("/cliente/eliminar")
	public String eliminar(Cliente c, RedirectAttributes ra) {
		System.out.println("El cliente a eliminar es: "+ c.getCedula());
		clienteRep.delete(c);
		// Mensaje que aparece en la pantalla del redirect
		ra.addFlashAttribute("msjExito", "Cliente eliminado Satisfactoriamente");
		// Redirecciona a la ruta raiz (indice)
		return "redirect:/clientes/administrar";
	}
	
}
