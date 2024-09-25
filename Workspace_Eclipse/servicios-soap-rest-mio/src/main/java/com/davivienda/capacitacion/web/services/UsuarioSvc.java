package com.davivienda.capacitacion.web.services;

import com.davivienda.capacitacion.web.dtos.LoginDto;

public class UsuarioSvc {
	
	public void validarUsuario(LoginDto datos) {
		System.out.println("Usuario: "+ datos.getUsuario());
		System.out.println("Clave: "+ datos.getContraseña());
		
	}

}
