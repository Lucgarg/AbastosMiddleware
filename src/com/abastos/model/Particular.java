package com.abastos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Particular extends ValueObject{
	private Long id;
	private String contrasena;
	private String email;
	private String alias;
	private String numeroTelefono;
	private String numberoMovil;
	private String nombre;
	private String apellidos;
	private Integer puntos;
	private Long idContenido;
	private Date dataAlta;
	private List<DireccionDto> direccionDto = null;
	

	public Particular() {
		direccionDto = new ArrayList<DireccionDto>();

	}

	
	public Long getId() {
		return id;
	}


	public String getContrasena() {
		return contrasena;
	}


	public String getEmail() {
		return email;
	}


	public String getAlias() {
		return alias;
	}


	public String getNumeroTelefono() {
		return numeroTelefono;
	}


	public String getNumberoMovil() {
		return numberoMovil;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public Integer getPuntos() {
		return puntos;
	}


	public Long getIdContenido() {
		return idContenido;
	}


	public Date getDataAlta() {
		return dataAlta;
	}


	public List<DireccionDto> getDireccionDto() {
		return direccionDto;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}


	public void setNumberoMovil(String numberoMovil) {
		this.numberoMovil = numberoMovil;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}


	public void setIdContenido(Long idContenido) {
		this.idContenido = idContenido;
	}


	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}


	public void setDireccionDto(List<DireccionDto> direccionDto) {
		this.direccionDto = direccionDto;
	}


	public void add(DireccionDto direccionDto) {
		this.direccionDto.add(direccionDto);
	}

}
