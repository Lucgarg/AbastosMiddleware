package com.abastos.model;

import java.util.Date;

public class Empresa extends ValueObject{
	private Long id;
	private String contrasena;
	private String nombre;
	private String apellidos;
	private String cif;
	private String alias;
	private String razonSocial;
	private String correoElectronico;
	private Long idContenido;
	private Date dataAlta;
	private DireccionDto direccion;
	public Empresa() {
		
	}
	public Long getId() {
		return id;
	}
	public String getContrasena() {
		return contrasena;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getCif() {
		return cif;
	}
	public String getAlias() {
		return alias;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public Long getIdContenido() {
		return idContenido;
	}
	public Date getDataAlta() {
		return dataAlta;
	}
	public DireccionDto getDireccion() {
		return direccion;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public void setIdContenido(Long idContenido) {
		this.idContenido = idContenido;
	}
	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}
	public void setDireccion(DireccionDto direccion) {
		this.direccion = direccion;
	}
	
	
	

}
