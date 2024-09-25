package com.davivienda.capacitacion.pos.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="CLIENTES")
public class Cliente {
	
	@Id
	private ClientePK id;
	
	private String celular;

	@Column(name="CORREO_ELECTRONICO")
	private String correoElectronico;

	@Column(name="FECHA_CREACION")
	private LocalDateTime fechaCreacion;

	@Column(name="FECHA_MODIFICACION")
	private LocalDateTime fechaModificacion;

	@Column(name="FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;

	private String nombre;

	@Column(name="NUMERO_HIJOS")
	private BigDecimal numeroHijos;

	@Column(name="USUARIO_CREA")
	private String usuarioCrea;
	
	@Column(name="USUARIO_MODIFICA")
	private String usuarioModifica;
	
	//Si pone FetchType.EAGER se hara la consulta a facturas inmediatamente cuando traiga al cliente
	@OneToMany(mappedBy="cliente", fetch = FetchType.LAZY)
	private List<Factura> lstFacturas;

	public ClientePK getId() {
		return id;
	}

	public void setId(ClientePK id) {
		this.id = id;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getNumeroHijos() {
		return numeroHijos;
	}

	public void setNumeroHijos(BigDecimal numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public String getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public List<Factura> getLstFacturas() {
		return lstFacturas;
	}

	public void setLstFacturas(List<Factura> lstFacturas) {
		this.lstFacturas = lstFacturas;
	}
	
	
	
}
