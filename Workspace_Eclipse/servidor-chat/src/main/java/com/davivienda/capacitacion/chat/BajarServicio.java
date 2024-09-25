package com.davivienda.capacitacion.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class BajarServicio {

	public static void bajarServicio() throws UnknownHostException, IOException {
		
		Socket socketConServidor = new Socket("localhost", 8085);
		PrintWriter out = new PrintWriter(socketConServidor.getOutputStream(), true);
		out.println("CTL;BAJAR_SERVICIO");
		System.out.println("Envio el mensaje");
		
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("cierro el cliente");
		socketConServidor.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		bajarServicio();

	}

}
