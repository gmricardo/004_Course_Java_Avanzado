package com.davivienda.capacitacion.pos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.davivienda.capacitacion.pos.controller.dto.DatosFacturaDto;
import com.davivienda.capacitacion.pos.modelo.Cliente;
import com.davivienda.capacitacion.pos.modelo.ClientePK;
import com.davivienda.capacitacion.pos.modelo.Factura;
import com.davivienda.capacitacion.pos.modelo.ProductoFactura;
import com.davivienda.capacitacion.pos.modelo.ProductoFacturaPK;
import com.davivienda.capacitacion.pos.service.ClienteServicio;
import com.davivienda.capacitacion.pos.service.FacturaServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class FacturaController {
	
	@Autowired
	private FacturaServicio facturaSvc;
	
	@Autowired
	private ClienteServicio clienteSvc;
	
	@GetMapping("/facturas/administrar")
	public String administrar(Model model) {
		List<Factura> lstFacturas = facturaSvc.obtenerTodas();
		model.addAttribute("lstFacturas",lstFacturas);
		return "/facturas/admFacturas";
	}
	
	@GetMapping("/facturas/irNueva")
	public String irNueva(Model model, HttpSession session) {
		
		DatosFacturaDto datos = (DatosFacturaDto)session.getAttribute("datosCreacion");
		
		if (datos == null) {
			datos = new DatosFacturaDto();
			session.setAttribute("datosCreacion", datos);
		}
		
		List<Cliente> lstClientes = this.clienteSvc.obtenerTodos();
		
		model.addAttribute("lstClientes", lstClientes);
		model.addAttribute("datosCreacion", datos);
		
		return "/facturas/nuevaFactura";
	}
	
	/*@PostMapping("/facturas/nuevoItem")
	public String crear(ProductoFactura pf, RedirectAttributes ra,  HttpSession session) {
		System.out.println("------------------Nuevo Item -------------------");
		System.out.println("Codigo producto:   " + pf.getId().getCodigoProducto());
		System.out.println("Codigo cantidad:   " + pf.getCantidadProducto());
		System.out.println("Codigo valor unitario:   " + pf.getValorUnitario());
		
		Factura factura = (Factura)session.getAttribute("facturaEnCreacion");
		factura.getLstProductosFactura().add(pf);
		return "redirect:/facturas/irNueva";
		
	}*/
	
	@PostMapping("/facturas/crear")
	public String crear(DatosFacturaDto datos, RedirectAttributes ra, HttpSession session) {
		
		DatosFacturaDto datosSession = (DatosFacturaDto)session.getAttribute("datosCreacion");
		datosSession.getFactura().setNumero(datos.getFactura().getNumero());
		datosSession.getFactura().setFecha(datos.getFactura().getFecha());
		datosSession.getFactura().getCliente().getId().setTxtId(datos.getFactura().getCliente().getId().getTxtId());
		
		if(!datos.getProductoFactura().getId().getCodigoProducto().isEmpty()) {
			// Hicieron click en agregar producto
			datosSession.getFactura().getLstProductosFactura().add(datos.getProductoFactura());
			return "redirect:/facturas/irNueva";
		}
		
		if (!datos.getCodigoProductoEliminar().isEmpty()) {
			// hicieron clic en el si de eliminar producto
			
			for (ProductoFactura pfac : datosSession.getFactura().getLstProductosFactura()) {
				
				if (pfac.getId().getCodigoProducto().equals(datos.getCodigoProductoEliminar())) {
				
					datosSession.getFactura().getLstProductosFactura().remove(pfac);
					break;
				}
			}
			
			return "redirect:/facturas/irNueva";
		}
		
		if (!datos.getProductoFacturaEditar().getId().getCodigoProducto().isEmpty()) {
			// hicieron clic en el si de eliminar producto
			
			for (ProductoFactura pfac : datosSession.getFactura().getLstProductosFactura()) {
				
				if (pfac.getId().getCodigoProducto().equals(datos.getProductoFacturaEditar().getId().getCodigoProducto())) {
				
					pfac.setCantidadProducto(datos.getProductoFacturaEditar().getCantidadProducto());
					pfac.setValorUnitario(datos.getProductoFacturaEditar().getValorUnitario());
					break;
				}
			}
			
			return "redirect:/facturas/irNueva";
		}
		
		// Hicieron click en crear factura
		for(ProductoFactura pfac : datosSession.getFactura().getLstProductosFactura()) {
			pfac.getId().setNumeroFactura(datosSession.getFactura().getNumero());
		}
		
		String[] vctId = datos.getFactura().getCliente().getId().getTxtId().split(";");
		datosSession.getFactura().getCliente().getId().setTipoIdentificacion(vctId[0]);
		datosSession.getFactura().getCliente().getId().setNroIdentificacion(vctId[1]);
		
		
		facturaSvc.guardar(datosSession.getFactura());
		session.removeAttribute("datosCreacion");
		ra.addFlashAttribute("msjExito", "Factura creada satisfactoriamente!");
		
		return "redirect:/facturas/administrar";
		
	}
}
