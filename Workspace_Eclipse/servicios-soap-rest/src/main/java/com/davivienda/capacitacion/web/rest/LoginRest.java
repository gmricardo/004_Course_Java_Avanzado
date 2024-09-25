package com.davivienda.capacitacion.web.rest;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import javax.crypto.Cipher;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

import com.davivienda.capacitacion.web.dtos.LlaveDto;
import com.davivienda.capacitacion.web.dtos.LoginDto;
import com.davivienda.capacitacion.web.error.PosError;
import com.davivienda.capacitacion.web.services.IUsuarioSvc;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Stateless
@Path("/login")
public class LoginRest {
	
	@EJB
	private IUsuarioSvc usrSvc;
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON})
	public Response crear(LoginDto login) {
		
		return this.validarUsuario(login);
	}
	
	private Response validarUsuario(LoginDto datos) {
		
		System.out.println("Usuario: " + datos.getUsuario());
		System.out.println("Clave: " + datos.getClave());
		
		if (usrSvc.validarUsuario(datos.getUsuario(), datos.getClave())) {
			
			return generarToken(datos.getUsuario());
		}
		
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
	
	private Response generarToken(String usuario) {

        String perfiles = this.usrSvc.obtenerPerfiles(usuario);
        
        String jwt = Jwts.builder().setSubject(usuario)
                                .signWith(SignatureAlgorithm.HS256, "F365553F9D4F3DC96E1C8B0346A67259".getBytes())
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60000))
                                .claim("perfiles", perfiles)
                                .compact();
        // Crea un objeto JSON con el token y la clave AES
        String jwtCif = UtilAES.cifrarAES(jwt);
        JsonObject json = Json.createObjectBuilder()
                .add("JWT", jwtCif)
                .build();
        
        return Response.status(Response.Status.CREATED).entity(json).build();

    }
	
	// ----------------------- CIFRADO ------------------------------------------------------------------------
	
	public static String llavePrivadaB64;
	public static String fraseAESB64;
    
    @GET
    @Path("/obtenerLlavePublica")
    @Produces({ MediaType.APPLICATION_JSON})
    public LlaveDto obtenerLlavePublica () {
    
        LlaveDto llaveDto = new LlaveDto();
        try {
    
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.genKeyPair();
    
            // Se obtiene la llave pública del par generado
            PublicKey publicKey = kp.getPublic();
            // Se obtiene la llave privada del par generado
            PrivateKey privateKey = kp.getPrivate();
    
            // Se codifica la llave pública en Base64 para su posterior transmisión
            String publicKeyB64 = Base64.encodeBase64String(publicKey.getEncoded());
            String privKeyB64 = Base64.encodeBase64String(privateKey.getEncoded());
            
            System.out.println("llave pub");
            System.out.println(publicKeyB64);
            System.out.println("llave priv");
            System.out.println(privKeyB64);
            
            this.llavePrivadaB64 = privKeyB64;
            
            
            llaveDto.setLlavePublica(publicKeyB64);
            llaveDto.setConsecutivo(87);
            
            this.llavePrivadaB64 = privKeyB64;
            
            return llaveDto;
    
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new PosError("GK000", "Error al generar las llaves publicas y privadas");
        }
    
    }
    
    private String decifrar(byte[] datoCifrado) {

        byte[] datoDecifradoBytes;

        try {
            
            byte[] privBytes = Base64.decodeBase64(llavePrivadaB64);
            PKCS8EncodedKeySpec keySpec2 = new PKCS8EncodedKeySpec(privBytes);
            
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = keyFactory.generatePrivate(keySpec2);
            
            
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING");
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            datoDecifradoBytes = cipher.doFinal(datoCifrado);
            return new String(datoDecifradoBytes, "UTF-8");
            
        } catch (Exception e) {
            
            e.printStackTrace();
            throw new PosError("COD-Y", "Error al decifrar");
            
        }
    }
    
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path("/login2")
    public Response login2 (LoginDto login) {
        
        System.out.println("----------------------------------------");
        
        
        System.out.println("Usuario1: " + login.getUsuario());
        System.out.println("clave1: " + login.getClave());
        
        byte[] usrCif = Base64.decodeBase64(login.getUsuario());
        byte[] clvCif = Base64.decodeBase64(login.getClave());
        byte[] frAesCif = Base64.decodeBase64(login.getFraseAES());
        
        String usr = this.decifrar(usrCif);
        String pwd = this.decifrar(clvCif);
        String frAes = this.decifrar(frAesCif);
        
        
        System.out.println("Usuario: " + usr);
        System.out.println("clave: " + pwd);
        System.out.println("FraseAes: " + frAes);
        
        fraseAESB64 = Base64.encodeBase64String(frAes.getBytes());
        
        if (usrSvc.validarUsuario(usr, pwd)) {
            
            return generarToken(usr);
        }
        
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

	
}
