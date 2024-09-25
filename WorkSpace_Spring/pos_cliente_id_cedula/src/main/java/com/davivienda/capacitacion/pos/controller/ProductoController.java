package com.davivienda.capacitacion.pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.davivienda.capacitacion.pos.modelo.Producto;
import com.davivienda.capacitacion.pos.service.ProductoServicio;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoServicio productoSvc;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Producto> lstProductos = productoSvc.obtenerTodos();
		model.addAttribute("prods",lstProductos);
		return "index";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Producto p = new Producto();
		model.addAttribute("producto",p);
		return "nuevo";
	}
	
	@PostMapping("/crear")
	public String crear(Producto p, RedirectAttributes ra) {
		productoSvc.guardar(p);
		ra.addFlashAttribute("msjExito", "Producto creado satisfactoriamente!");
		return "redirect:/";
	}
	
	@GetMapping("/{codigo}/{valor}/irEditar")
	public String irEditar(@PathVariable String codigo, @PathVariable String valor, Model model) {
		Producto p = productoSvc.findById(codigo);
		System.out.println(valor);
		model.addAttribute("producto",p);
		return "editar";
	}
	
	@PostMapping("/editar")
	public String editar(Producto p, RedirectAttributes ra) {
		productoSvc.editar(p);
		ra.addFlashAttribute("msjExito", "Producto actualizado correctamente!");
		return "redirect:/";
	}
	
	@GetMapping("/{codigo}/irEliminar")
	public String irEliminar(@PathVariable String codigo, Model model) {
		Producto p = productoSvc.findById(codigo);
		model.addAttribute("producto",p);
		return "eliminar";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(Producto p, RedirectAttributes ra) {
		System.out.println("El producto a eliminar es: "+ p.getCodigo());
		productoSvc.borrar(p);
		// Mensaje que aparece en la pantalla del redirect
		ra.addFlashAttribute("msjExito", "Producto eliminado Satisfactoriamente");
		// Redirecciona a la ruta raiz (indice)
		return "redirect:/";
	}
}
