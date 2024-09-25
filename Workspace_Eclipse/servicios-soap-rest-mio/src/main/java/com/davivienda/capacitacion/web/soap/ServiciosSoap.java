package com.davivienda.capacitacion.web.soap;

import java.security.SignatureException;
import java.util.Date;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.davivienda.capacitacion.web.dtos.FacturaDto;
import com.davivienda.capacitacion.web.dtos.ProductoDto;
import com.davivienda.capacitacion.web.dtos.soap.DataResponseDto;
import com.davivienda.capacitacion.web.dtos.soap.HeaderResponseDto;
import com.davivienda.capacitacion.web.dtos.soap.RequestDto;
import com.davivienda.capacitacion.web.dtos.soap.ResponseDto;
import com.davivienda.capacitacion.web.error.PosError;
import com.davivienda.capacitacion.web.services.ServicioPrueba;

@WebService(serviceName="ServiciosSoap")
public class ServiciosSoap {
	
	@EJB
	private ServicioPrueba svcPrueba;
	
	@WebMethod(operationName = "saludar")
	@WebResult(name="Respuesta")
	public String saludar(@WebParam(name="Solicitud") String nombre) {
		return "Hola "+nombre;
	}
	
	@WebMethod(operationName = "actualizarFactura")
	@WebResult(name="Response")
	public void actualizarFactura(@WebParam(name="Solicitud") FacturaDto factura) {
		svcPrueba.actualizarFactura(factura);
		
	}
	
	@WebMethod(operationName = "consultarProducto")

	@WebResult(name = "Response")

	public ResponseDto consultarProducto(@WebParam(name = "Request") RequestDto request) {

	    ResponseDto rta = new ResponseDto();

	    rta.setHeader(new HeaderResponseDto());

	    rta.setData(new DataResponseDto());

	    try {

	        String tokenVigente = this.validarUsuario(request.getHeader().getToken()

	                , request.getHeader().getUsuario()

	                , request.getHeader().getClave());

	        ProductoDto p = this.svcPrueba.consultarProducto(request.getData().getCodigoProducto());

	        rta.getData().setProducto(p);

	        rta.getHeader().setCanal(request.getHeader().getCanal());

	        rta.getHeader().setUsuario(request.getHeader().getUsuario());

	        rta.getHeader().setCaracterAceptacion("B");

	        rta.getHeader().setToken(tokenVigente);

	    } catch (PosError e) {

	        rta.getHeader().setCanal(request.getHeader().getCanal());

	        rta.getHeader().setUsuario(request.getHeader().getUsuario());

	        rta.getHeader().setCaracterAceptacion("M");

	        rta.getHeader().setCodigoError(e.getCodigo());

	        rta.getHeader().setDescripcionError(e.getMessage());

	    } catch (Exception e) {

	        rta.getHeader().setCanal(request.getHeader().getCanal());

	        rta.getHeader().setUsuario(request.getHeader().getUsuario());

	        rta.getHeader().setCaracterAceptacion("M");

	        rta.getHeader().setCodigoError("POS-EG");

	        rta.getHeader().setDescripcionError(e.getMessage());

	    }

	    return rta;

	}

	/**
	 * 
	 * valida el usuario con los datos recibidos
	 * en el request
	 * 
	 * @param token
	 * 
	 * @param usuario
	 * 
	 * @param clave
	 * 
	 * @return
	 * 
	 */

	public String validarUsuario(String token, String usuario, String clave) {

	    if (token == null || token.trim().isEmpty()) {

	        if (this.svcPrueba.validarUsuario(usuario, clave)) {

	            return generarToken(usuario);

	        } else {

	            throw new PosError("POS-NA", "No Autorizado");

	        }

	    }

	    try {

	        Claims claims = Jwts.parser().setSigningKey("F365553F9D4F3DC96E1C8B0346A67259".getBytes())

	                .parseClaimsJws(token)

	                .getBody();

	        System.out.println("Subject: " + claims.getSubject());

	        System.out.println("Fecha Expiracion: " + claims.getExpiration());

	        return token;

	    } catch (SignatureException e) {

	        throw new PosError("POS-NA", "No Autorizado");

	    } catch (ExpiredJwtException e) {

	        if (this.svcPrueba.validarUsuario(usuario, clave)) {

	            return generarToken(usuario);

	        } else {

	            throw new PosError("POS-NA", "No Autorizado");

	        }

	    } catch (Exception e) {

	        throw new PosError("POS-NA", "No Autorizado");

	    }

	}

	private String generarToken(String usuario) {

	    String jwt = Jwts.builder().setSubject(usuario)

	            .signWith(SignatureAlgorithm.HS256, "F365553F9D4F3DC96E1C8B0346A67259".getBytes())

	            .setIssuedAt(new Date(System.currentTimeMillis()))

	            .setExpiration(new Date(System.currentTimeMillis() + 2 * 60000))

	            .compact();

	    return jwt;

	}
	
}
