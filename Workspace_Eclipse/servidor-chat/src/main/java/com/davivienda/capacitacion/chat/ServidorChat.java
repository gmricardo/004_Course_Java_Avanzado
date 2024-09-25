package com.davivienda.capacitacion.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class ServidorChat 
{
    public class HiloEscucha extends Thread {
    	
    	private Socket socket;
    	
    	private BufferedReader in;
    	
    	private PrintWriter out;
    	
    	private String nombreUsuario;
    	
    	public HiloEscucha(Socket socket) {
    		this.socket = socket;
    		try {
	    		InputStreamReader isr = new InputStreamReader(this.socket.getInputStream());
	    		in = new BufferedReader(isr);
	    		out = new PrintWriter(this.socket.getOutputStream(), true);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	@Override
    	public void run() {
    		try {
	    		
    			this.nombreUsuario = in.readLine();
    			System.out.println("Nombre de usuario recibido: " + this.nombreUsuario);
    			
    			if (this.nombreUsuario.equals("CTL;BAJAR_SERVICIO")) {
    				ServidorChat.this.servicioArriba = Boolean.FALSE;
    				System.out.println("Se bajará el servicio.");
    				BajarServicio.bajarServicio();
    				return;
    			}
    			
    			
    			boolean valido = validar(this.nombreUsuario);
    			
    			if (valido) {
    				
    				String usrConectados = obtenerUsuariosConectados();
    				System.out.println("Usuario conectados enviados al cliente: " + this.nombreUsuario);
    				out.println(usrConectados);
    				
    				while(true) {
    					
    					String msj = in.readLine();
    					System.out.println("Mensaje Recibido: " + msj);
    					if (msj.startsWith("MSJ;")) {
    						String[] vctMsj = msj.split(";");
    						propagarMensaje(vctMsj[1], vctMsj[2], vctMsj[3]);
    					} else if (msj.equals("CTL:DESCONECTAR")) {
    						ServidorChat.this.desconectar(this);
    						break;
    					}
    					
    				}
    				
    				out.close();
    				in.close();
    				socket.close();
    				
    				System.out.println(this.nombreUsuario + ": Usuario desconectado correctamente");
    			} 
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    }
	
    private List<HiloEscucha> lstConexiones = new ArrayList<HiloEscucha>();
    
    private boolean servicioArriba;
    
    private void desconectar(HiloEscucha hilo) {
    	this.lstConexiones.remove(hilo);
    }
    
    /**
     * Valida que el nombre del usuario conectado sea unico
     * @param nombreUsuario
     * @return
     */
    private boolean validar(String nombreUsuario) {
    	
    	int cantidad = 0;
    	for (HiloEscucha hs: this.lstConexiones) {
    		if (hs.nombreUsuario.equals(nombreUsuario)) {
    			cantidad++;
    			if (cantidad > 1) {
    				return Boolean.FALSE;
    			}
    		}
    	}
    	
    	return Boolean.TRUE;
    }
	
    /**
     * Retorna los nombres de usuarios conectados separados por ;
     * @return
     */
    private String obtenerUsuariosConectados() {
    	
    	StringBuilder sbUsrConn = new StringBuilder();
    	
    	for (int i=0; i<this.lstConexiones.size(); i++) {
    		HiloEscucha hs = this.lstConexiones.get(i);
    		if (i!=0) {
    			sbUsrConn.append(";");
    		}
    		sbUsrConn.append(hs.nombreUsuario);
    	}
    	
    	return sbUsrConn.toString();

    }
    
    private void propagarMensaje(String origen, String destino, String mensaje) {
    	
    	if (destino.equals("*")) {
	    	for (HiloEscucha hs: this.lstConexiones) {
	    		System.out.println(origen + ":" + mensaje);
	    		hs.out.println(origen + ":" + mensaje);
	    	}
    	} else {
    		for (HiloEscucha hs: this.lstConexiones) {
	    		if (hs.nombreUsuario.equals(origen)) {
	    			hs.out.println(origen + ":*" + mensaje);
	    		}
	    		if (hs.nombreUsuario.equals(destino)) {
	    			hs.out.println(origen + ":*" + mensaje);
	    		}
	    	}
    	}
    	
    }
    
    private void cerrarConexiones(ServerSocket serverSocket) throws IOException {
    	
    	for (HiloEscucha hs: this.lstConexiones) {
    		hs.socket.close();
    	}
    	
    	serverSocket.close();
    	
    }
    
	private void iniciarServicio () throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(8085);
		
		servicioArriba = Boolean.TRUE;
		
		while (servicioArriba) {
			
			System.out.println("Esperando un cliente...");
			
			Socket socketCliente = serverSocket.accept();
			
			if (this.servicioArriba) {
			
				HiloEscucha he = new HiloEscucha(socketCliente);
				this.lstConexiones.add(he);
				he.start();
			
			}
			
		}
		
		cerrarConexiones(serverSocket);
		
	}
	
	public static void main( String[] args ) throws IOException
    {
        ServidorChat servidor = new ServidorChat();
        servidor.iniciarServicio();
    }
}
