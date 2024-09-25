package com.davivienda.capacitacion.hilos;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class ManejadorHilos 
{
	
	public class HiloPrueba extends Thread{
		
		private String nombre;
		
		private long demora;
		
		private boolean finalizarEjecucion = Boolean.FALSE;
		
		public HiloPrueba (String nombre, long demora) {
			this.nombre = nombre;
			this.demora = demora;
			
		}
		
		@Override
		public void run() {

	        FileWriter fw = null;
	        PrintWriter pw = null;
	        
	        try {
	            fw = new FileWriter("Valores.txt", true);
	            pw = new PrintWriter(fw);
	            
	            while (!finalizarEjecucion) {
	            	synchronized (llave) {
	            		int v = obtenerValor();
		                pw.println(this.nombre +  ":" + v);
		                pw.flush();
					}
	            	
	                try {
	                    this.sleep(demora);
	                } catch (InterruptedException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            
	            System.out.println("Finaliza ejecucion de hilo: " + this.nombre);
	        } catch (Exception e) {
	            System.out.println("Finaliza ejecucion de hilo con error: " + this.nombre);
	            e.printStackTrace();
	        } finally {
	            if (pw != null) {
	                pw.close();
	            }
	            if (fw != null) {
	                try {
	                    fw.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        }
		}
		
		public void finalizarEjecucion() {
			this.finalizarEjecucion = Boolean.TRUE;
		}

	}
	
	private int valor = 0;
	
	private Object llave = new Object();
	
	private int obtenerValor() {
		int x;
		x = valor;
		valor = valor + 1;
		return x;
	}
	
	public void iniciarProcesos()  throws IOException {
		 HiloPrueba hp = new HiloPrueba("PRIMERO", 3);
    	 HiloPrueba hp2 = new HiloPrueba("SEGUNDO", 3);
         HiloPrueba hp3 = new HiloPrueba("TERCERO", 3);
         HiloPrueba hp4 = new HiloPrueba("CUARTO", 3);
         HiloPrueba hp5 = new HiloPrueba("QUINTO", 3);
         
         hp.start();
         hp2.start();
         hp3.start();
         hp4.start();
         hp5.start();
         
         System.out.println( "Hola Mundo desde el hilo principal" );
         
         InputStreamReader isr = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(isr);
         System.out.println("Presione enter para finalizar la ejecucion...");
         br.readLine();
         
         
         hp.finalizarEjecucion();
         hp2.finalizarEjecucion();
         hp3.finalizarEjecucion();
         hp4.finalizarEjecucion();
         hp5.finalizarEjecucion();
         
         br.close();
         isr.close();
         
         System.out.println("Finaliza el hilo principal");
	}
	
	
	
    public static void main( String[] args ) throws IOException
    {
    	ManejadorHilos mh = new ManejadorHilos();
    	mh.iniciarProcesos();
    }
}
