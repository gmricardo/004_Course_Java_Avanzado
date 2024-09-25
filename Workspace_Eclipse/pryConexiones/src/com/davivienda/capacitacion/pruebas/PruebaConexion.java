package com.davivienda.capacitacion.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebaConexion {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection con;
		PreparedStatement pstm;
		ResultSet rst;
		
		Class.forName("org.hsqldb.jdbcDriver");
		con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/bdCap", "SA", null);
		System.out.println("Conexión Exitosa");
		
		pstm = con.prepareStatement("SELECT * FROM PRODUCTOS");
		rst = pstm.executeQuery();
		
		while(rst.next()) {
			System.out.println(rst.getString(1) + " " + rst.getString(2));
		}
		rst.close();
		pstm.close();
		con.close();

	}

}
