package com.davivienda.capacitacion.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrmChat extends JFrame {
	
	public class HiloEscucha extends Thread {
		
		@Override
		public void run() {
			try {
				while(true) {  
					String rta = in.readLine();
					FrmChat.this.txtMensajesRecibidos.append(rta + System.getProperty("line.separator"));
				}
			} catch (IOException e) {
				System.out.println("Error al enviar");
				e.printStackTrace();
			}
		}
	}
	
	private JTextArea txtMensajesRecibidos;
	
	private JTextField txtMensaje;
	
	private JButton btnEnviar;
	
	private JButton btnConectar;
	
	private JButton btnDesconectar;
	
	private Socket socketConServidor;
	
	private BufferedReader in;
	
	private PrintWriter out;
	
	private HiloEscucha hiloEscucha;
	
	private String ipServidor;
	
	private Integer puerto;
	
	private String nombreUsuario;
	
	public FrmChat () {
		
		this.setLayout(null);
		this.setBounds(10, 10, 300, 410);
		this.setTitle("Power Chat");
		
		txtMensajesRecibidos=new JTextArea();
        JScrollPane scroll = new JScrollPane(txtMensajesRecibidos);
        scroll.setBounds(5,50,280,250);
        this.add(scroll);
        
        txtMensaje=new JTextField();
        txtMensaje.setBounds(5,305,150,45);
        add(txtMensaje);
        
        btnEnviar = new JButton("Env");
        btnEnviar.setBounds(155,305,80,45);
        btnEnviar.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  btnEnviarClick();
		  } 
		});
        add(btnEnviar);
        btnEnviar.setEnabled(false);
        
        btnConectar = new JButton("Conectar...");
        btnConectar.setBounds(5,5,195,40);
        btnConectar.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  btnConectarClick();
		  } 
		});
        add(btnConectar);
        
        btnDesconectar = new JButton("Deconectar...");
        btnDesconectar.setBounds(205,5,195,40);
        btnDesconectar.addActionListener(new ActionListener() { 
		  public void actionPerformed(ActionEvent e) { 
			  btnDesconectarClick();
		  } 
		});
        btnDesconectar.setEnabled(false);
        add(btnDesconectar);
        
        

        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
            	btnDesconectarClick();
            }
        });
        
	}
	
	public void btnConectarClick() {
		
		try {
			System.out.println("Conectando...");
			
			this.socketConServidor = new Socket(this.ipServidor, this.puerto);
			
			InputStreamReader isr = new InputStreamReader(socketConServidor.getInputStream());
			this.in = new BufferedReader(isr);
			this.out = new PrintWriter(socketConServidor.getOutputStream(), true);
			
			System.out.println("Conectando Exitosamente!");
			
			this.btnConectar.setEnabled(false);
			this.btnEnviar.setEnabled(true);
			this.btnDesconectar.setEnabled(true);
			
			//se envía el nombre del cliente o usuario al servidor
			out.println(this.nombreUsuario);
			
			//espero los usuarios conectados
			String usrConn = in.readLine();
			
			System.out.println("-----------------------USUARIO CONECTADOS-------------------------------");
			String vctUsr[] = usrConn.split(";");
			for (int i=0; i<vctUsr.length; i++) {
				System.out.println(vctUsr[i]);
			}
			System.out.println("---------*******************----");
			
			//hilo que esta escuchando los mensajes del servidor
			hiloEscucha = new HiloEscucha();
			hiloEscucha.start();
		
		} catch (IOException e) {
			System.out.println("Error al conectar");
			e.printStackTrace();
		}
	}
	
	public void btnDesconectarClick() {
		
		out.println("CTL:DESCONECTAR");
		this.cerrarConexion();
		this.setVisible(false);
		this.dispose();
			
	}
	
	
	public void btnEnviarClick() {
		
		
			String texto = this.txtMensaje.getText();
			
			out.println("MSJ;" + this.nombreUsuario + ";" + texto);
			
			this.txtMensaje.setText("");

		
	}
	
	private void cerrarConexion() {
		
		try {
			in.close();
			out.close();
			socketConServidor.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar conexion");
			e.printStackTrace();
		}
	}
	
	
	
	public String getIpServidor() {
		return ipServidor;
	}

	public void setIpServidor(String ipServidor) {
		this.ipServidor = ipServidor;
	}

	public Integer getPuerto() {
		return puerto;
	}

	public void setPuerto(Integer puerto) {
		this.puerto = puerto;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public static void main(String args[]) {
		
		System.out.println("Parametros recibidos: " +  args[0] + " " + args[1] + " " + args[2]);
		
		
		FrmChat frm = new FrmChat();
		frm.setIpServidor(args[0]);
		frm.setPuerto(new Integer(args[1]));
		frm.setNombreUsuario(args[2]);
		
		frm.setTitle(frm.getNombreUsuario());
		frm.setVisible(true);
		
		frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

}
