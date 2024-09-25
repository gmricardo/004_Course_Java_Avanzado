package com.davivienda.capacitacion.web.seguridad;

import java.io.IOException;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.davivienda.capacitacion.web.rest.UtilAES;
import com.davivienda.capacitacion.web.services.IUsuarioSvc;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Provider
@PreMatching
public class SecurityFilter implements ContainerRequestFilter{
	
	private IUsuarioSvc usrSvc;
	
	public SecurityFilter() {
		this.usrSvc = this.obtRefManualServicioUsuario();
	}
	
	
	private IUsuarioSvc obtRefManualServicioUsuario() {
		
		System.out.println(" -------------- +++++++++++++++ ------------------..OBTENIENDO REFERENCIA MANUAL");
		InitialContext ic;
		try {
			ic = new InitialContext();
			return (IUsuarioSvc)ic.lookup("java:comp/env/UsuarioSvc");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String url = requestContext.getUriInfo().getPath().toString();
		
		if (!url.startsWith("login")) {
			
			System.out.println("-----------------------SE VALIDARÁ EL TOKEN ------------------------------");
			
			String token = requestContext.getHeaderString("Authorization");
			
			if (token == null || token.trim().isEmpty()) {
				
				JsonObject json = Json.createObjectBuilder()
									.add("mensaje", "Credenciales son necesarias")
									.add("codigo", "ERR-001")
									.build();
				
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).build());
				
				return;
			}
			
			try {
				
				token = UtilAES.descifrarAES(token);
                
                Claims claims = Jwts.parser().setSigningKey("F365553F9D4F3DC96E1C8B0346A67259".getBytes())
                                                .parseClaimsJws(token)
                                                .getBody();
				
				System.out.println("Subject: " + claims.getSubject());
				System.out.println("Fecha Expiracion: " + claims.getExpiration());
				System.out.println("Perfiles: " + claims.get("perfiles"));
				
				String perfiles = (String)claims.get("perfiles");
				
				if (!this.validarAutorizacion(perfiles, url)) {
					
					JsonObject json = Json.createObjectBuilder()
							.add("mensaje", "Perfil no autorizado")
							.add("codigo", "ERR-002")
							.build();
		
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).build());
					return;
				}
				
				System.out.print("Token Anterior");
				System.out.print(token);
				
				Date fechaExpiracion = claims.getExpiration();
				
				Long ahora = System.currentTimeMillis();
				Long vencimiento = fechaExpiracion.getTime();
				Long diferencia = vencimiento - ahora;
				
				String tokenNuevo = token;
				
				/*if (diferencia < 5 * 60000) {
					tokenNuevo = Jwts.builder().setSubject(claims.getSubject())
							.signWith(SignatureAlgorithm.HS256, "F365553F9D4F3DC96E1C8B0346A67259".getBytes())
							.setIssuedAt(new Date(System.currentTimeMillis()))
							.setExpiration(new Date(System.currentTimeMillis() + 15 * 60000))
							.claim("perfiles", (String)claims.get("perfiles"))
							.compact();
				} else {
					tokenNuevo = token;
				}*/
				
				
				
				requestContext.setProperty("tokenNuevo", tokenNuevo);
				
				System.out.print("Token Nuevo");
				System.out.print(tokenNuevo);
				
				
				
			
			} catch (SignatureException e) {
				
				JsonObject json = Json.createObjectBuilder().add("mensaje", "Credenciales invalidas").build();
				
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).build());
				
			
			} catch (ExpiredJwtException e) {
				
				/*Long ahora = System.currentTimeMillis();
				Long vencimiento = e.getClaims().getExpiration().getTime();
				Long diferencia = ahora - vencimiento;
				
				String tokenNuevo;
				
				if (diferencia < 1 * 60000) {
					tokenNuevo = Jwts.builder().setSubject(e.getClaims().getSubject())
							.signWith(SignatureAlgorithm.HS256, "F365553F9D4F3DC96E1C8B0346A67259".getBytes())
							.setIssuedAt(new Date(System.currentTimeMillis()))
							.setExpiration(new Date(System.currentTimeMillis() + 1 * 60000))
							.claim("perfiles", (String)e.getClaims().get("perfiles"))
							.compact();
					
					requestContext.setProperty("tokenNuevo", tokenNuevo);
					
				} else { */
				
					JsonObject json = Json.createObjectBuilder().add("mensaje", "Token expirado").build();
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).build());
				//}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				System.out.println("Error real: " + e.getClass().getName());
				
				JsonObject json = Json.createObjectBuilder().add("mensaje", "Error no esperado").build();
				
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).build());
				
			}
			
		}
		
		
	}
	
	private boolean validarAutorizacion(String perfiles, String url) {
		
		return this.usrSvc.validarAutorizacion(perfiles, url);
		
	}

}
