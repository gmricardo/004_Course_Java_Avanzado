package com.davivienda.capacitacion.web.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.davivienda.capacitacion.web.entities.PermisoPerfil;
import com.davivienda.capacitacion.web.manejadores.ManejadorPermisoPerfil;

@Stateless
@EJB(beanInterface = IUsuarioSvc.class, beanName = "UsuarioSvc", name = "UsuarioSvc")
public class UsuarioSvc implements IUsuarioSvc{
	
	@EJB
	private ManejadorPermisoPerfil manPermisoPerfil;
	
	public boolean validarUsuario (String usuario, String clave) {
		
		if (usuario.equalsIgnoreCase("jestevez") && clave.equals("123")) {
			return Boolean.TRUE;
		}
		
		if (usuario.equalsIgnoreCase("william") && clave.equals("456")) {
			return Boolean.TRUE;
		}
		
		if (usuario.equalsIgnoreCase("ricardo") && clave.equals("123")) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	public boolean validarAutorizacion(String perfiles, String url) {
		
		System.out.println("-----------EN EL SERVCIO UsuarioSvc");
		
		String[] vct = perfiles.split(",");
 		for (String perfil: vct) {
 			
 			System.out.println("Validando en BD: perfil: " + perfil+", con la url: "+url);
 			
 			PermisoPerfil pp = this.manPermisoPerfil.consultarPorPerfilUrl(perfil, url);
 			
 			if (pp != null) {
 				
 				System.out.println("Este perfil SI tiene permiso para la URL");
 				return Boolean.TRUE;
 			}
 			
 		}
 		
 		System.out.println("Este perfil NO tiene permiso para la URL");
 		return Boolean.FALSE;
	}
	
	public String obtenerPerfiles (String usuario) {
		if (usuario.equalsIgnoreCase("jestevez")) {
			return "PF1,PF2";
		}
		if (usuario.equalsIgnoreCase("william")) {
			return "PF1";
		}
		return null;
	}

}
