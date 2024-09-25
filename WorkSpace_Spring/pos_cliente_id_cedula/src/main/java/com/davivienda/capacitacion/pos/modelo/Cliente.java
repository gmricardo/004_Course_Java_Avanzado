package com.davivienda.capacitacion.pos.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "CLIENTES")

public class Cliente {

	@Id
	private String cedula;
	private String nombre;
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;
	@Column(name = "CORREO_ELECTRONICO")
	private String correoElectronico;
	private String celular;
	@Column(name = "NUMERO_HIJOS")
	private Integer numeroHijos;
	@Column(name = "USUARIO_CREA")
	private String usuarioCrea;
	@Column(name = "FECHA_CREACION")
	private LocalDate fechaCreacion;
	@Column(name = "USUARIO_MODIFICA")
	private String usuarioModifica;
	@Column(name = "FECHA_MODIFICACION")
	private LocalDate fechaModificacion;
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Integer getNumeroHijos() {
		return numeroHijos;
	}
	public void setNumeroHijos(Integer numeroHijos) {
		this.numeroHijos = numeroHijos;
	}
	public String getUsuarioCrea() {
		return usuarioCrea;
	}
	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuarioModifica() {
		return usuarioModifica;
	}
	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	public LocalDate getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(LocalDate fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	
}
