package com.davivienda.capacitacion.web.services;

import javax.ejb.Local;

@Local
public interface IUsuarioSvc {
	
	public boolean validarUsuario (String usuario, String clave);
	
	public boolean validarAutorizacion(String perfiles, String url);
	
	public String obtenerPerfiles (String usuario);
	
}
