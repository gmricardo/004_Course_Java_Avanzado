package com.davivienda.capacitacion.pruebas.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.davivienda.capacitacion.pruebas.dtos.ProductoDto;
import com.davivienda.capacitacion.pruebas.entities.Producto;
import com.davivienda.capacitacion.pruebas.repository.IProductoRepository;

class ProductoServiceTest {

	@Test
	void testObtenerTodosSinDatos() {
		
		System.out.println("testObtenerTodosSinDatos---");
		
		IProductoRepository prodRep = Mockito.mock(IProductoRepository.class);
		ProductoService prodSvc = new ProductoService();
		prodSvc.setProdRep(prodRep);
		
		List<Producto> lstDatos = new ArrayList<Producto>();
		
		Mockito.when(prodRep.obtenerTodos()).thenReturn(lstDatos);
		
		
		List<ProductoDto> lstDatoObt = prodSvc.obtenerTodos();
		
		assertEquals(0, lstDatoObt.size());
		
	}
	
	@Test
	void testObtenerTodos() {
		
		System.out.println("testObtenerTodos---");
		
		IProductoRepository prodRep = Mockito.mock(IProductoRepository.class);
		ProductoService prodSvc = new ProductoService();
		prodSvc.setProdRep(prodRep);
		
		//Inicio - Datos simulados
		List<Producto> lstDatos = new ArrayList<Producto>();
		Producto p = new Producto();
		p.setCodigo("P01");
		p.setNombre("ALUMINIO");
		p.setValor(1000d);
		lstDatos.add(p);
		
		p = new Producto();
		p.setCodigo("P02");
		p.setNombre("POTASIO");
		p.setValor(2000d);
		lstDatos.add(p);
		// fin datos simulados
		Mockito.when(prodRep.obtenerTodos()).thenReturn(lstDatos);
		
		// inicio - datos esperados
		List<ProductoDto> lstDatosEsperados = new ArrayList<ProductoDto>();
		ProductoDto pDto = new ProductoDto();
		pDto.setCodigo("P01");
		pDto.setNombre("ALUMINIO");
		pDto.setValor(1000d);
		lstDatosEsperados.add(pDto);
		
		pDto = new ProductoDto();
		pDto.setCodigo("P02");
		pDto.setNombre("POTASIO");
		pDto.setValor(2000d);
		lstDatosEsperados.add(pDto);
		// fin - datos esperados

		
		List<ProductoDto> lstDatoObt = prodSvc.obtenerTodos();
		
		assertEquals(lstDatosEsperados.size(), lstDatoObt.size());
		
		for (int i= 0; i <lstDatosEsperados.size(); i++) {
			ProductoDto pEsp = lstDatosEsperados.get(i);
			ProductoDto pObt = lstDatoObt.get(i);
			
			assertEquals(pEsp.getCodigo(), pObt.getCodigo());
			assertEquals(pEsp.getNombre(), pObt.getNombre());
			assertEquals(pEsp.getValor(), pObt.getValor());
		}
		
	}
	
	@Test
	@DisplayName("Prueba del metodo ObtenerPorCodigo")
	void testObtenerPorCodigo() {
		
		System.out.println("testObtenerPorCodigo---");
		
		IProductoRepository prodRep = Mockito.mock(IProductoRepository.class);
		ProductoService prodSvc = new ProductoService();
		prodSvc.setProdRep(prodRep);
		
		//Dato simulados
		Producto p1 = new Producto();
		p1.setCodigo("X01");
		p1.setNombre("Alfalfa");
		p1.setValor(300d);
		
		/*Producto p2 = new Producto();
		p2.setCodigo("X02");
		p2.setNombre("Perejil");
		p2.setValor(150d);*/
		
		Mockito.when(prodRep.obtenerPorCodigo("X01")).thenReturn(p1);
		//Mockito.when(prodRep.obtenerPorCodigo("X02")).thenReturn(p2);
		//fin datos simulados
		
		//Datos Esperados
		ProductoDto pDtoEsp = new ProductoDto();
		pDtoEsp.setCodigo("X01");
		pDtoEsp.setNombre("Alfalfa");
		pDtoEsp.setValor(300d);
		//Fin Datos Esperados
		
		//Prueba:
		ProductoDto pDtoRecibido = prodSvc.obtenerPorCodigo("X01");
		
		//Validaciones
		assertNotNull(pDtoRecibido);
		assertEquals(pDtoEsp.getCodigo(), pDtoRecibido.getCodigo());
		assertEquals(pDtoEsp.getNombre(), pDtoRecibido.getNombre(), "Los nombres deberían ser iguales");
		assertEquals(pDtoEsp.getValor(), pDtoRecibido.getValor());
		
	}
	
	@BeforeEach
	void inicializar() {
		System.out.println("Inicializa---");
	}
	
	@AfterEach
	void finalizar() {
		System.out.println("Finaliza---");
	}
	

}
