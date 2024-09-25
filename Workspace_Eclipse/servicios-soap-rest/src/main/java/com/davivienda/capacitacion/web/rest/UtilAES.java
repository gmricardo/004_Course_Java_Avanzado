package com.davivienda.capacitacion.web.rest;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class UtilAES {
	
	public static String cifrarAES(String textoACifrar) {
		System.out.println("-------------------------------cifrarAES-----------------------");
		System.out.println("Dato a cifrar: " + textoACifrar);
		try {
			// Generar un vector de inicialización (IV) aleatorio del tamaño GCM_IV_LONGITUD
			byte[] iv = new byte[12]; //No re-usar con la misma clave
			SecureRandom secureRandom = new SecureRandom();
			secureRandom.nextBytes(iv);

			// Crear un objeto Cipher con el algoritmo especificado y configurarlo para el modo de cifrado
			final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
			
			SecretKey clave = new SecretKeySpec(Base64.decodeBase64(LoginRest.fraseAESB64), "AES");
			//SecretKey clave = new SecretKeySpec(ServicioLogin.claveAES.getBytes(), "AES");

			GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv); //128 bit auth tag length
			cipher.init(Cipher.ENCRYPT_MODE, clave, parameterSpec);

			// Convertir el texto a cifrar a un array de bytes utilizando la codificación UTF-8
			byte[] cipherText = cipher.doFinal(textoACifrar.getBytes(StandardCharsets.UTF_8));

			// Combinar el IV y los datos cifrados en un único array de bytes utilizando un ByteBuffer
			ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
			byteBuffer.put(iv);
			byteBuffer.put(cipherText);
			
			String textoCifrado = Base64.encodeBase64String(byteBuffer.array());
			System.out.println("Dato Cifrado: " + textoCifrado);

			// Devolver el array de bytes resultante
			return textoCifrado;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String descifrarAES(String textoDescifrarB64)  {
        System.out.println("-------------------------------descifrarAES-----------------------");
        System.out.println("Dato a decifrar: " + textoDescifrarB64);
        try {
            // Decodificar el texto cifrado en Base64 a un array de bytes
            byte[] vctTextoCifrado = Base64.decodeBase64(textoDescifrarB64);

            // Separar el IV(Inicializacion Vector) y los datos cifrados
            byte[] iv = Arrays.copyOfRange(vctTextoCifrado, 0, 12);
            byte[] datosCifrados = Arrays.copyOfRange(vctTextoCifrado, 12, vctTextoCifrado.length);

            // Crear un objeto Cipher con el algoritmo especificado y configurarlo para el modo de descifrado
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            // Crear un objeto GCMParameterSpec con el IV para usarlo en el descifrado
            GCMParameterSpec gcmIv = new GCMParameterSpec(128, iv);

            // Inicializar el cifrado con la clave y el IV
            SecretKey clave = new SecretKeySpec(Base64.decodeBase64(LoginRest.fraseAESB64), "AES");
            cipher.init(Cipher.DECRYPT_MODE, clave, gcmIv);

            // Realizar el descifrado y devolver los datos descifrados como un array de bytes
            byte[] datosDescifrados = cipher.doFinal(datosCifrados);
                        
            // Convertir los datos descifrados a una cadena de texto utilizando la codificación UTF-8
            String datoDecifrado = new String(datosDescifrados, StandardCharsets.UTF_8);
            
            System.out.println("Dato decifrado: " + datoDecifrado);
            
            return datoDecifrado;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
